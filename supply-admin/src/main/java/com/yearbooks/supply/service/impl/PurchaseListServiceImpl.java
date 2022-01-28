package com.yearbooks.supply.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yearbooks.supply.mapper.PurchaseListMapper;
import com.yearbooks.supply.model.CountResultModel;
import com.yearbooks.supply.pojo.Goods;
import com.yearbooks.supply.pojo.PurchaseList;
import com.yearbooks.supply.pojo.PurchaseListGoods;
import com.yearbooks.supply.query.PurchaseListGoodsQuery;
import com.yearbooks.supply.query.PurchaseListQuery;
import com.yearbooks.supply.service.IGoodsService;
import com.yearbooks.supply.service.IGoodsTypeService;
import com.yearbooks.supply.service.IPurchaseListGoodsService;
import com.yearbooks.supply.service.IPurchaseListService;
import com.yearbooks.supply.utils.AssertUtil;
import com.yearbooks.supply.utils.DateUtil;
import com.yearbooks.supply.utils.PageResultUtil;
import com.yearbooks.supply.utils.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 进货单 服务实现类
 * </p>
 *
 * @author zhangDouYun
 * @since 2021-12-18
 */
@Service
public class PurchaseListServiceImpl extends ServiceImpl<PurchaseListMapper, PurchaseList> implements IPurchaseListService {

    @Resource
    private IPurchaseListGoodsService purchaseListGoodsService;

    @Resource
    private IGoodsService goodsService;

    @Resource
    private IGoodsTypeService goodsTypeService;

    @Override
    public String getNextPurchaseNumber() {
        //JH20210101000x--生成该单位；
        try {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("JH");
            stringBuffer.append(DateUtil.getCurrentDateStr());
            //stringBuffer.append("-");
            String purchaseNumber = this.baseMapper.getNextPurchaseNumber();
            if (null != purchaseNumber) {
                stringBuffer.append(StringUtil.formatCode(purchaseNumber));
            } else {
                stringBuffer.append("0001");
            }
            return stringBuffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return " ";
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void savePurchaseList(PurchaseList purchaseList, List<PurchaseListGoods> plgList) {
        AssertUtil.isTrue(!(this.save(purchaseList)), "记录添加失败!");
        PurchaseList temp = this.getOne(new QueryWrapper<PurchaseList>().eq("purchase_number", purchaseList.getPurchaseNumber()));
        plgList.forEach(plg -> {
            plg.setPurchaseListId(temp.getId());
            /*更新商品数量信息*/
            Goods goods = goodsService.getById(plg.getGoodsId());
            goods.setInventoryQuantity(goods.getInventoryQuantity() + plg.getNum());
            goods.setLastPurchasingPrice(plg.getPrice());
            goods.setState(2);
            goodsService.updateById(goods);
        });
        AssertUtil.isTrue(!(purchaseListGoodsService.saveBatch(plgList)), "记录添加失败!");
    }

    @Override
    public Map<String, Object> purchaseList(PurchaseListQuery purchaseListQuery) {
        IPage<PurchaseList> page = new Page<PurchaseList>(purchaseListQuery.getPage(), purchaseListQuery.getLimit());
        page = this.baseMapper.purchaseList(page, purchaseListQuery);
        return PageResultUtil.getResult(page.getTotal(), page.getRecords());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deletePurchaseList(Integer id) {
        /**
         * 1.进货单商品记录删除；
         * 2.进货单记录删除；
         */
        AssertUtil.isTrue(!(purchaseListGoodsService.remove(new QueryWrapper<PurchaseListGoods>()
                        .eq("purchase_list_id", id))),
                "记录删除失败!");
        AssertUtil.isTrue(!(this.removeById(id)), "记录删除失败!");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updatePurchaseListState(Integer id) {
        PurchaseList purchaseList = this.getById(id);
        AssertUtil.isTrue(null == purchaseList, "待结算的记录不存在!");
        AssertUtil.isTrue(purchaseList.getState() == 1, "该记录已经支付!");
        purchaseList.setState(1);
        AssertUtil.isTrue(!(this.updateById(purchaseList)), "记录结算失败!");
    }

    @Override
    public Map<String, Object> countPurchase(PurchaseListQuery purchaseListQuery) {
        /**
         * 1.分页查询：A.查总数；B.查当前列表页。
         */
        if (null != purchaseListQuery.getTypeId()) {
            List<Integer> typeIds = goodsTypeService.queryAllSubTypeIdsByTypeId(purchaseListQuery.getTypeId());
            purchaseListQuery.setTypeIds(typeIds);
        }
        /**
         * page:1-->0;2-->10;3-->20;
         */
        purchaseListQuery.setIndex((purchaseListQuery.getPage() - 1) * purchaseListQuery.getLimit());
        Long count = this.baseMapper.countPurchaseTotal(purchaseListQuery);
        List<CountResultModel> list = this.baseMapper.countPurchaseList(purchaseListQuery);
        return PageResultUtil.getResult(count, list);
    }
}
