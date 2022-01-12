package com.yearbooks.supply.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yearbooks.supply.pojo.SaleListGoods;
import com.yearbooks.supply.mapper.SaleListGoodsMapper;
import com.yearbooks.supply.query.SaleListGoodsQuery;
import com.yearbooks.supply.service.ISaleListGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yearbooks.supply.utils.PageResultUtil;
import org.springframework.stereotype.Service;

import java.util.Map;

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
        return null == saleListGoods ? 0 : saleListGoods.getNum();
    }

    @Override
    public Map<String, Object> saleListGoodsList(SaleListGoodsQuery saleListGoodsQuery) {
        IPage<SaleListGoods> page = new Page<SaleListGoods>(saleListGoodsQuery.getPage(), saleListGoodsQuery.getLimit());
        QueryWrapper<SaleListGoods> queryWrapper = new QueryWrapper<>();
        if (null != saleListGoodsQuery.getSaleListId()) {
            queryWrapper.eq("sale_list_id", saleListGoodsQuery.getSaleListId());
        }
        page = this.baseMapper.selectPage(page, queryWrapper);
        return PageResultUtil.getResult(page.getTotal(),page.getRecords());
    }
}
