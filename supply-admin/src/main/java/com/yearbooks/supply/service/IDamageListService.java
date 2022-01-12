package com.yearbooks.supply.service;

import com.yearbooks.supply.pojo.DamageList;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yearbooks.supply.pojo.DamageListGoods;
import com.yearbooks.supply.query.DamageListQuery;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 报损单表 服务类
 * </p>
 *
 * @author zhangDouYun
 * @since 2022-01-10
 */
public interface IDamageListService extends IService<DamageList> {

    String getNextDamageNumber();

    void saveDamageList(DamageList damageList, List<DamageListGoods> dlgList);

    Map<String, Object> damageList(DamageListQuery damageListQuery);
}
