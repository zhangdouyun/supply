package com.yearbooks.supply.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yearbooks.supply.pojo.Goods;
import com.yearbooks.supply.pojo.OverflowList;
import com.yearbooks.supply.mapper.OverflowListMapper;
import com.yearbooks.supply.pojo.OverflowListGoods;
import com.yearbooks.supply.query.OverFlowListQuery;
import com.yearbooks.supply.service.IGoodsService;
import com.yearbooks.supply.service.IOverflowListGoodsService;
import com.yearbooks.supply.service.IOverflowListService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yearbooks.supply.utils.AssertUtil;
import com.yearbooks.supply.utils.DateUtil;
import com.yearbooks.supply.utils.PageResultUtil;
import com.yearbooks.supply.utils.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 报溢单表 服务实现类
 * </p>
 *
 * @author zhangDouYun
 * @since 2022-01-10
 */
@Service
public class OverflowListServiceImpl extends ServiceImpl<OverflowListMapper, OverflowList> implements IOverflowListService {

    @Resource
    private IGoodsService goodsService;

    @Resource
    private IOverflowListGoodsService overflowListGoodsService;

    @Override
    public String getNextOverFlowNumber() {
        try {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("BY");
            stringBuffer.append(DateUtil.getCurrentDateStr());
            String overFlowNumber = this.baseMapper.getNextOverFlowNumber();
            if (null != overFlowNumber){
                stringBuffer.append(StringUtil.formatCode(overFlowNumber));
            } else {
                stringBuffer.append("0001");
            }
            return stringBuffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    @Override
    public void saveOverFlowList(OverflowList overflowList, List<OverflowListGoods> plgList) {
        AssertUtil.isTrue(!(this.save(overflowList)),"记录添加失败!");
        OverflowList temp = this.getOne(new QueryWrapper<OverflowList>().eq("overflow_number", overflowList.getOverflowNumber()));
        plgList.forEach(plg->{
            plg.setOverflowListId(temp.getId());
            Goods goods = goodsService.getById(plg.getGoodsId());
            goods.setInventoryQuantity(goods.getInventoryQuantity()+plg.getNum());
            goods.setState(2);
            AssertUtil.isTrue(!(goodsService.updateById(goods)),"记录添加失败!");
            AssertUtil.isTrue(!(overflowListGoodsService.save(plg)),"记录添加失败!");
        });
    }

    @Override
    public Map<String, Object> overFlowList(OverFlowListQuery overFlowListQuery) {
        IPage<OverflowList> page = new Page<OverflowList>(overFlowListQuery.getPage(), overFlowListQuery.getLimit());
        page = this.baseMapper.overFlowList(page,overFlowListQuery);
        return PageResultUtil.getResult(page.getTotal(),page.getRecords());
    }
}
