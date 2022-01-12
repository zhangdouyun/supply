package com.yearbooks.supply.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yearbooks.supply.pojo.DamageList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yearbooks.supply.query.DamageListQuery;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 报损单表 Mapper 接口
 * </p>
 *
 * @author zhangDouYun
 * @since 2022-01-10
 */
public interface DamageListMapper extends BaseMapper<DamageList> {

    String getNextDamageNumber();

    IPage<DamageList> damageList(IPage<DamageList> page, @Param("damageListQuery") DamageListQuery damageListQuery);
}
