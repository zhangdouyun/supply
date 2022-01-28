package com.yearbooks.supply.controller;


import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.yearbooks.supply.model.RespBean;
import com.yearbooks.supply.pojo.PurchaseList;
import com.yearbooks.supply.pojo.PurchaseListGoods;
import com.yearbooks.supply.query.PurchaseListGoodsQuery;
import com.yearbooks.supply.query.PurchaseListQuery;
import com.yearbooks.supply.service.IGoodsService;
import com.yearbooks.supply.service.IPurchaseListGoodsService;
import com.yearbooks.supply.service.IPurchaseListService;
import com.yearbooks.supply.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 进货单 前端控制器
 * </p>
 *
 * @author zhangDouYun
 * @since 2021-12-18
 */
@Controller
@RequestMapping("/purchase")
public class PurchaseListController {

    @Resource
    private IPurchaseListService purchaseListService;

    @Resource
    private IUserService userService;

    /**
     * 进货入库主页面；
     * @return
     */
    @RequestMapping("index")
    public String index(Model model){
        String purchaseNumber = purchaseListService.getNextPurchaseNumber();
        //注意：purchaseNumber和前端保持一致；
        model.addAttribute("purchaseNumber",purchaseNumber);
        return "purchase/purchase";
    }

    /**
     * 保存进货单据
     * @param purchaseList
     * @param goodsJson
     * @param principal
     * @return
     */
    @RequestMapping("save")
    @ResponseBody
    public RespBean save(PurchaseList purchaseList, String goodsJson, Principal principal){
        String userName = principal.getName();
        purchaseList.setUserId(userService.findUserByName(userName).getId());
        Gson gson = new Gson();
        List<PurchaseListGoods> plgList = gson.fromJson(goodsJson, new TypeToken<List<PurchaseListGoods>>(){}.getType());
        purchaseListService.savePurchaseList(purchaseList,plgList);
        return RespBean.success("商品进货入库成功!");
    }

    /**
     * 进货单查询；
     * @return
     */
    @RequestMapping("searchPage")/*注意此处searchPage为main.ftl中“进货单查询”条目purchase/searchPage*/
    public String searchPage(){
        return "purchase/purchase_search";
    }


    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> purchaseListGoodsList(PurchaseListQuery purchaseListQuery){
        return purchaseListService.purchaseList(purchaseListQuery);
    }

    /**
     * 删除进货单记录；
     * @param id
     * @return
     */
    @RequestMapping("delete")
    @ResponseBody
    public RespBean delete(Integer id){
        purchaseListService.deletePurchaseList(id);
        return RespBean.success("删除成功!");
    }

    @RequestMapping("update")
    @ResponseBody
    public RespBean update(Integer id){
        purchaseListService.updatePurchaseListState(id);
        return RespBean.success("状态更新成功!");
    }

    /**
     * 商品采购查询接口
     * @param purchaseListQuery
     * @return
     */
    @RequestMapping("countPurchase")
    @ResponseBody
    public Map<String,Object> countPurchase(PurchaseListQuery purchaseListQuery){
        return purchaseListService.countPurchase(purchaseListQuery);
    }

}
