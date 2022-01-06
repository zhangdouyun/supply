package com.yearbooks.supply.controller;


import com.yearbooks.supply.query.ReturnListGoodsQuery;
import com.yearbooks.supply.service.IReturnListGoodsService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 退货单商品表 前端控制器
 * </p>
 *
 * @author zhangDouYun
 * @since 2021-12-25
 */
@Controller
@RequestMapping("/returnListGoods")
public class ReturnListGoodsController {

    @Resource
    private IReturnListGoodsService returnListGoodsService;

    /**
     * 退货商品清单；
     * @param returnListGoodsQuery
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> returnListGoodsList(ReturnListGoodsQuery returnListGoodsQuery){
        return returnListGoodsService.returnListGoodsList(returnListGoodsQuery);
    }

}
