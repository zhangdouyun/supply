package com.yearbooks.supply.query;

import lombok.Data;

import java.util.List;

/**
 * @author zhangdouyun
 * @version 1.0
 * @className UserQuery
 * @description TODO
 * @since 2021-11-06 21:15
 */
@Data
public class GoodsQuery extends BaseQuery {
    private String goodsName;
    private Integer typeId;
    private List<Integer> typeIds;
    //查询类型 区分库存量是否大于零；
    /**
     * 1.库存量=0；
     * 2.库存量大于0；
     */
    private Integer type;
}
