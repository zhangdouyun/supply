package com.yearbooks.supply.service;

import com.yearbooks.supply.pojo.OverflowListGoods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yearbooks.supply.query.OverFlowListGoodsQuery;

import java.util.Map;

/**
 * <p>
 * 报溢单商品表 服务类
 * </p>
 *
 * @author zhangDouYun
 * @since 2022-01-10
 */
public interface IOverflowListGoodsService extends IService<OverflowListGoods> {

    Map<String, Object> overflowListGoodsListGoods(OverFlowListGoodsQuery overFlowListGoodsQuery);
}
