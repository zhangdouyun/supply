package com.yearbooks.supply.mapper;

import com.yearbooks.supply.pojo.PurchaseList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

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
}
