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
* 奖励金设置 Entity定义
* @author Achin
* @version 1.0
* @date 2021-06-09
* Copyright © MyCompany
*/
@ApiModel(value = "奖励金设置")
@Getter @Setter @Accessors(chain = true)
public class SldzBonuSsetting extends BaseCustomEntity {
    private static final long serialVersionUID = -7377901979401977312L;

    /**
    * 产品id 
    */
    @ApiModelProperty(value="产品id", example = "0")
    @TableField()
    private Long productId;

    /**
    * 代理商id 
    */
    @ApiModelProperty(value="代理商编码", example = "0")
    @TableField()
    private String agentRandom;

    /**
    * 奖励金 
    */
    @ApiModelProperty(value="奖励金", example = "0.0")
    @TableField()
    private Double bonus;


} 
