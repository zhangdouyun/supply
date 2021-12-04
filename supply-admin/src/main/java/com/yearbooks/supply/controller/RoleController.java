package com.yearbooks.supply.controller;


import com.yearbooks.supply.model.RespBean;
import com.yearbooks.supply.pojo.Role;
import com.yearbooks.supply.query.RoleQuery;
import com.yearbooks.supply.service.IRoleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
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

    //注入IRoleService,注意：注解一定写上，否则提示：返回的数据不符合规范，正确的成功状态码 (code) 应为：0
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
    public String addOrUpdatePage(Integer id, Model model) {
        if (null != id) {
            model.addAttribute("role", roleService.getById(id));
        }
        return "role/add_update";
    }
    /*========================================4.角色：添加角色；========================================*/

    /**
     * 角色记录添加接口；
     *
     * @param role
     * @return
     */
    @RequestMapping("save")
    @ResponseBody
    public RespBean saveRole(Role role) {
        roleService.saveRole(role);
        return RespBean.success("角色添加成功!");
    }

    /*========================================5.角色：修改角色；========================================*/

    /**
     * 角色记录更新接口
     *
     * @param role
     * @return
     */
    @RequestMapping("update")
    @ResponseBody
    public RespBean updateRole(Role role) {
        roleService.updateRole(role);
        return RespBean.success("角色修改成功!");
    }
    /*========================================6.角色：删除角色；========================================*/

    /**
     * 角色记录删除接口；
     *
     * @param id
     * @return
     */
    @RequestMapping("delete")
    @ResponseBody
    public RespBean deleteRole(Integer id) {
        roleService.deleteRole(id);
        return RespBean.success("角色删除成功!");
    }

    /*========================================7.角色：查询所有角色；=====================================*/

    /**
     * 1.权限添加页面；
     *
     * @param userId
     * @return
     */
    @RequestMapping("queryAllRoles")
    @ResponseBody
    public List<Map<String, Object>> queryAllRoles(Integer userId) {
        List<Map<String,Object>> list = new ArrayList<>();
        list=roleService.queryAllRoles(userId);
        return list;
    }

    /*========================================8.权限：权限添加页面；=====================================*/

    /**
     * 权限添加页面
     *
     * @param roleId
     * @param model
     * @return
     */
    @RequestMapping("toAddGrantPage")
    public String toAddGrantPage(Integer roleId, Model model) {
        model.addAttribute("roleId", roleId);
        return "role/grant";
    }
    /*========================================9.权限：角色授权接口；=====================================*/

    /**
     * 角色授权接口
     *
     * @param roleId
     * @param mids
     * @return
     */
    @RequestMapping("addGrant")
    @ResponseBody
    public RespBean addGrant(Integer roleId, Integer[] mids) {
        roleService.addGrant(roleId, mids);
        return RespBean.success("角色记录授权成功!");
    }
}
