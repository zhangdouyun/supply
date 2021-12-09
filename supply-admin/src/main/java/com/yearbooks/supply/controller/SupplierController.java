package com.yearbooks.supply.controller;


import com.yearbooks.supply.model.RespBean;
import com.yearbooks.supply.pojo.Supplier;
import com.yearbooks.supply.query.SupplierQuery;
import com.yearbooks.supply.service.ISupplierService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 供应商表 前端控制器
 * </p>
 *
 * @author zhangDouYun
 * @since 2021-12-06
 */
@Controller
@RequestMapping("/supplier")
public class SupplierController {

    @Resource
    private ISupplierService supplierService;

    /**
     * ==================================供应商管理主页=================================
     * 供应商管理主页
     * 1.注意事项：24行“index”来自main.ftl;/supplier/index
     */
    @RequestMapping("index")
    public String index(){
        return "supplier/supplier";
    }


    /**
     * ==================================供应商管理查询=================================
     * @param supplierQuery
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> supplierLsit(SupplierQuery supplierQuery){
        return supplierService.supplierList(supplierQuery);
    }

    /**
     * ==================================供应商:打开主页面==============================
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("addOrUpdateSupplierPage")
    public String addOrUpdateSupplierPage(Integer id, Model model){
        if (null!=id){
            model.addAttribute("supplier",supplierService.getById(id));
        }
        return "supplier/add_update";
    }


    /**
     * ==================================供应商:添加供应商==============================
     * @param supplier
     * @return
     */
    @RequestMapping("save")
    @ResponseBody
    public RespBean saveSupplier(Supplier supplier){
        supplierService.saveSupplier(supplier);
        return RespBean.success("供应商记录添加成功!");
    }

    /**
     * ==================================供应商:更新供应商==============================
     * @param supplier
     * @return
     */
    @RequestMapping("update")
    @ResponseBody
    public RespBean updateSupplier(Supplier supplier){
        supplierService.updateSupplier(supplier);
        return RespBean.success("供应商记录更新成功");
    }

    /**
     * =================================供应商:删除供应商==============================
     * @param ids
     * @return
     */
    @RequestMapping("delete")
    @ResponseBody
    public RespBean deleteSupplier(Integer[] ids){
        supplierService.deleteSupplier(ids);
        return RespBean.success("用户删除成功!");
    }



}
