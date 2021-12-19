package com.yearbooks.supply.controller;


import com.yearbooks.supply.dto.TreeDto;
import com.yearbooks.supply.model.RespBean;
import com.yearbooks.supply.pojo.GoodsType;
import com.yearbooks.supply.service.IGoodsTypeService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品类别表 前端控制器
 * </p>
 *
 * @author zhangDouYun
 * @since 2021-12-10
 */
@Controller
@RequestMapping("/goodsType")
public class GoodsTypeController {

    @Resource
    private IGoodsTypeService goodsTypeService;

    /**
     * 查询所有商品类型；
     * @return
     */
    @RequestMapping("queryAllGoodsTypes")
    @ResponseBody
    public List<TreeDto> queryAllGoodsTypes(){
        return goodsTypeService.queryAllGoodsTypes();
    }

    /**
     * 商品类别展示页
     * @return
     */
    @RequestMapping("index")
    public String index(){
        return "goodsType/goods_type";
    }

    /**
     * 菜单列表查询接口
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> goodsTypeList(){
        return goodsTypeService.goodsTypeList();
    }

    /**
     *
     * @param pId
     * @param model
     * @return
     */
    @RequestMapping("addGoodsTypePage")//*.js 接口；
    public String addGoodsTypePage(Integer pId, Model model){
        model.addAttribute("pId",pId);
        return "goodsType/add";//*.ftl 网页；
    }

    /**
     * 添加商品类别
     * @param goodsTpye
     * @return
     */
    @RequestMapping("save")
    @ResponseBody
    public RespBean saveGoodsType(GoodsType goodsTpye){
        goodsTypeService.saveGoodsType(goodsTpye);
        return RespBean.success("商品类别添加成功!");
    }

    /**
     * 删除商品类别
     * @param id
     * @return
     */
    @RequestMapping("delete")
    @ResponseBody
    public RespBean deleteGoodsType(Integer id){
        goodsTypeService.deleteGoodsType(id);
        return RespBean.success("商品类别删除成功!");
    }
}
