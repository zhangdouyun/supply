package com.yearbooks.supply.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zhangdouyun
 * @version 1.0
 * @className  MainController
 * @description TODO
 * @since 2021-10-25 23:11
 * 1.基础页面，未发现service注释注入。
 */

@Controller
class MainController {
    /**
     * 系统登录页
     * @return
     */
    @RequestMapping("index")
    public String index(){
        return "index";
    }

    /**
     * 系统主页面
     * @return
     */
    @RequestMapping("main")
    public String main(){
        return "main";
    }

    /**
     * 系统欢迎页
     * @return
     */
    @RequestMapping("welcome")
    public String welcome(){
        return "welcome";
    }

    /**
     * 用户退出，该模块最终由spring-security来完成；
     * @return
     */
    /*@RequestMapping("signout")
    public String signout(){
        return "redirect:index";
    }*/
}
