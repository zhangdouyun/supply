package com.yearbooks.supply.service;

import com.yearbooks.supply.pojo.SaleListGoods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yearbooks.supply.query.SaleListGoodsQuery;

import java.util.Map;

/**
 * <p>
 * 销售单商品表 服务类
 * </p>
 *
 * @author zhangDouYun
 * @since 2021-12-28
 */
public interface ISaleListGoodsService extends IService<SaleListGoods> {

    Integer getSaleTotalByGoodsId(Integer id);

    Map<String, Object> saleListGoodsList(SaleListGoodsQuery saleListGoodsQuery);
}
