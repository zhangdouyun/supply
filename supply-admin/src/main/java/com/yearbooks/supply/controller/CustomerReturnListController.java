package com.yearbooks.supply.controller;


import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.yearbooks.supply.model.RespBean;
import com.yearbooks.supply.pojo.CustomerReturnList;
import com.yearbooks.supply.pojo.CustomerReturnListGoods;
import com.yearbooks.supply.query.CustomerReturnListQuery;
import com.yearbooks.supply.service.ICustomerReturnListService;
import com.yearbooks.supply.service.IUserService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.spring.web.json.Json;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 客户退货单表 前端控制器
 * </p>
 *
 * @author zhangDouYun
 * @since 2021-12-31
 */
@Controller
@RequestMapping("/customerReturn")
public class CustomerReturnListController {

    @Resource
    private ICustomerReturnListService customerReturnListService;

    @Resource
    private IUserService userService;

    /**
     * 客户退货主页面；
     * @return
     */
    @RequestMapping("index")
    public String index(Model model){
        model.addAttribute("customerReturnNumber",customerReturnListService.getNextCustomerReturnnumber());
        System.out.println(model.toString());
        return "customerReturn/customer_return";
    }

    /**
     * 保存退货单据及退货商品
     * @param customerReturnList
     * @param goodsJson
     * @param principal
     * @return
     */
    @RequestMapping("save")
    @ResponseBody
    public RespBean save(CustomerReturnList customerReturnList, String goodsJson, Principal principal){
        String userName = principal.getName();
        customerReturnList.setUserId(userService.findUserByName(userName).getId());
        Gson gson = new Gson();
        List<CustomerReturnListGoods> slgList = gson.fromJson(goodsJson, new TypeToken<List<CustomerReturnListGoods>>() {}.getType());
        customerReturnListService.saveCustomerReturnList(customerReturnList,slgList);
        return RespBean.success("商品退货成功!");
    }
    /**
     * 退货单及退货商品页面
     * @return
     */
    @RequestMapping("searchPage")
    public String index(){
        return "customerReturn/customer_return_search";
    }

    /**
     * 退货单列表；
     * @param customerReturnListQuery
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> customerReturnList(CustomerReturnListQuery customerReturnListQuery){
        System.out.println("=======>"+customerReturnListService.customerReturnList(customerReturnListQuery));
        return customerReturnListService.customerReturnList(customerReturnListQuery);
    }

    /**
     * 删除退货单
     * @param id
     * @return
     */
    @RequestMapping("delete")
    @ResponseBody
    public RespBean delete(Integer id){
        customerReturnListService.deleteCustomerReturnList(id);
        return RespBean.success("退货单删除成功!");
    }
}
