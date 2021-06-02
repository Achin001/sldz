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
* 代理商关系表 Entity定义
* @author Achin
* @version 1.0
* @date 2021-06-02
* Copyright © MyCompany
*/
@ApiModel(value = "代理商关系表")
@Getter @Setter @Accessors(chain = true)
public class SldzAgentRel extends BaseCustomEntity {
    private static final long serialVersionUID = 985569412368029793L;

    /**
    * 上级id 
    */
    @ApiModelProperty(value="上级id", example = "")
    @TableField()
    private Long supId;

    /**
    * 下级id 
    */
    @ApiModelProperty(value="下级id", example = "")
    @TableField()
    private Long subId;


} 
