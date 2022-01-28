package com.yearbooks.supply.model;

import lombok.Data;

/**
 * @author zhangdouyun
 * @version 1.0
 * @className SaleCount
 * @description TODO
 * @since 2022-01-24 23:12
 */

@Data
public class SaleCount {
    private float amountCost;//成本总金额；
    private float amountSale;//销售总金额；
    private float amountProfit;//销售利润；
    private  String date;//统计日期；
}
