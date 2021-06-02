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
* 代理商等级 Entity定义
* @author Achin
* @version 1.0
* @date 2021-06-02
* Copyright © MyCompany
*/
@ApiModel(value = "代理商等级")
@Getter @Setter @Accessors(chain = true)
public class SldzAgentGrade extends BaseCustomEntity {
    private static final long serialVersionUID = 1206681524587304435L;

    /**
    * 等级名称 
    */
    @ApiModelProperty(value="等级名称", example = "")
    @Length(max=100, message="等级名称长度应小于100")
    @TableField()
    private String gradeName;


} 
