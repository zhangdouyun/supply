package com.yearbooks.supply.controller;


import com.fasterxml.jackson.databind.util.TypeKey;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.yearbooks.supply.model.RespBean;
import com.yearbooks.supply.pojo.DamageList;
import com.yearbooks.supply.pojo.DamageListGoods;
import com.yearbooks.supply.query.DamageListQuery;
import com.yearbooks.supply.service.IDamageListService;
import com.yearbooks.supply.service.IUserService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 报损单表 前端控制器
 * </p>
 *
 * @author zhangDouYun
 * @since 2022-01-10
 */
@Controller
@RequestMapping("/damage")
public class DamageListController {

    @Resource
    private IDamageListService damageListService;

    @Resource
    private IUserService userService;

    /**
     * 商品报损主页面
     * @return
     */
    @RequestMapping("index")
    public String index(Model model){
        model.addAttribute("damageNumber",damageListService.getNextDamageNumber());
        return "damage/damage";
    }

    /**
     * 添加报损单；
     * @param damageList
     * @param goodsJson
     * @param principal
     * @return
     */
    @RequestMapping("save")
    @ResponseBody
    public RespBean save(DamageList damageList, String goodsJson, Principal principal){
        String userName = principal.getName();
        damageList.setUserId( userService.findUserByName(userName).getId());
        Gson gson = new Gson();
        List<DamageListGoods> dlgList = gson.fromJson(goodsJson, new TypeToken<List<DamageListGoods>>() {}.getType());
        damageListService.saveDamageList(damageList,dlgList);
        return RespBean.success("商品报损出库成功!");
    }

    /**
     * 报损单查询
     * @param damageListQuery
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> damageList(DamageListQuery damageListQuery){
        return damageListService.damageList(damageListQuery);
    }



}
