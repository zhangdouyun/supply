package com.yearbooks.supply.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yearbooks.supply.pojo.SaleListGoods;
import com.yearbooks.supply.mapper.SaleListGoodsMapper;
import com.yearbooks.supply.service.ISaleListGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 销售单商品表 服务实现类
 * </p>
 *
 * @author zhangDouYun
 * @since 2021-12-28
 */
@Service
public class SaleListGoodsServiceImpl extends ServiceImpl<SaleListGoodsMapper, SaleListGoods> implements ISaleListGoodsService {

    @Override
    public Integer getSaleTotalByGoodsId(Integer id) {
        SaleListGoods saleListGoods = this.getOne(new QueryWrapper<SaleListGoods>().select("sum(num) as num").eq("goods_id", id));
        return null== saleListGoods?0:saleListGoods.getNum();
    }
}
