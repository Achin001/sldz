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
* 轮播图 Entity定义
* @author Achin
* @version 1.0
* @date 2021-05-19
* Copyright © MyCompany
*/
@ApiModel(value = "轮播图")
@Getter @Setter @Accessors(chain = true)
public class SldzBanner extends BaseCustomEntity {
    private static final long serialVersionUID = 4674784300875131863L;

    /**
    * 轮播图地址 
    */
    @ApiModelProperty(value="轮播图地址", example = "")
    @Length(max=300, message="轮播图地址长度应小于300")
    @TableField()
    private String bannerImg;


} 
