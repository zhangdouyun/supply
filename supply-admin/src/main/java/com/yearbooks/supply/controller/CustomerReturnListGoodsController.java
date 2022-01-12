package com.yearbooks.supply.controller;


import com.yearbooks.supply.pojo.CustomerReturnList;
import com.yearbooks.supply.query.CustomerReturnListGoodsQuery;
import com.yearbooks.supply.query.CustomerReturnListQuery;
import com.yearbooks.supply.service.ICustomerReturnListGoodsService;
import com.yearbooks.supply.service.ICustomerReturnListService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 客户退货单商品表 前端控制器
 * </p>
 *
 * @author zhangDouYun
 * @since 2021-12-28
 */
@Controller
@RequestMapping("/customerReturnListGoods")
public class CustomerReturnListGoodsController {

    @Resource
    private ICustomerReturnListGoodsService customerReturnListGoodsService;

    /**
     *
     * @param customerReturnListGoodsQuery
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> customerReturnListGoodsList(CustomerReturnListGoodsQuery customerReturnListGoodsQuery){
        return customerReturnListGoodsService.customerReturnListGoods(customerReturnListGoodsQuery);
    }
}
