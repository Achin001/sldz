package com.gxc.sldz.entity;

import java.util.Date;
import java.lang.Double;
import java.math.BigDecimal;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
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
* 客户档案 Entity定义
* @author Achin
* @version 1.0
* @date 2021-07-29
* Copyright © MyCompany
*/
@Data
@ApiModel(value = "客户档案")
@Accessors(chain = true)
public class SldzCustomerProfile extends BaseCustomEntity {
    private static final long serialVersionUID = 6392497825895892011L;

    /**
    * 客户唯一编码 
    */
    @ApiModelProperty(value="客户唯一编码", example = "")
    @Length(max=100, message="客户唯一编码长度应小于100")
    @TableField()
    private String customerRandom;

    /**
    * 代理商唯一编码 
    */
    @ApiModelProperty(value="代理商唯一编码", example = "")
    @Length(max=100, message="代理商唯一编码长度应小于100")
    @TableField()
    private String agentRandom;

    /**
    * 已扣积分 
    */
    @ApiModelProperty(value="已扣积分", example = "0.0")
    @TableField()
    private Double payPoints;

    /**
    * 客户资料json 
    */
    @ApiModelProperty(value="客户资料json", example = "")
    @Length(max=500, message="客户资料json长度应小于500")
    @TableField()
    private String customerDataJson;

    /**
    * 客户皮肤资料json 
    */
    @ApiModelProperty(value="客户皮肤资料json", example = "")
    @Length(max=1000, message="客户皮肤资料json长度应小于1000")
    @TableField()
    private String customerSkinDataJson;

    /**
    * 状态 1意向客户,2,待审核客户,3,已通过客户,4,未通过客户 
    */
    @ApiModelProperty(value="状态 1意向客户,2,待审核客户,3,已通过客户,4,未通过客户", example = "1")
    @TableField()
    private int state;


} 
