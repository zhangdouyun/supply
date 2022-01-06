package com.yearbooks.supply.service;

import com.yearbooks.supply.pojo.SaleList;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yearbooks.supply.pojo.SaleListGoods;
import com.yearbooks.supply.query.SaleListQuery;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 销售单表 服务类
 * </p>
 *
 * @author zhangDouYun
 * @since 2021-12-31
 */
public interface ISaleListService extends IService<SaleList> {

    String getNextSaleNumber();

    void saveSaleList(SaleList saleList, List<SaleListGoods> slgList);

    Map<String, Object> saleList(SaleListQuery saleListQuery);
}
