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
* 产品类别 Entity定义
* @author Achin
* @version 1.0
* @date 2021-05-20
* Copyright © MyCompany
*/
@ApiModel(value = "产品类别")
@Getter @Setter @Accessors(chain = true)
public class SldzProductCategory extends BaseCustomEntity {
    private static final long serialVersionUID = 6390865188518279112L;

    /**
    * 类别名称 
    */
    @ApiModelProperty(value="类别名称", example = "")
    @Length(max=100, message="类别名称长度应小于100")
    @TableField()
    private String categoryName;


} 
