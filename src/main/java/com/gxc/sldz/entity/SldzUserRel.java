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
* 用户绑定 Entity定义
* @author Achin
* @version 1.0
* @date 2021-07-15
* Copyright © MyCompany
*/
@ApiModel(value = "用户绑定")
@Getter @Setter @Accessors(chain = true)
public class SldzUserRel extends BaseCustomEntity {
    private static final long serialVersionUID = 2955804633771289297L;

    /**
    * 上级编号 
    */
    @ApiModelProperty(value="上级编号", example = "")
    @Length(max=100, message="上级编号长度应小于100")
    @TableField()
    private String supRandom;

    /**
    * 下级编号 
    */
    @ApiModelProperty(value="下级编号", example = "")
    @Length(max=100, message="下级编号长度应小于100")
    @TableField()
    private String subRandom;


} 
