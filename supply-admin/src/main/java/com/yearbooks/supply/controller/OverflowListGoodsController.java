package com.yearbooks.supply.controller;


import com.yearbooks.supply.query.OverFlowListGoodsQuery;
import com.yearbooks.supply.service.IOverflowListGoodsService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 报溢单商品表 前端控制器
 * </p>
 *
 * @author zhangDouYun
 * @since 2022-01-10
 */
@Controller
@RequestMapping("/overflowListGoods")
public class OverflowListGoodsController {

    @Resource
    private IOverflowListGoodsService overflowListGoodsService;

    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> overFlowListGoodsList(OverFlowListGoodsQuery overFlowListGoodsQuery){
        return overflowListGoodsService.overflowListGoodsListGoods(overFlowListGoodsQuery);
    }
}
