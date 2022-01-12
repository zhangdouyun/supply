package com.yearbooks.supply.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 进货单
 * </p>
 *
 * @author zhangDouYun
 * @since 2021-12-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_purchase_list")
@ApiModel(value="PurchaseList对象", description="进货单")
public class PurchaseList implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "实付金额")
    private Float amountPaid;

    @ApiModelProperty(value = "应付金额")
    private Float amountPayable;

    @ApiModelProperty(value = "进货日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date purchaseDate;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "交易状态")
    private Integer state;

    @ApiModelProperty(value = "进货单号")
    private String purchaseNumber;

    @ApiModelProperty(value = "供应商")
    private Integer supplierId;

    @ApiModelProperty(value = "操作用户")
    private Integer userId;

    @TableField(exist = false)
    private String userName;

    @TableField(exist = false)
    private String supplierName;
}
