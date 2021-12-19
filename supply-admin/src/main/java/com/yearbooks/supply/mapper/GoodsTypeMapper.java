package com.yearbooks.supply.mapper;

import com.yearbooks.supply.dto.TreeDto;
import com.yearbooks.supply.pojo.GoodsType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 商品类别表 Mapper 接口
 * </p>
 *
 * @author zhangDouYun
 * @since 2021-12-10
 */
public interface GoodsTypeMapper extends BaseMapper<GoodsType> {

    List<TreeDto> queryAllGoodsTypes();
}
