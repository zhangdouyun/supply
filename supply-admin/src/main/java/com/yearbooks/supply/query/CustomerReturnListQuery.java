package com.yearbooks.supply.query;

import lombok.Data;

import java.security.Principal;

/**
 * @author zhangdouyun
 * @version 1.0
 * @className CustomerReturnListQuery
 * @description TODO
 * @since 2022-01-09 16:11
 */
@Data
public class CustomerReturnListQuery extends BaseQuery {

    //退货单号；
    private String customerReturnNumber;

    //客户名称;
    private String customerId;

    //是否付款;
    private Integer state;

    //开始时间；
    private String startDate;

    //结束时间；
    private String endDate;
}
