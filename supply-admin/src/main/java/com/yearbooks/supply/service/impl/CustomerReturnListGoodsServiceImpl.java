package com.yearbooks.supply.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yearbooks.supply.pojo.CustomerReturnListGoods;
import com.yearbooks.supply.mapper.CustomerReturnListGoodsMapper;
import com.yearbooks.supply.query.CustomerReturnListGoodsQuery;
import com.yearbooks.supply.service.ICustomerReturnListGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yearbooks.supply.utils.PageResultUtil;
import org.springframework.stereotype.Service;

import java.util.Map;

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

    @Override
    public Map<String, Object> customerReturnListGoods(CustomerReturnListGoodsQuery customerReturnListGoodsQuery) {
        IPage<CustomerReturnListGoods> page = new Page<CustomerReturnListGoods>(customerReturnListGoodsQuery.getPage(), customerReturnListGoodsQuery.getLimit());
        QueryWrapper<CustomerReturnListGoods> queryWrapper = new QueryWrapper<>();
        if (null != customerReturnListGoodsQuery.getCustomerReturnListId()){
            queryWrapper.eq("customer_return_list_id",customerReturnListGoodsQuery.getCustomerReturnListId());
        }
        page = this.baseMapper.selectPage(page,queryWrapper);
        return PageResultUtil.getResult(page.getTotal(),page.getRecords());
    }
}
