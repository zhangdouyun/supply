package com.yearbooks.supply.service;

import com.yearbooks.supply.pojo.Customer;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yearbooks.supply.query.CustomerQuery;

import java.util.Map;

/**
 * <p>
 * 客户表 服务类
 * </p>
 *
 * @author zhangDouYun
 * @since 2021-12-07
 */
public interface ICustomerService extends IService<Customer> {

    Map<String, Object> customerList(CustomerQuery customerQuery);

    Customer findCustomerByName(String name);

    void saveCustomer(Customer customer);

    void updateCustomer(Customer customer);

    void deleteCustomer(Integer[] ids);
}
