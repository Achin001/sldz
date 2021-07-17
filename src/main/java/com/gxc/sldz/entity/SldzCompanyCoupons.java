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
 * 公司优惠券 Entity定义
 * @author Achin
 * @version 1.0
 * @date 2021-06-22
 * Copyright © MyCompany
 */
@ApiModel(value = "公司优惠券")
@Getter
@Setter
@Accessors(chain = true)
public class SldzCompanyCoupons extends BaseCustomEntity {

    private static final long serialVersionUID = 4261816699039245477L;

    /**
     * 优惠券名称
     */
    @ApiModelProperty(value = "优惠券名称", example = "")
    @Length(max = 100, message = "优惠券名称长度应小于100")
    @TableField()
    private String couponsName;

    /**
     * 口令
     */
    @ApiModelProperty(value = "口令", example = "")
    @Length(max = 100, message = "吱口令长度应小于100")
    @TableField()
    private String couponsSpwd;

    /**
     * 优惠券大厅是否显示(1显示,2不显示)
     */
    @ApiModelProperty(value = "优惠券大厅是否显示(1显示,2不显示)", example = "1")
    @TableField()
    private Integer couponsShowOrHide ;

    /**
     * 优惠券总数量
     */
    @ApiModelProperty(value = "优惠券总数量", example = "")
    @TableField()
    private Integer couponsTotal;

    /**
     * 优惠券失效时间
     */
    @ApiModelProperty(value = "优惠券失效时间", example = "2021-06-22 00:00")
    @JsonFormat(pattern = D.FORMAT_DATETIME_Y4MDHM)
    @TableField()
    private Date couponsFailureTime;

    /**
     * 满多少
     */
    @ApiModelProperty(value = "满多少", example = "")
    @TableField()
    private Double couponsFullPrice;

    /**
     * 减多少
     */
    @ApiModelProperty(value = "减多少", example = "")
    @TableField()
    private Double couponsReducePrice;

    /**
     * 指定可用产品id(  数组 )
     */
    @ApiModelProperty(value = "指定可用产品id(  数组 )", example = "")
    @Length(max = 500, message = "指定可用产品id(  数组 )长度应小于500")
    @TableField()
    private String couponsAppointProductIds;

    /**
     * 详情
     */
    @ApiModelProperty(value = "详情", example = "")
    @Length(max = 500, message = "详情长度应小于500")
    @TableField()
    private String couponsDetails;
}
