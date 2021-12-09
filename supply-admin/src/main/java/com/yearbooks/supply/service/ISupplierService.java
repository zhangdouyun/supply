package com.yearbooks.supply.service;

import com.yearbooks.supply.pojo.Supplier;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yearbooks.supply.query.SupplierQuery;

import java.util.Map;

/**
 * <p>
 * 供应商表 服务类
 * </p>
 *
 * @author zhangDouYun
 * @since 2021-12-06
 */
public interface ISupplierService extends IService<Supplier> {

    Map<String, Object> supplierList(SupplierQuery supplierQuery);

    Supplier findSupplierByName(String name);

    void saveSupplier(Supplier supplier);

    void updateSupplier(Supplier supplier);

    void deleteSupplier(Integer[] ids);
}
