package com.yearbooks.supply.controller;


import com.yearbooks.supply.model.RespBean;
import com.yearbooks.supply.pojo.User;
import com.yearbooks.supply.query.UserQuery;
import com.yearbooks.supply.service.IUserService;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.management.Query;
import java.awt.image.RescaleOp;
import java.security.Principal;
import java.util.Map;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author zhangDouYun
 * @since 2021-10-27
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService userService;

    /*@RequestMapping("login")//注意：此注解已经从前端前端数据取过来，交给后端。
    @ResponseBody
    public RespBean login(String userName, String password, HttpSession session) {
        User user = userService.login(userName, password);
        session.setAttribute("user", user);
        return RespBean.success("用户登录成功!");
    }*/

    /**
     * 用户信息设置页面
     *
     * @return
     */
    @RequestMapping("setting")
    public String setting(Principal principal, Model model) {
        User user = userService.findUserByName(principal.getName());
        model.addAttribute("user",user);
        System.out.println("获取更新信息:"+user.toString());
        return "user/setting";
    }

    /**
     * 用户信息更新
     * @param user
     * @return
     */
    @RequestMapping("updateUserInfo")//已经从前端获取信息
    @ResponseBody
    public RespBean updateUserInfo(User user) {
        System.out.println("user:"+user.toString());
        userService.updateUserInfo(user);
        return RespBean.success("用户信息更新成功!");
    }

    /**
     * 更新密码页面
     * @return
     */
    @RequestMapping("password")
    public String password() {
        return "user/password";
    }

    /**
     * 更新用户密码
     * @param principal
     * @param oldPassword
     * @param newPassword
     * @param confirmPassword
     * @return
     */
    @RequestMapping("updateUserPassword")
    @ResponseBody
    public RespBean updateUserPassword(Principal principal, String oldPassword, String newPassword, String confirmPassword) {
        //User user = (User) session.getAttribute("user");
        userService.updateUserPassword(principal.getName(), oldPassword, newPassword, confirmPassword);
        return RespBean.success("用户密码更新成功");
    }

    /*=====================================《1》用户篇：查询、添加、修改或删除用户======================================*/
    /**
     * 1.打开用户主页面
     * @return
     */
    @RequestMapping("index")//该处index与前端main.ftl中
    public String index(){
        return "user/user";
    }

    /**
     * 2.获取用户有效记录。
     * @param userQuery
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> userList(UserQuery userQuery){
        return userService.userList(userQuery);
    }

    /**
     * 3.打开增加用户或修改用户页面：
     * 总结：此时不涉及其他页面。只在controller页面体现。
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("addOrUpdateUserPage")//跟user.js页面的接口保持一致：/user/addOrUpdateUserPage。/user/与controller类的注释名称保持一致。
    public String addOrUpdatePage(Integer id,Model model){
        if (null!=id){
            model.addAttribute("user",userService.getById(id));
        }
        return "user/add_update";
    }

    /**
     * 4.添加用户；
     * @param user
     * @return
     */
    @RequestMapping("save")
    @ResponseBody
    public RespBean saveUser(User user){
        userService.saveUser(user);
        return RespBean.success("用户添加成功!");
    }

    /**
     * 5.更新用户；
     * @param user
     * @return
     */
    @RequestMapping("update")
    @ResponseBody
    public RespBean updateUser(User user){
        userService.updateUser(user);
        return RespBean.success("用户更新成功!");
    }

    /**
     * 6.删除用户
     * @param ids
     * @return
     */
    @RequestMapping("delete")
    @ResponseBody
    public RespBean deleteUser(Integer[] ids){
        userService.deleteUser(ids);
        return RespBean.success("用户删除成功!");
    }
}
