package com.yearbooks.supply.service;

import com.yearbooks.supply.dto.TreeDto;
import com.yearbooks.supply.pojo.GoodsType;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品类别表 服务类
 * </p>
 *
 * @author zhangDouYun
 * @since 2021-12-10
 */
public interface IGoodsTypeService extends IService<GoodsType> {

    List<TreeDto> queryAllGoodsTypes();

    List<Integer> queryAllSubTypeIdsByTypeId(Integer typeId);

    Map<String, Object> goodsTypeList();

    void saveGoodsType(GoodsType goodsTpye);

    void deleteGoodsType(Integer id);
}
