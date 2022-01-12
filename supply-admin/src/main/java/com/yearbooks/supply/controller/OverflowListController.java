package com.yearbooks.supply.controller;


import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.yearbooks.supply.model.RespBean;
import com.yearbooks.supply.pojo.OverflowList;
import com.yearbooks.supply.pojo.OverflowListGoods;
import com.yearbooks.supply.query.OverFlowListQuery;
import com.yearbooks.supply.service.IGoodsService;
import com.yearbooks.supply.service.IOverflowListGoodsService;
import com.yearbooks.supply.service.IOverflowListService;
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
 * 报溢单表 前端控制器
 * </p>
 *
 * @author zhangDouYun
 * @since 2022-01-10
 */
@Controller
@RequestMapping("/overflow")
public class OverflowListController {

    @Resource
    private IGoodsService goodsService;
    @Resource
    private IOverflowListService overflowListService;
    @Resource
    private IOverflowListGoodsService overflowListGoodsService;
    @Resource
    private IUserService userService;

    /**
     * 报溢单主页面；
     * @param model
     * @return
     */
    @RequestMapping("index")
    public String index(Model model){
        model.addAttribute("overflowNumber",overflowListService.getNextOverFlowNumber());
        return "overflow/overflow";
    }

    /**
     * 报溢商品入库保存。
     * @param overflowList
     * @param goodsJson
     * @param principal
     * @return
     */
    @RequestMapping("save")
    @ResponseBody
    public RespBean save(OverflowList overflowList, String goodsJson, Principal principal){
        String userName = principal.getName();
        overflowList.setUserId(userService.findUserByName(userName).getId());
        Gson gson = new Gson();
        List<OverflowListGoods> plgList = gson.fromJson(goodsJson, new TypeToken<List<OverflowListGoods>>() {}.getType());
        overflowListService.saveOverFlowList(overflowList,plgList);
        return RespBean.success("商品报溢入库成功!");
    }

    /**
     * 报溢单查询
     * @param overFlowListQuery
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> overFlowLList(OverFlowListQuery overFlowListQuery){
        return overflowListService.overFlowList(overFlowListQuery);
    }




    

}
