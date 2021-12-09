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
import com.yearbooks.supply.utils.PageResultUtil;
import org.springframework.stereotype.Service;

import java.util.Map;

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
}
