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
 * 代理商积分纪录 Entity定义
 * @author Achin
 * @version 1.0
 * @date 2021-06-02
 * Copyright © MyCompany
 */
@ApiModel(value = "代理商积分纪录")
@Getter
@Setter
@Accessors(chain = true)
public class SldzAgentIntegralLog extends BaseCustomEntity {

    private static final long serialVersionUID = 7120939960560286329L;

    /**
     * 代理商唯一编码
     */
    @ApiModelProperty(value = "代理商唯一编码", example = "")
    @Length(max = 100, message = "代理商唯一编码长度应小于100")
    @TableField()
    private String agentRandom;

    /**
     * 1是收入2是支出
     */
    @ApiModelProperty(value = "1是收入2是支出", example = "")
    @TableField()
    private Long integralType;

    /**
     * 金额
     */
    @ApiModelProperty(value = "金额", example = "")
    @TableField()
    private Double integralMoney;

    /**
     * 事件
     */
    @ApiModelProperty(value = "事件", example = "")
    @Length(max = 150, message = "事件长度应小于150")
    @TableField()
    private String integralEvent;

    /**
     * 日期
     */
    @ApiModelProperty(value = "日期", example = "2021-06-02")
    @Length(max = 100, message = "日期长度应小于100")
    @TableField()
    private String integralDate;
}
