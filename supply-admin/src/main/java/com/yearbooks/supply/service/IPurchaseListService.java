package com.yearbooks.supply.service;

import com.yearbooks.supply.pojo.PurchaseList;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yearbooks.supply.pojo.PurchaseListGoods;
import com.yearbooks.supply.query.PurchaseListGoodsQuery;
import com.yearbooks.supply.query.PurchaseListQuery;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 进货单 服务类
 * </p>
 *
 * @author zhangDouYun
 * @since 2021-12-18
 */
public interface IPurchaseListService extends IService<PurchaseList> {

    String getNextPurchaseNumber();

    void savePurchaseList(PurchaseList purchaseList, List<PurchaseListGoods> plgList);

    Map<String, Object> purchaseList(PurchaseListQuery purchaseListQuery);

    void deletePurchaseList(Integer id);
}
