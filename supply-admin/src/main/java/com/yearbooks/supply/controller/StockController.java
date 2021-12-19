package com.yearbooks.supply.controller;

import com.yearbooks.supply.model.RespBean;
import com.yearbooks.supply.pojo.Goods;
import com.yearbooks.supply.query.GoodsQuery;
import com.yearbooks.supply.service.IGoodsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author zhangdouyun
 * @version 1.0
 * @className StockController
 * @description TODO
 * @since 2021-12-15 21:28
 */

@Controller
@RequestMapping("stock")
public class StockController {

    @Resource
    private IGoodsService goodsService;

    /**
     * 期初库存主页
     * @return
     */
    @RequestMapping("index")
    public String index(){
        return "stock/stock";
    }

    /**
     * 查询库存量为0的商品记录；
     * @param goodsQuery
     * @return
     */
    @RequestMapping("listNoInventoryQuantity")
    @ResponseBody
    public Map<String,Object> listNoInventoryQuantity(GoodsQuery goodsQuery){
        goodsQuery.setType(1);
        return goodsService.goodsList(goodsQuery);
    }

    /**
     * 查询存货大于0；
     * @param goodsQuery
     * @return
     */
    @RequestMapping("listHasInventoryQuantity")
    @ResponseBody
    public Map<String,Object> listHasInventoryQuantity(GoodsQuery goodsQuery){
        goodsQuery.setType(2);
        return goodsService.goodsList(goodsQuery);
    }

    /**
     * 更新存货信息页
     * @param gid
     * @param model
     * @return
     */
    @RequestMapping("toUpdateGoodsInfoPage")
    public String toUpdateGoodsInfoPage(Integer gid, Model model){
        model.addAttribute("goods",goodsService.getById(gid));
        return "stock/goods_update";
    }

    /**
     * 更新商品初始数量；
     * @param goods
     * @return
     */
    @RequestMapping("updateStock")
    @ResponseBody
    public RespBean updateStock(Goods goods){
        goodsService.updateStock(goods);
        return RespBean.success("商品记录更新成功!");
    }

    /**
     * 删除商品期初数据；
     * @param id
     * @return
     */
    @RequestMapping("deleteStock")
    @ResponseBody
    public RespBean deleteStock(Integer id){
        goodsService.deleteStock(id);
        return RespBean.success("商品期初记录删除成功!");
    }

}
