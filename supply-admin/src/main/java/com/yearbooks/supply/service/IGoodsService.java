package com.yearbooks.supply.service;

import com.yearbooks.supply.pojo.Goods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yearbooks.supply.query.GoodsQuery;

import java.util.Map;

/**
 * <p>
 * 商品表 服务类
 * </p>
 *
 * @author zhangDouYun
 * @since 2021-12-10
 */
public interface IGoodsService extends IService<Goods> {

    Map<String, Object> goodsList(GoodsQuery goodsQuery);

    String genGoodsCode();

    void saveGoods(Goods goods);

    void updateGoods(Goods goods);

    void deleteGoods(Integer id);

    void updateStock(Goods goods);

    void deleteStock(Integer id);

    Goods getGoodsInfoById(Integer gid);

}
