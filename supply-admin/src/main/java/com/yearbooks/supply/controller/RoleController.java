package com.yearbooks.supply.controller;


import com.yearbooks.supply.model.RespBean;
import com.yearbooks.supply.pojo.Role;
import com.yearbooks.supply.query.RoleQuery;
import com.yearbooks.supply.service.IRoleService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author zhangDouYun
 * @since 2021-11-17
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    //注入IRoleService,注意：注解一定写上，否则：返回的数据不符合规范，正确的成功状态码 (code) 应为：0
    @Resource
    private IRoleService roleService;

    /*=========================================1.角色：主页面；=========================================*/

    /**
     * 1.角色管理主页面
     * 2.特点：直接在controller搞定。
     *
     * @return
     */
    @RequestMapping("index")//在main主页面：data-tab="role/index"斜杠前role为第17行/role。
    public String index() {
        return "role/role";
    }
    /*========================================2.角色：获取角色；========================================*/

    /**
     * @param roleQuery
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> roleList(RoleQuery roleQuery) {
        return roleService.roleList(roleQuery);
    }
    /*========================================3.角色：获取页面；========================================*/

    @RequestMapping("addOrUpdateRolePage")
    public String addOrUpdatePage(Integer id, Model model){
        if (null!=id){
            model.addAttribute("role",roleService.getById(id));
        }
        return "role/add_update";
    }
    /*========================================4.角色：添加角色；========================================*/

    @RequestMapping("save")
    @ResponseBody
    public RespBean saveRole(Role role){
        roleService.saveRole(role);
        return RespBean.success("角色添加成功!");
    }

    /*========================================5.角色：修改角色；========================================*/

    @RequestMapping("update")
    @ResponseBody
    public RespBean updateRole(Role role){
        roleService.updateRole(role);
        return RespBean.success("角色修改成功!");
    }
    /*========================================6.角色：删除角色；========================================*/

    @RequestMapping("delete")
    @ResponseBody
    public RespBean deleteRole(Integer id){
        roleService.deleteRole(id);
        return RespBean.success("角色删除成功!");
    }

}
