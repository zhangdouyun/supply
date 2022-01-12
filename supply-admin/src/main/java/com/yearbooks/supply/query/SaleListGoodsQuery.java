package com.yearbooks.supply.query;

import lombok.Data;

/**
 * @author zhangdouyun
 * @version 1.0
 * @className SaleListGoodsQuery
 * @description TODO
 * @since 2022-01-06 20:46
 */
@Data
public class SaleListGoodsQuery extends BaseQuery{
    private Integer saleListId;/*注意：此处saleListId与sale.js中的saleListId相互一致*/
}
