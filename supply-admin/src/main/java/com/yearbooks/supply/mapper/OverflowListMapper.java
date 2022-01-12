package com.yearbooks.supply.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yearbooks.supply.pojo.OverflowList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yearbooks.supply.query.OverFlowListQuery;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 报溢单表 Mapper 接口
 * </p>
 *
 * @author zhangDouYun
 * @since 2022-01-10
 */
public interface OverflowListMapper extends BaseMapper<OverflowList> {

    String getNextOverFlowNumber();

    IPage<OverflowList> overFlowList(IPage<OverflowList> page, @Param("overFlowListQuery") OverFlowListQuery overFlowListQuery);
}
