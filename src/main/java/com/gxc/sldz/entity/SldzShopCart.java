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
* 购物车 Entity定义
* @author Achin
* @version 1.0
* @date 2021-06-18
* Copyright © MyCompany
*/
@ApiModel(value = "购物车")
@Getter @Setter @Accessors(chain = true)
public class SldzShopCart extends BaseCustomEntity {
    private static final long serialVersionUID = -1558955467357978972L;

    /**
    * 唯一编码 
    */
    @ApiModelProperty(value="唯一编码", example = "")
    @Length(max=100, message="唯一编码长度应小于100")
    @TableField()
    private String agentRandom;

    /**
    * 产品json 
    */
    @ApiModelProperty(value="产品json", example = "")
    @Length(max=3000, message="产品json长度应小于3000")
    @TableField()
    private String productJson;

    /**
    * 加购数量 
    */
    @ApiModelProperty(value="加购数量", example = "")
    @TableField()
    private Long cartNum;


} 
