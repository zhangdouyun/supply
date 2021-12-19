package com.yearbooks.supply.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yearbooks.supply.pojo.Customer;
import com.yearbooks.supply.mapper.CustomerMapper;
import com.yearbooks.supply.query.CustomerQuery;
import com.yearbooks.supply.service.ICustomerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yearbooks.supply.utils.AssertUtil;
import com.yearbooks.supply.utils.PageResultUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Map;
import java.util.spi.CurrencyNameProvider;

/**
 * <p>
 * 客户表 服务实现类
 * </p>
 *
 * @author zhangDouYun
 * @since 2021-12-07
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements ICustomerService {

    @Override
    public Map<String, Object> customerList(CustomerQuery customerQuery) {
        IPage<Customer> page = new Page<Customer>(customerQuery.getPage(),customerQuery.getLimit());
        QueryWrapper<Customer> queryWrapper = new QueryWrapper<Customer>();
        queryWrapper.eq("is_del",0);
        if (StringUtils.isNotBlank(customerQuery.getCustomerName())){
            queryWrapper.like("name",customerQuery.getCustomerName());
        }
        page= this.baseMapper.selectPage(page,queryWrapper);
        return PageResultUtil.getResult(page.getTotal(),page.getRecords());
    }

    @Override
    public Customer findCustomerByName(String name) {
        return this.getOne(new QueryWrapper<Customer>().eq("is_del",0).eq("name",name));
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void saveCustomer(Customer customer) {
        /**
         * 逻辑条件：
         * 供应商名称；联系人；联系电话；======非空
         * 名称不可重复；
         * isDel=0；
         */
        checkParams(customer.getName(), customer.getContact(),customer.getNumber());
        AssertUtil.isTrue(null!=this.findCustomerByName(customer.getName()),"客户已存在!");
        customer.setIsDel(0);
        AssertUtil.isTrue(!(this.save(customer)),"记录添加失败!");
    }

    private void checkParams(String name, String contact, String number) {
        AssertUtil.isTrue(StringUtils.isBlank(name),"请输入供应商名称!");
        AssertUtil.isTrue(StringUtils.isBlank(contact),"请输入联系人!");
        AssertUtil.isTrue(StringUtils.isBlank(number),"请输入联系电话!");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void updateCustomer(Customer customer) {
        AssertUtil.isTrue(null==this.getById(customer.getId()),"请选择客户记录！");
        checkParams(customer.getName(),customer.getContact(),customer.getNumber());
        Customer temp = this.findCustomerByName(customer.getName());
        AssertUtil.isTrue(null!=temp&&!(temp.getId().equals(customer.getId())),"客户已存在!");
        AssertUtil.isTrue(!(this.updateById(customer)),"记录更新失败!");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void deleteCustomer(Integer[] ids) {
        AssertUtil.isTrue(null==ids||ids.length==0,"请选择待删除的记录!");
        ArrayList<Customer> customersList = new ArrayList<>();
        for (Integer id : ids) {
            Customer temp = this.getById(id);
            temp.setIsDel(1);
            customersList.add(temp);
        }
        AssertUtil.isTrue(!(this.updateBatchById(customersList)),"记录删除失败!");
    }
}
