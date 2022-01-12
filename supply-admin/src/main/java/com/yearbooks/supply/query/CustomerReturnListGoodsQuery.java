package com.yearbooks.supply.query;

import lombok.Data;

/**
 * @author zhangdouyun
 * @version 1.0
 * @className CustomerReturnListGoodsQuery
 * @description TODO
 * @since 2022-01-09 18:33
 */
@Data
public class CustomerReturnListGoodsQuery extends BaseQuery{
    //退货单号
    private Integer customerReturnListId;
}
