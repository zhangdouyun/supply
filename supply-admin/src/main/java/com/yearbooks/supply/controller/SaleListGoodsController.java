package com.yearbooks.supply.controller;


import com.yearbooks.supply.query.SaleListGoodsQuery;
import com.yearbooks.supply.service.ISaleListGoodsService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 销售单商品表 前端控制器
 * </p>
 *
 * @author zhangDouYun
 * @since 2021-12-28
 */
@Controller
@RequestMapping("/saleListGoods")
public class SaleListGoodsController {

    @Resource
    private ISaleListGoodsService saleListGoodsService;

    /**
     * 销售商品清单；
     * @param saleListGoodsQuery
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> saleListGoodsList(SaleListGoodsQuery saleListGoodsQuery){
        return saleListGoodsService.saleListGoodsList(saleListGoodsQuery);
    }

}
