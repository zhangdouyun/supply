package com.yearbooks.supply.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yearbooks.supply.pojo.CustomerReturnListGoods;
import com.yearbooks.supply.mapper.CustomerReturnListGoodsMapper;
import com.yearbooks.supply.service.ICustomerReturnListGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 客户退货单商品表 服务实现类
 * </p>
 *
 * @author zhangDouYun
 * @since 2021-12-28
 */
@Service
public class CustomerReturnListGoodsServiceImpl extends ServiceImpl<CustomerReturnListGoodsMapper, CustomerReturnListGoods> implements ICustomerReturnListGoodsService {

    @Override
    public Integer getReturnTotalByGoodsId(Integer id) {
        CustomerReturnListGoods customerReturnListGoods = this.getOne(new QueryWrapper<CustomerReturnListGoods>().select("sum(num) as num").eq("goods_id", id));
        return null==customerReturnListGoods?0:customerReturnListGoods.getNum();
    }
}
