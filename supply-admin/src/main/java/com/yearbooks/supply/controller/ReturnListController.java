package com.yearbooks.supply.controller;


import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.yearbooks.supply.model.RespBean;
import com.yearbooks.supply.pojo.ReturnList;
import com.yearbooks.supply.pojo.ReturnListGoods;
import com.yearbooks.supply.query.ReturnListQuery;
import com.yearbooks.supply.service.IReturnListService;
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
 * 退货单表 前端控制器
 * </p>
 *
 * @author zhangDouYun
 * @since 2021-12-25
 */
@Controller
@RequestMapping("/return")
public class ReturnListController {

    @Resource
    private IReturnListService returnListService;
    @Resource
    private IUserService userService;

    @RequestMapping("index")
    public String index(Model model){
        model.addAttribute("returnNumber",returnListService.getNextReturnNumber());
        return "return/return";
    }

    @RequestMapping("save")
    @ResponseBody
    public RespBean save(ReturnList returnList, String goodsJson, Principal principal){
        String userName = principal.getName();
        returnList.setUserId(userService.findUserByName(userName).getId());
        Gson gson = new Gson();
        List<ReturnListGoods> rlgList = gson.fromJson(goodsJson,new TypeToken<List<ReturnListGoods>>(){}.getType());
        returnListService.saveReturnList(returnList,rlgList);
        return RespBean.success("商品退货出库成功!");
    }

    /**
     * 退货单据查询页
     * @return
     */
    @RequestMapping("searchPage")
    public String searchPage(){
        return "return/return_search";
    }

    /**
     * 退货清单显示
     * @param returnListQuery
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> returnList(ReturnListQuery returnListQuery){
        return returnListService.returnList(returnListQuery);
    }

    /**
     * 删除退货单据；
     * @param id
     * @return
     */
    @RequestMapping("delete")
    @ResponseBody
    public RespBean delete(Integer id){
        returnListService.deleteReturnList(id);
        return RespBean.success("删除成功!");
    }
}
