package com.yearbooks.supply.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yearbooks.supply.pojo.CustomerReturnList;
import com.yearbooks.supply.mapper.CustomerReturnListMapper;
import com.yearbooks.supply.pojo.CustomerReturnListGoods;
import com.yearbooks.supply.pojo.Goods;
import com.yearbooks.supply.query.CustomerReturnListQuery;
import com.yearbooks.supply.service.ICustomerReturnListGoodsService;
import com.yearbooks.supply.service.ICustomerReturnListService;
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
 * 客户退货单表 服务实现类
 * </p>
 *
 * @author zhangDouYun
 * @since 2021-12-31
 */
@Service
public class CustomerReturnListServiceImpl extends ServiceImpl<CustomerReturnListMapper, CustomerReturnList> implements ICustomerReturnListService {

    @Resource
    private IGoodsService goodsService;

    @Resource
    private ICustomerReturnListService customerReturnListService;

    @Resource
    private ICustomerReturnListGoodsService customerReturnListGoodsService;


    @Override
    public String getNextCustomerReturnnumber() {
        try {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("XT");
            stringBuffer.append(DateUtil.getCurrentDateStr());
            String customerReturnNumber = this.baseMapper.getNextCustomerReturnNumber();
            if (null !=customerReturnNumber){
                stringBuffer.append(StringUtil.formatCode(customerReturnNumber));
            }else {
                stringBuffer.append("0001");
            }
            return stringBuffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    @Override
    public void saveCustomerReturnList(CustomerReturnList customerReturnList, List<CustomerReturnListGoods> slgList) {
        AssertUtil.isTrue(!(this.save(customerReturnList)),"记录添加失败!");
        CustomerReturnList temp = this.getOne(new QueryWrapper<CustomerReturnList>().eq("customer_return_number", customerReturnList.getCustomerReturnNumber()));
        slgList.forEach(slg->{
            slg.setCustomerReturnListId(temp.getId());
            Goods goods = goodsService.getById(slg.getGoodsId());
            goods.setInventoryQuantity(goods.getInventoryQuantity()+slg.getNum());
            goods.setState(2);
            AssertUtil.isTrue(!(goodsService.updateById(goods)),"记录添加失败!");
            AssertUtil.isTrue(!(customerReturnListGoodsService.save(slg)),"记录添加失败!");
        });
    }

    @Override
    public Map<String, Object> customerReturnList(CustomerReturnListQuery customerReturnListQuery) {
        IPage<CustomerReturnList> page =  new Page<CustomerReturnList>(customerReturnListQuery.getPage(), customerReturnListQuery.getLimit());
        page = this.baseMapper.customerReturnList(page, customerReturnListQuery);
        return PageResultUtil.getResult(page.getTotal(),page.getRecords());
    }

    @Override
    public void deleteCustomerReturnList(Integer id) {
        /**
         * 1.删除销售单；
         * 2.删除销售单商品记录；
         */
        AssertUtil.isTrue(!(customerReturnListGoodsService.remove(new QueryWrapper<CustomerReturnListGoods>().eq("customer_return_list_id",id))),"删除失败!");
        AssertUtil.isTrue(!(this.removeById(id)),"删除失败!");
    }
}
