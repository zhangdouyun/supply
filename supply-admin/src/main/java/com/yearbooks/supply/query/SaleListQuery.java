package com.yearbooks.supply.query;

import lombok.Data;

/**
 * @author zhangdouyun
 * @version 1.0
 * @className SaleListQuery
 * @description TODO
 * @since 2022-01-05 22:22
 */
@Data
public class SaleListQuery extends BaseQuery {
    private String saleNumber;
    private Integer customerId;
    private Integer state;
    private String startDate;
    private String endDate;

}
