package com.yearbooks.supply.controller;


import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.yearbooks.supply.model.RespBean;
import com.yearbooks.supply.model.SaleCount;
import com.yearbooks.supply.pojo.SaleList;
import com.yearbooks.supply.pojo.SaleListGoods;
import com.yearbooks.supply.query.SaleListQuery;
import com.yearbooks.supply.service.ISaleListService;
import com.yearbooks.supply.service.IUserService;
import com.yearbooks.supply.utils.DateUtil;
import com.yearbooks.supply.utils.MathUtil;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 销售单表 前端控制器
 * </p>
 *
 * @author zhangDouYun
 * @since 2021-12-31
 */
@Controller
@RequestMapping("/sale")
public class SaleListController {

    @Resource
    private ISaleListService saleListService;

    @Resource
    private IUserService userService;

    /**
     * 销售出库主页
     *
     * @return
     */
    @RequestMapping("index")
    public String index(Model model) {
        model.addAttribute("saleNumber", saleListService.getNextSaleNumber());
        return "sale/sale";
    }
    /*getNextSaleNumber-Model存起来，供前端调用，sale.ftl页面有 <legend>单号:${saleNumber!}</legend> */

    @RequestMapping("save")
    @ResponseBody
    public RespBean save(SaleList saleList, String goodsJson, Principal principal) {
        String userName = principal.getName();
        saleList.setUserId(userService.findUserByName(userName).getId());
        Gson gson = new Gson();
        List<SaleListGoods> slgList = gson.fromJson(goodsJson, new TypeToken<List<SaleListGoods>>() {
        }.getType());
        saleListService.saveSaleList(saleList, slgList);
        return RespBean.success("商品销售出库成功!");
    }

    /**
     * 销售单查询页
     *
     * @return
     */
    @RequestMapping("searchPage")
    public String searchPage() {
        return "sale/sale_search";
    }

    /**
     * 查询商品销售单
     *
     * @param saleListQuery
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> saleList(SaleListQuery saleListQuery) {
        return saleListService.saleList(saleListQuery);
    }

    /**
     * 删除销售单据
     *
     * @param id
     * @return
     */
    @RequestMapping("delete")
    @ResponseBody
    public RespBean delete(Integer id) {
        saleListService.deleteSaleList(id);
        return RespBean.success("删除成功！");
    }

    /**
     * 销售单结算接口
     *
     * @param id
     * @return
     */
    @RequestMapping("update")
    @ResponseBody
    public RespBean update(Integer id) {
        saleListService.updateSaleListState(id);
        return RespBean.success("销售单结算成功!");
    }

    /**
     * 销售收入统计接口
     *
     * @param saleListQuery
     * @return
     */
    @RequestMapping("countSale")
    @ResponseBody
    public Map<String, Object> countSale(SaleListQuery saleListQuery) {
        return saleListService.countSale(saleListQuery);
    }

    /**
     * 按日销售统计接口；
     *
     * @param begin
     * @param end
     * @return
     */
    @RequestMapping("countSaleByDay")
    @ResponseBody
    public Map<String, Object> countDaySale(String begin, String end) {
        Map<String, Object> result = new HashMap<String, Object>();
        List<SaleCount> saleCounts = new ArrayList<SaleCount>();
        List<Map<String, Object>> list = saleListService.countDaySale(begin, end);/*注意：查询日期集合‘yyyy-MM-dd格式’*/
        /**
         * 根据传入的时间段，生成日期列表
         */
        List<String> datas = DateUtil.getRangeDates(begin, end);
        System.out.println(datas.toString());
        /*遍历*/
        for (String data : datas) {
            SaleCount saleCount = new SaleCount();
            saleCount.setDate(data);
            boolean flag = true;
            for (Map<String, Object> map : list) {/*遍历日期，*/
                String dd = map.get("saleDate").toString().substring(0, 10);//根据saleDate查询出相关数据；
                if (data.equals(dd)) {
                    saleCount.setAmountCost(MathUtil.format2Bit(Float.parseFloat(map.get("amountCost").toString())));
                    /*给实例属性赋值*/
                    saleCount.setAmountSale(MathUtil.format2Bit(Float.parseFloat(map.get("amountSale").toString())));
                    /*给实例属性赋值*/
                    saleCount.setAmountProfit(MathUtil.format2Bit(saleCount.getAmountSale() - saleCount.getAmountCost()));
                    flag = false;
                }
            }
            if (flag) {
                saleCount.setAmountSale(0F);
                saleCount.setAmountCost(0F);
                saleCount.setAmountProfit(0F);
            }
            saleCounts.add(saleCount);
        }
        result.put("count", saleCounts.size());
        result.put("data", saleCounts);
        result.put("code", 0);
        result.put("msg", "");
        return result;
    }

    /**
     * 按月统计接口；
     * @param begin
     * @param end
     * @return
     */
    @RequestMapping("countSaleByMonth")
    @ResponseBody
    public Map<String, Object> countSaleByMonth(String begin, String end) {
        Map<String, Object> result = new HashMap<String, Object>();
        ArrayList<SaleCount> saleCounts = new ArrayList<SaleCount>();
        List<Map<String, Object>> list = saleListService.countSaleByMonth(begin, end);
        /*话分两头，一边去查询数据，一边加工日期*/
        List<String> datas = DateUtil.getRangeMonth(begin, end);
        /*遍历相关日期虚列*/
        for (String data : datas) {
            SaleCount saleCount = new SaleCount();
            saleCount.setDate(data);
            boolean flag = true;
            for (Map<String, Object> map : list) {
                String dd = map.get("saleDate").toString().substring(0, 7);
                if (data.equals(dd)){
                    saleCount.setAmountSale(MathUtil.format2Bit(Float.parseFloat(map.get("amountSale").toString())));
                    saleCount.setAmountCost(MathUtil.format2Bit(Float.parseFloat(map.get("amountCost").toString())));
                    saleCount.setAmountProfit(MathUtil.format2Bit(saleCount.getAmountSale()-saleCount.getAmountCost()));
                    flag = false;
                }
            }
            if (flag){
                saleCount.setAmountSale(0F);
                saleCount.setAmountCost(0F);
                saleCount.setAmountProfit(0F);
            }
            saleCounts.add(saleCount);
        }
        result.put("count",saleCounts.size());
        result.put("data",saleCounts);
        result.put("code",0);
        result.put("msg","");
        return result;
    }

}










