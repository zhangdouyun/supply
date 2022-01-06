package com.yearbooks.supply.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yearbooks.supply.pojo.ReturnList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yearbooks.supply.query.ReturnListQuery;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 退货单表 Mapper 接口
 * </p>
 *
 * @author zhangDouYun
 * @since 2021-12-25
 */
public interface ReturnListMapper extends BaseMapper<ReturnList> {

    String getNextReturnNumber();

    IPage<ReturnList> returnList(IPage<ReturnList> page, @Param("returnListQuery") ReturnListQuery returnListQuery);
}
