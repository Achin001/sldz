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
* 代理商分组 Entity定义
* @author Achin
* @version 1.0
* @date 2021-06-09
* Copyright © MyCompany
*/
@ApiModel(value = "代理商分组")
@Getter @Setter @Accessors(chain = true)
public class SldzAgentGroup extends BaseCustomEntity {
    private static final long serialVersionUID = -1690995543837089204L;

    /**
    * 分组名称 
    */
    @ApiModelProperty(value="分组名称", example = "")
    @Length(max=100, message="分组名称长度应小于100")
    @TableField()
    private String groupName;


} 
