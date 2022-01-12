package com.yearbooks.supply.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yearbooks.supply.pojo.DamageList;
import com.yearbooks.supply.mapper.DamageListMapper;
import com.yearbooks.supply.pojo.DamageListGoods;
import com.yearbooks.supply.pojo.Goods;
import com.yearbooks.supply.query.DamageListQuery;
import com.yearbooks.supply.service.IDamageListGoodsService;
import com.yearbooks.supply.service.IDamageListService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yearbooks.supply.service.IGoodsService;
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
 * 报损单表 服务实现类
 * </p>
 *
 * @author zhangDouYun
 * @since 2022-01-10
 */
@Service
public class DamageListServiceImpl extends ServiceImpl<DamageListMapper, DamageList> implements IDamageListService {

    @Resource
    private IGoodsService goodsService;
    @Resource
    private IDamageListGoodsService damageListGoodsService;

    @Override
    public String getNextDamageNumber() {
        try {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("BX");
            stringBuffer.append(DateUtil.getCurrentDateStr());
            String damageNumber = this.baseMapper.getNextDamageNumber();
            if (null != damageNumber) {
                stringBuffer.append(StringUtil.formatCode(damageNumber));
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
    public void saveDamageList(DamageList damageList, List<DamageListGoods> dlgList) {
        AssertUtil.isTrue(!(this.save(damageList)), "记录添加失败!");
        DamageList temp = this.getOne(new QueryWrapper<DamageList>().eq("damage_number", damageList.getDamageNumber()));
        dlgList.forEach(dlg -> {
            dlg.setDamageListId(temp.getId());
            Goods goods = goodsService.getById(dlg.getGoodsId());
            goods.setInventoryQuantity(goods.getInventoryQuantity() - dlg.getNum());
            goods.setState(2);
            AssertUtil.isTrue(!(goodsService.updateById(goods)), "记录添加失败!");
            AssertUtil.isTrue(!(damageListGoodsService.save(dlg)), "记录添加失败!");
        });
    }

    @Override
    public Map<String, Object> damageList(DamageListQuery damageListQuery) {
        IPage<DamageList> page = new Page<DamageList>(damageListQuery.getPage(), damageListQuery.getLimit());
        page = this.baseMapper.damageList(page,damageListQuery);
        return PageResultUtil.getResult(page.getTotal(),page.getRecords());
    }
}
