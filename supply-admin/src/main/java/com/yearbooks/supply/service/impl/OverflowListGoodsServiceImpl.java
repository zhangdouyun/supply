package com.yearbooks.supply.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yearbooks.supply.pojo.OverflowListGoods;
import com.yearbooks.supply.mapper.OverflowListGoodsMapper;
import com.yearbooks.supply.query.OverFlowListGoodsQuery;
import com.yearbooks.supply.service.IOverflowListGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yearbooks.supply.utils.PageResultUtil;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 报溢单商品表 服务实现类
 * </p>
 *
 * @author zhangDouYun
 * @since 2022-01-10
 */
@Service
public class OverflowListGoodsServiceImpl extends ServiceImpl<OverflowListGoodsMapper, OverflowListGoods> implements IOverflowListGoodsService {

    @Override
    public Map<String, Object> overflowListGoodsListGoods(OverFlowListGoodsQuery overFlowListGoodsQuery) {
        IPage<OverflowListGoods> page = new Page<OverflowListGoods>(overFlowListGoodsQuery.getPage(), overFlowListGoodsQuery.getLimit());
        QueryWrapper<OverflowListGoods> queryWrapper = new QueryWrapper<>();
        if (null != overFlowListGoodsQuery.getOverflowListId()){
            queryWrapper.eq("overflow_list_id",overFlowListGoodsQuery.getOverflowListId());
        }
        page = this.baseMapper.selectPage(page,queryWrapper);
        return PageResultUtil.getResult(page.getTotal(),page.getRecords());
    }
}
