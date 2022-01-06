package com.yearbooks.supply.controller;


import com.yearbooks.supply.query.PurchaseListGoodsQuery;
import com.yearbooks.supply.service.IPurchaseListGoodsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 进货单商品表 前端控制器
 * </p>
 *
 * @author zhangDouYun
 * @since 2021-12-18
 */
@Controller
@RequestMapping("/purchaseListGoods")
public class PurchaseListGoodsController {

    @Resource
    private IPurchaseListGoodsService purchaseListGoodsService;

    /**
     *
     * @param purchaseListGoodsQuery
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> purchaseListGoodsList(PurchaseListGoodsQuery purchaseListGoodsQuery){
        return purchaseListGoodsService.purchaseListGoodsList(purchaseListGoodsQuery);
    }



}
