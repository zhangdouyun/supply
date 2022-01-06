package com.yearbooks.supply.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yearbooks.supply.pojo.SaleList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yearbooks.supply.query.SaleListQuery;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 销售单表 Mapper 接口
 * </p>
 *
 * @author zhangDouYun
 * @since 2021-12-31
 */
public interface SaleListMapper extends BaseMapper<SaleList> {

    String getNextSaleNumber();

    IPage<SaleList> saleList(IPage<SaleList> page, @Param("saleListQuery") SaleListQuery saleListQuery);
}
