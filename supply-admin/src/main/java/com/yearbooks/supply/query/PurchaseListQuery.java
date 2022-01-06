package com.yearbooks.supply.query;

import lombok.Data;

import javax.print.attribute.IntegerSyntax;

/**
 * @author zhangdouyun
 * @version 1.0
 * @className PurchaseListQuery
 * @description TODO
 * @since 2021-12-23 21:44
 */
@Data
public class PurchaseListQuery extends BaseQuery {

    private String purchaseNumber;
    private Integer supplierId;
    private Integer state;
    private String startDate;
    private String endDate;

}
