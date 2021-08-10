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
* @date 2021-07-09
* Copyright © MyCompany
*/
@ApiModel(value = "订单")
@Getter @Setter @Accessors(chain = true)
public class SldzOrder extends BaseCustomEntity {
    private static final long serialVersionUID = -6964696713678032331L;

    /**
    * 订单号 
    */
    @ApiModelProperty(value="订单号", example = "20210706171639392211429")
    @Length(max=100, message="订单号长度应小于100")
    @TableField()
    private String orderNumber;

    /**
    * 买方唯一编号 
    */
    @ApiModelProperty(value="买方唯一编号", required = true, example = "KXHS1lDU")
    @NotNull(message = "买方唯一编号不能为空")
    @Length(max=100, message="买方唯一编号长度应小于100")
    @TableField()
    private String buyersRandom;

    /**
    * 买方姓名 
    */
    @ApiModelProperty(value="买方姓名", required = true, example = "郭祥初")
    @NotNull(message = "买方姓名不能为空")
    @Length(max=100, message="买方姓名长度应小于100")
    @TableField()
    private String buyersName;

    /**
    * 产品json 
    */
    @ApiModelProperty(value="产品json", required = true, example = "")
    @NotNull(message = "产品json不能为空")
    @TableField()
    private String productJson;

    /**
    * 应付金额 
    */
    @ApiModelProperty(value="应付金额", required = true, example = "11070.0")
    @NotNull(message = "应付金额不能为空")
    @TableField()
    private Double amountPayable;

    /**
    * 买方备注 
    */
    @ApiModelProperty(value="买方备注", example = "")
    @Length(max=200, message="买方备注长度应小于200")
    @TableField()
    private String buyersRemarks;

    /**
    * 优惠金额 
    */
    @ApiModelProperty(value="优惠金额", required = true, example = "0.0")
    @NotNull(message = "优惠金额不能为空")
    @TableField()
    private Double discount;

    /**
    * 优惠券json 
    */
    @ApiModelProperty(value="优惠券json", example = "{}")
    @Length(max=1500, message="优惠券json长度应小于1500")
    @TableField()
    private String couponJson;

    /**
    * 实付金额 
    */
    @ApiModelProperty(value="实付金额", required = true, example = "472.0")
    @NotNull(message = "实付金额不能为空")
    @TableField()
    private Double amountActuallyPaid;

    /**
    * 付款方式(1微信钱包,2积分,3,奖励金) 
    */
    @ApiModelProperty(value="付款方式(1微信钱包,2积分,3,奖励金)", example = "1")
    @TableField()
    private int paymentMethod;

    /**
    * 状态(1待付款,2待收货,3,已完成,4售后) 
    */
    @ApiModelProperty(value="状态(1待付款,2待收货,3,已完成,4售后)", example = "1")
    @TableField()
    private int state ;

    /**
    * 收货地址json 
    */
    @ApiModelProperty(value="收货地址json", example = "{}")
    @Length(max=500, message="收货地址json长度应小于500")
    @TableField()
    private String addresJson;

    /**
    * 物流编号 
    */
    @ApiModelProperty(value="物流编号", example = "")
    @Length(max=100, message="物流编号长度应小于100")
    @TableField()
    private String logisticsNumber;

    /**
    * 付款时间 
    */
    @ApiModelProperty(value="付款时间", example = "2021-07-06 00:00")
    @JsonFormat(pattern = D.FORMAT_DATETIME_Y4MDHM)
    @TableField()
    private Date paymentTime;

    /**
    * 发货时间 
    */
    @ApiModelProperty(value="发货时间", example = "")
    @JsonFormat(pattern = D.FORMAT_DATETIME_Y4MDHM)
    @TableField()
    private Date deliveryTime;

    /**
    * 确认收货时间 
    */
    @ApiModelProperty(value="确认收货时间", example = "")
    @JsonFormat(pattern = D.FORMAT_DATETIME_Y4MDHM)
    @TableField()
    private Date confirmReceivingTime;

    /**
    * 微信支付流水号 
    */
    @ApiModelProperty(value="微信支付流水号", example = "")
    @Length(max=200, message="微信支付流水号长度应小于200")
    @TableField()
    private String wxPaySerialNum;

    /**
    * 已评论产品id 
    */
    @ApiModelProperty(value="已评论产品id", example = "")
    @Length(max=500, message="已评论产品id长度应小于500")
    @TableField()
    private String productsIdReviewed;


    /**
     * 是否发放奖励金.1否,2是
     */
    @ApiModelProperty(value="是否发放奖励金.1否,2是", example = "1")
    @TableField()
    private int commissionPayment ;

} 
