package com.yearbooks.supply.service;

import com.yearbooks.supply.pojo.ReturnList;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yearbooks.supply.pojo.ReturnListGoods;
import com.yearbooks.supply.query.ReturnListQuery;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 退货单表 服务类
 * </p>
 *
 * @author zhangDouYun
 * @since 2021-12-25
 */
public interface IReturnListService extends IService<ReturnList> {

    String getNextReturnNumber();

    void saveReturnList(ReturnList returnList, List<ReturnListGoods> rlgList);

    Map<String, Object> returnList(ReturnListQuery returnListQuery);

    void deleteReturnList(Integer id);
}
