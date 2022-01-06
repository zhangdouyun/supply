package com.yearbooks.supply.query;

import lombok.Data;

/**
 * @author zhangdouyun
 * @version 1.0
 * @className ReturnListQuery
 * @description TODO
 * @since 2021-12-26 16:35
 */
@Data
public class ReturnListQuery extends BaseQuery {

    private String returnNumber;
    private Integer supplierId;
    private Integer state;
    private String startDate;
    private String endDate;
}

