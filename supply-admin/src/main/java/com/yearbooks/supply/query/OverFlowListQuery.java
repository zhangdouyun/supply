package com.yearbooks.supply.query;

import lombok.Data;

/**
 * @author zhangdouyun
 * @version 1.0
 * @className OverFlowListQuery
 * @description TODO
 * @since 2022-01-12 20:22
 */
@Data
public class OverFlowListQuery extends BaseQuery {

    private String startDate;
    private String endDate;
}
