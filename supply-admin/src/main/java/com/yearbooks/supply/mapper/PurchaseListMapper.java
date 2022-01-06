package com.yearbooks.supply.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yearbooks.supply.pojo.PurchaseList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yearbooks.supply.query.PurchaseListQuery;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 进货单 Mapper 接口
 * </p>
 *
 * @author zhangDouYun
 * @since 2021-12-18
 */
public interface PurchaseListMapper extends BaseMapper<PurchaseList> {

    String getNextPurchaseNumber();

    IPage<PurchaseList> purchaseList( IPage<PurchaseList> page, @Param("purchaseListQuery") PurchaseListQuery purchaseListQuery);
}
