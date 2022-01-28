package com.yearbooks.supply.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zhangdouyun
 * @version 1.0
 * @className ReportController
 * @description TODO
 * @since 2022-01-16 22:50
 * 统计报表
 */

@Controller
@RequestMapping("report")
public class ReportController {

    /**
     * 启动供应商统计主页面；
     * @return
     */
    @RequestMapping("countSupplier")
    public String countSupplierPage(){
        return "count/supplier";
    }

    /**
     * 启动客户统计主页面；
     * @return
     */
    @RequestMapping("countCustomer")
    public String countCustomer(){
        return "count/customer";
    }

    /**
     * 商品采购统计启动页面
     * @return
     */
    @RequestMapping("countPurchase")
    public String countPurchase(){
        return "count/purchase";
    }

    /**
     * 商品销售统计启动页面
     * @return
     */
    @RequestMapping("countSale")
    public String countSale(){
        return "count/sale";
    }

    /**
     * 商品日销售启动页面
     * @return
     */
    @RequestMapping("countDaySale")
    public String countDaySale(){
        return "count/day_sale";
    }

    /**
     * 商品月销售启动页面
     * @return
     */
    @RequestMapping("countMonthSale")
    public String countMonthSale(){
        return "count/month_sale";
    }


}












