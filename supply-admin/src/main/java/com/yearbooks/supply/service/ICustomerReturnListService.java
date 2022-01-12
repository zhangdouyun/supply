package com.yearbooks.supply.service;

import com.yearbooks.supply.pojo.CustomerReturnList;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yearbooks.supply.pojo.CustomerReturnListGoods;
import com.yearbooks.supply.query.CustomerReturnListQuery;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 客户退货单表 服务类
 * </p>
 *
 * @author zhangDouYun
 * @since 2021-12-31
 */
public interface ICustomerReturnListService extends IService<CustomerReturnList> {

    String getNextCustomerReturnnumber();

    void saveCustomerReturnList(CustomerReturnList customerReturnList, List<CustomerReturnListGoods> slgList);

    Map<String, Object> customerReturnList(CustomerReturnListQuery customerReturnListQuery);

    void deleteCustomerReturnList(Integer id);
}
