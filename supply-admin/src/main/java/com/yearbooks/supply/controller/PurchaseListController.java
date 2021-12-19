package com.yearbooks.supply.controller;


import com.yearbooks.supply.service.IPurchaseListService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

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
}
