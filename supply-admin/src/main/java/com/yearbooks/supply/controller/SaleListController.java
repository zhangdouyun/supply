package com.yearbooks.supply.controller;


import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.yearbooks.supply.model.RespBean;
import com.yearbooks.supply.pojo.PurchaseListGoods;
import com.yearbooks.supply.pojo.SaleList;
import com.yearbooks.supply.pojo.SaleListGoods;
import com.yearbooks.supply.query.SaleListQuery;
import com.yearbooks.supply.service.ISaleListService;
import com.yearbooks.supply.service.IUserService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.security.Principal;
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
     * @return
     */
    @RequestMapping("index")
    public String index(Model model){
        model.addAttribute("saleNumber",saleListService.getNextSaleNumber());
        return "sale/sale";
    }
    /*getNextSaleNumber-Model存起来，供前端调用，sale.ftl页面有 <legend>单号:${saleNumber!}</legend> */

    @RequestMapping("save")
    @ResponseBody
    public RespBean save(SaleList saleList, String goodsJson, Principal principal){
        String userName = principal.getName();
        saleList.setUserId(userService.findUserByName(userName).getId());
        Gson gson = new Gson();
        List<SaleListGoods> slgList =  gson.fromJson(goodsJson, new TypeToken<List<SaleListGoods>>() {}.getType());
        saleListService.saveSaleList(saleList,slgList);
        return RespBean.success("商品销售出库成功!");
    }

    /**
     * 销售单查询页
     * @return
     */
    @RequestMapping("searchPage")
    public String searchPage(){
        return "sale/sale_search";
    }

    /**
     * 查询商品销售单
     * @param saleListQuery
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> saleList(SaleListQuery saleListQuery){
        return saleListService.saleList(saleListQuery);
    }




}
