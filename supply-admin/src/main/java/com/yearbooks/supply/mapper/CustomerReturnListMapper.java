package com.yearbooks.supply.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yearbooks.supply.pojo.CustomerReturnList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yearbooks.supply.query.CustomerReturnListQuery;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 客户退货单表 Mapper 接口
 * </p>
 *
 * @author zhangDouYun
 * @since 2021-12-31
 */
public interface CustomerReturnListMapper extends BaseMapper<CustomerReturnList> {

    String getNextCustomerReturnNumber();

    IPage<CustomerReturnList> customerReturnList(IPage<CustomerReturnList> page, @Param("customerReturnListQuery") CustomerReturnListQuery customerReturnListQuery);
}
