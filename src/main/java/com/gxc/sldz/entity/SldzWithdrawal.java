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
* 提现 Entity定义
* @author Achin
* @version 1.0
* @date 2021-07-15
* Copyright © MyCompany
*/
@ApiModel(value = "提现")
@Getter @Setter @Accessors(chain = true)
public class SldzWithdrawal extends BaseCustomEntity {
    private static final long serialVersionUID = 4371636466470365612L;

    /**
    * 唯一编号 
    */
    @ApiModelProperty(value="唯一编号", example = "")
    @Length(max=100, message="唯一编号长度应小于100")
    @TableField()
    private String random;

    /**
    * 提现者昵称 
    */
    @ApiModelProperty(value="提现者昵称", example = "")
    @Length(max=100, message="提现者昵称长度应小于100")
    @TableField()
    private String withdrawalName;

    /**
    * 提现金额 
    */
    @ApiModelProperty(value="提现金额", example = "")
    @TableField()
    private Double withdrawalAmount;

    /**
    * 银行卡json 
    */
    @ApiModelProperty(value="银行卡json", example = "")
    @Length(max=100, message="银行卡json长度应小于100")
    @TableField()
    private String bankJson;

    /**
    * 状态  1处理中，2已发放,3驳回 
    */
    @ApiModelProperty(value="状态  1处理中，2已发放,3驳回", example = "true")
    @TableField()
    private Boolean state = true;

    /**
    * 处理时间 
    */
    @ApiModelProperty(value="处理时间", example = "2021-07-15 00:00")
    @JsonFormat(pattern = D.FORMAT_DATETIME_Y4MDHM)
    @TableField()
    private Date dateAfter;


} 
