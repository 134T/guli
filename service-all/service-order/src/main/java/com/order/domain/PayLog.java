package com.order.domain;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 支付日志表
 * </p>
 *
 * @author asus
 * @since 2021-11-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_pay_log")
@ApiModel(value="PayLog对象", description="支付日志表")
public class PayLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty(value = "订单号")
    @TableField("order_no")
    private String orderNo;

    @ApiModelProperty(value = "支付完成时间")
    @TableField("pay_time")
    private Date payTime;

    @ApiModelProperty(value = "支付金额（分）")
    @TableField("total_fee")
    private BigDecimal totalFee;

    @ApiModelProperty(value = "交易流水号")
    @TableField("transaction_id")
    private String transactionId;

    @ApiModelProperty(value = "交易状态")
    @TableField("trade_state")
    private String tradeState;

    @ApiModelProperty(value = "支付类型（1：微信 2：支付宝）")
    @TableField("pay_type")
    private Integer payType;

    @ApiModelProperty(value = "其他属性")
    @TableField("attr")
    private String attr;

    @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
    @TableField("is_deleted")
    @TableLogic
    private Boolean isDeleted;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}
