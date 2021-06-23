package com.gxc.sldz.entity;

import java.util.Date;
import java.lang.Double;
import java.math.BigDecimal;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;
import java.util.List;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.FieldFill;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.diboot.core.binding.query.BindQuery;
import com.diboot.core.binding.query.Comparison;
import com.diboot.core.util.D;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 订单 Entity定义
 * @author Achin
 * @version 1.0
 * @date 2021-06-22
 * Copyright © MyCompany
 */
@ApiModel(value = "订单")
@Getter
@Setter
@Accessors(chain = true)
public class SldzOrder extends BaseCustomEntity {

    private static final long serialVersionUID = 4845868198009471896L;

    /**
     * 订单号
     */
    @ApiModelProperty(value = "订单号", example = "订单号样例内容")
    @Length(max = 100, message = "订单号长度应小于100")
    @TableField()
    private String orderNumber;

    /**
     * 微信支付流水号
     */
    @ApiModelProperty(value = "微信支付流水号", example = "")
    @Length(max = 200, message = "微信支付流水号长度应小于200")
    @TableField()
    private String wxPaySerialNum;

    /**
     * 买方唯一编号
     */
    @ApiModelProperty(value = "买方唯一编号", example = "")
    @Length(max = 100, message = "买方唯一编号长度应小于100")
    @TableField()
    private String buyersRandom;

    /**
     * 买方姓名
     */
    @ApiModelProperty(value = "买方姓名", example = "")
    @Length(max = 100, message = "买方姓名长度应小于100")
    @TableField()
    private String buyersName;

    /**
     * 应付金额
     */
    @ApiModelProperty(value = "应付金额", required = true, example = "0.0")
    @NotNull(message = "应付金额不能为空")
    @TableField()
    private Double amountPayable;

    /**
     * 产品json
     */
    @ApiModelProperty(value = "产品json", example = "")
    @Length(max = 3000, message = "产品json长度应小于3000")
    @TableField()
    private String productJson;

    /**
     * 优惠金额
     */
    @ApiModelProperty(value = "优惠金额", required = true, example = "0.0")
    @NotNull(message = "优惠金额不能为空")
    @TableField()
    private Double discount;

    /**
     * 优惠券json
     */
    @ApiModelProperty(value = "优惠券json", example = "")
    @Length(max = 1500, message = "优惠券json长度应小于1500")
    @TableField()
    private String couponJson;

    /**
     * 付款方式(1微信钱包,2奖励金,3,积分)
     */
    @ApiModelProperty(value = "付款方式(1微信钱包,2奖励金,3,积分)", example = "")
    @TableField()
    private Boolean paymentMethod;

    /**
     * 实付金额
     */
    @ApiModelProperty(value = "实付金额", required = true, example = "0.0")
    @NotNull(message = "实付金额不能为空")
    @TableField()
    private Double amountActuallyPaid;

    /**
     * 状态(1待付款,2待收货,3,已完成,4售后)
     */
    @ApiModelProperty(value = "状态(1待付款,2待收货,3,已完成,4售后)", example = "")
    @TableField()
    private Boolean state;

    /**
     * 收货地址json
     */
    @ApiModelProperty(value = "收货地址json", example = "")
    @Length(max = 1000, message = "收货地址json长度应小于1000")
    @TableField()
    private String addresJson;

    /**
     * 物流编号
     */
    @ApiModelProperty(value = "物流编号", example = "")
    @Length(max = 100, message = "物流编号长度应小于100")
    @TableField()
    private String logisticsNumber;

    /**
     * 付款时间
     */
    @ApiModelProperty(value = "付款时间", example = "2021-06-22 00:00")
    @JsonFormat(pattern = D.FORMAT_DATETIME_Y4MDHM)
    @TableField()
    private Date paymentTime;

    /**
     * 发货时间
     */
    @ApiModelProperty(value = "发货时间", example = "2021-06-22 00:00")
    @JsonFormat(pattern = D.FORMAT_DATETIME_Y4MDHM)
    @TableField()
    private Date deliveryTime;

    /**
     * 确认收货时间
     */
    @ApiModelProperty(value = "确认收货时间", example = "2021-06-22 00:00")
    @JsonFormat(pattern = D.FORMAT_DATETIME_Y4MDHM)
    @TableField()
    private Date confirmReceivingTime;
}
