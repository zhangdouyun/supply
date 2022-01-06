package com.yearbooks.supply.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yearbooks.supply.pojo.PurchaseListGoods;
import com.yearbooks.supply.mapper.PurchaseListGoodsMapper;
import com.yearbooks.supply.query.PurchaseListGoodsQuery;
import com.yearbooks.supply.service.IPurchaseListGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yearbooks.supply.utils.PageResultUtil;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 进货单商品表 服务实现类
 * </p>
 *
 * @author zhangDouYun
 * @since 2021-12-18
 */
@Service
public class PurchaseListGoodsServiceImpl extends ServiceImpl<PurchaseListGoodsMapper, PurchaseListGoods> implements IPurchaseListGoodsService {

    @Override
    public Map<String, Object> purchaseListGoodsList(PurchaseListGoodsQuery purchaseListGoodsQuery) {
        IPage<PurchaseListGoods> page = new Page<PurchaseListGoods>(purchaseListGoodsQuery.getPage(), purchaseListGoodsQuery.getLimit());
        QueryWrapper<PurchaseListGoods> queryWrapper = new QueryWrapper<PurchaseListGoods>();
        if (null != purchaseListGoodsQuery.getPurchaseListId()){
            queryWrapper.eq("purchase_list_id",purchaseListGoodsQuery.getPurchaseListId());
        }
        page = this.baseMapper.selectPage(page,queryWrapper);
        return PageResultUtil.getResult(page.getTotal(),page.getRecords());
    }
}
