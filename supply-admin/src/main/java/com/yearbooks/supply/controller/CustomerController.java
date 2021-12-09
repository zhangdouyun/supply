package com.yearbooks.supply.controller;


import com.yearbooks.supply.query.CustomerQuery;
import com.yearbooks.supply.service.ICustomerService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 客户表 前端控制器
 * </p>
 *
 * @author zhangDouYun
 * @since 2021-12-07
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Resource
    private ICustomerService customerService;

    /**
     * ==========================1.客户管理：主页面==========================
     * @return
     */
    @RequestMapping("index")
    public String index(){
        return "customer/customer";
    }

    /**
     * ==========================2.客户管理：客户名单=========================
     * @param customerQuery
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> customerList(CustomerQuery customerQuery){
        return customerService.customerList(customerQuery);
    }

    /**
     * ==========================3.客户管理：增加或修改=========================
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("addOrUpdateCustomerPage")
    public String addOrUpdateCustomer(Integer id, Model model){
        if (null!=id){
            model.addAttribute("customer",customerService.getById(id));
        }
        return "customer/add_update";
    }



}
