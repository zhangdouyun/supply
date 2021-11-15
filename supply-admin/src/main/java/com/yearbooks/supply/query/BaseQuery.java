package com.yearbooks.supply.query;

import lombok.Data;

/**
 * @author zhangdouyun
 * @version 1.0
 * @className BaseQuery
 * @description TODO
 * @since 2021-11-06 21:11
 */
@Data
public class BaseQuery {
    private Integer page=1;
    private Integer limit=10;
}
