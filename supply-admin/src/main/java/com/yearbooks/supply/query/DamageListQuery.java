package com.yearbooks.supply.query;

import lombok.Data;

/**
 * @author zhangdouyun
 * @version 1.0
 * @className DamageListQuery
 * @description TODO
 * @since 2022-01-11 22:36
 */
@Data
public class DamageListQuery extends BaseQuery {

    private String startDate;
    private String endDate;
}
