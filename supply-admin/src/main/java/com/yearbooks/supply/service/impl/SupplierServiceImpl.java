package com.yearbooks.supply.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yearbooks.supply.pojo.Supplier;
import com.yearbooks.supply.mapper.SupplierMapper;
import com.yearbooks.supply.query.SupplierQuery;
import com.yearbooks.supply.service.ISupplierService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yearbooks.supply.utils.AssertUtil;
import com.yearbooks.supply.utils.PageResultUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 供应商表 服务实现类
 * </p>
 *
 * @author zhangDouYun
 * @since 2021-12-06
 */
@Service
public class SupplierServiceImpl extends ServiceImpl<SupplierMapper, Supplier> implements ISupplierService {

    @Override
    public Map<String, Object> supplierList(SupplierQuery supplierQuery) {
        IPage<Supplier> page = new Page<Supplier>(supplierQuery.getPage(), supplierQuery.getLimit());
        QueryWrapper<Supplier> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_del", 0);
        if (StringUtils.isNotBlank(supplierQuery.getSupplierName())) {
            queryWrapper.like("name", supplierQuery.getSupplierName());
        }
        page = this.baseMapper.selectPage(page, queryWrapper);
        return PageResultUtil.getResult(page.getTotal(), page.getRecords());
    }

    @Override
    public Supplier findSupplierByName(String name) {
        return this.getOne(new QueryWrapper<Supplier>().eq("is_del", 0).eq("name", name));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void saveSupplier(Supplier supplier) {

        /*供应商名称：1.不能为空；2.联系人不能为空；3.联系电话不能为空；4.名称不可能重复；5.isDel=0*/
        checkParams(supplier.getName(), supplier.getContact(), supplier.getNumber());
        AssertUtil.isTrue(null != this.findSupplierByName(supplier.getName()), "供应商已经存在!");
        supplier.setIsDel(0);
        AssertUtil.isTrue(!(this.save(supplier)), "记录添加失败!");

    }

    private void checkParams(String name, String contact, String number) {

        AssertUtil.isTrue(StringUtils.isBlank(name), "请输入供应商名称！");
        AssertUtil.isTrue(StringUtils.isBlank(contact), "请输入联系人!");
        AssertUtil.isTrue(StringUtils.isBlank(number), "请输入联系方式!");

    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void updateSupplier(Supplier supplier) {
        AssertUtil.isTrue(null == this.getById(supplier.getId()), "请选择供应商记录!");
        checkParams(supplier.getName(), supplier.getContact(), supplier.getNumber());
        Supplier temp = this.findSupplierByName(supplier.getName());
        AssertUtil.isTrue(null != temp && !(temp.getId().equals(supplier.getId())), "供应商已经存在!");
        AssertUtil.isTrue(!(this.updateById(supplier)), "记录更新已经失败!");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void deleteSupplier(Integer[] ids) {
        AssertUtil.isTrue(null==ids||ids.length==0,"请删除待删除的记录id");
        List<Supplier> supplierList = new ArrayList<Supplier>();
        for (Integer id : ids) {
            Supplier temp = this.getById(id);
            temp.setIsDel(1);
            supplierList.add(temp);
        }
        AssertUtil.isTrue(!(this.updateBatchById(supplierList)),"记录删除失败!");
    }


}
