package com.yearbooks.supply.service;

import com.yearbooks.supply.pojo.ReturnListGoods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yearbooks.supply.query.ReturnListGoodsQuery;

import java.util.Map;

/**
 * <p>
 * 退货单商品表 服务类
 * </p>
 *
 * @author zhangDouYun
 * @since 2021-12-25
 */
public interface IReturnListGoodsService extends IService<ReturnListGoods> {

    Map<String, Object> returnListGoodsList(ReturnListGoodsQuery returnListGoodsQuery);
}
