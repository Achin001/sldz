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
 * 订单奖励金发放记录 Entity定义
 * @author Achin
 * @version 1.0
 * @date 2021-08-16
 * Copyright © MyCompany
 */
@ApiModel(value = "订单奖励金发放记录")
@Getter
@Setter
@Accessors(chain = true)
public class SldzOrderRewardDueLog extends BaseCustomEntity {

    private static final long serialVersionUID = 1070801351349117601L;

    /**
     * 订单号
     */
    @ApiModelProperty(value = "订单号", example = "")
    @Length(max = 100, message = "订单号长度应小于100")
    @TableField()
    private String orderNumber;

    /**
     * 上级Random
     */
    @ApiModelProperty(value = "上级Random", example = "")
    @Length(max = 100, message = "上级Random长度应小于100")
    @TableField()
    private String supRandom;

    /**
     * 上级姓名
     */
    @ApiModelProperty(value = "上级姓名", example = "")
    @Length(max = 100, message = "上级姓名长度应小于100")
    @TableField()
    private String supName;

    /**
     * 关系
     */
    @ApiModelProperty(value = "关系", example = "")
    @Length(max = 100, message = "关系长度应小于100")
    @TableField()
    private String relationship;

    /**
     * 产品id
     */
    @ApiModelProperty(value = "产品id", example = "")
    @TableField()
    private Long productId;

    /**
     * 产品价格
     */
    @ApiModelProperty(value = "产品价格", example = "")
    @TableField()
    private Double productPrice;

    /**
     * 加购数量
     */
    @ApiModelProperty(value = "加购数量", example = "")
    @TableField()
    private Integer cartNum;

    /**
     * 应得奖励金
     */
    @ApiModelProperty(value = "应得奖励金", example = "")
    @TableField()
    private Double dueReward;
}
