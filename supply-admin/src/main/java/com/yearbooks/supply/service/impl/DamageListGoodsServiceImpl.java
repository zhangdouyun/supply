package com.yearbooks.supply.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yearbooks.supply.pojo.DamageListGoods;
import com.yearbooks.supply.mapper.DamageListGoodsMapper;
import com.yearbooks.supply.query.DamageListGoodsQuery;
import com.yearbooks.supply.service.IDamageListGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yearbooks.supply.utils.PageResultUtil;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 报损单商品表 服务实现类
 * </p>
 *
 * @author zhangDouYun
 * @since 2022-01-10
 */
@Service
public class DamageListGoodsServiceImpl extends ServiceImpl<DamageListGoodsMapper, DamageListGoods> implements IDamageListGoodsService {

    @Override
    public Map<String, Object> damageListGoodsList(DamageListGoodsQuery damageListGoodsQuery) {
        IPage<DamageListGoods> page =  new Page<DamageListGoods>(damageListGoodsQuery.getPage(), damageListGoodsQuery.getLimit());
        QueryWrapper<DamageListGoods> queryWrapper = new QueryWrapper<>();
        if (null != damageListGoodsQuery.getDamageListId()){
            queryWrapper.eq("damage_list_id",damageListGoodsQuery.getDamageListId());
        }
        page = this.baseMapper.selectPage(page,queryWrapper);
        return PageResultUtil.getResult(page.getTotal(),page.getRecords());
    }
}
