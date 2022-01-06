package com.yearbooks.supply.service;

import com.yearbooks.supply.pojo.PurchaseListGoods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yearbooks.supply.query.PurchaseListGoodsQuery;

import java.util.Map;

/**
 * <p>
 * 进货单商品表 服务类
 * </p>
 *
 * @author zhangDouYun
 * @since 2021-12-18
 */
public interface IPurchaseListGoodsService extends IService<PurchaseListGoods> {


    Map<String, Object> purchaseListGoodsList(PurchaseListGoodsQuery purchaseListGoodsQuery);
}
