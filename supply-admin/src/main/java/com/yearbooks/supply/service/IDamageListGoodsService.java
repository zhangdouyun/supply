package com.yearbooks.supply.service;

import com.yearbooks.supply.pojo.DamageListGoods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yearbooks.supply.query.DamageListGoodsQuery;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * <p>
 * 报损单商品表 服务类
 * </p>
 *
 * @author zhangDouYun
 * @since 2022-01-10
 */
public interface IDamageListGoodsService extends IService<DamageListGoods> {

    Map<String, Object> damageListGoodsList( DamageListGoodsQuery damageListGoodsQuery);
}
