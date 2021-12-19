package com.yearbooks.supply.service;

import com.yearbooks.supply.pojo.PurchaseList;
import com.baomidou.mybatisplus.extension.service.IService;

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
}
