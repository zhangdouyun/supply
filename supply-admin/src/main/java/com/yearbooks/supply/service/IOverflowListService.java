package com.yearbooks.supply.service;

import com.yearbooks.supply.pojo.OverflowList;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yearbooks.supply.pojo.OverflowListGoods;
import com.yearbooks.supply.query.OverFlowListQuery;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 报溢单表 服务类
 * </p>
 *
 * @author zhangDouYun
 * @since 2022-01-10
 */
public interface IOverflowListService extends IService<OverflowList> {

    String getNextOverFlowNumber();

    void saveOverFlowList(OverflowList overflowList, List<OverflowListGoods> plgList);

    Map<String, Object> overFlowList(OverFlowListQuery overFlowListQuery);

}
