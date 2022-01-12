package com.yearbooks.supply.service;

import com.yearbooks.supply.pojo.CustomerReturnListGoods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yearbooks.supply.query.CustomerReturnListGoodsQuery;

import java.util.Map;

/**
 * <p>
 * 客户退货单商品表 服务类
 * </p>
 *
 * @author zhangDouYun
 * @since 2021-12-28
 */
public interface ICustomerReturnListGoodsService extends IService<CustomerReturnListGoods> {

    Integer getReturnTotalByGoodsId(Integer id);

    Map<String, Object> customerReturnListGoods(CustomerReturnListGoodsQuery customerReturnListGoodsQuery);

}
