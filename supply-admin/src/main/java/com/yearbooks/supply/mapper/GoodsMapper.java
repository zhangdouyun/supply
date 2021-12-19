package com.yearbooks.supply.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yearbooks.supply.pojo.Goods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yearbooks.supply.query.GoodsQuery;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 商品表 Mapper 接口
 * </p>
 *
 * @author zhangDouYun
 * @since 2021-12-10
 */
public interface GoodsMapper extends BaseMapper<Goods> {

    IPage<Goods> queryGoodsParams(IPage<Goods> page, @Param("goodsQuery") GoodsQuery goodsQuery);

    Goods getGoodsInfoById(Integer gid);

    IPage<Goods> queryGoodsByParams(@Param("page") IPage<Goods> page, @Param("goodsQuery") GoodsQuery goodsQuery);
}



