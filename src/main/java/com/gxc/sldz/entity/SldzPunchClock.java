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
* 签到 Entity定义
* @author Achin
* @version 1.0
* @date 2021-07-06
* Copyright © MyCompany
*/
@ApiModel(value = "签到")
@Getter @Setter @Accessors(chain = true)
public class SldzPunchClock extends BaseCustomEntity {
    private static final long serialVersionUID = -4286143429770267981L;

    /**
    * 唯一编码 
    */
    @ApiModelProperty(value="唯一编码", example = "")
    @Length(max=100, message="唯一编码长度应小于100")
    @TableField()
    private String random;

    /**
    * 签到人json 
    */
    @ApiModelProperty(value="签到人json", example = "")
    @Length(max=100, message="签到人json长度应小于100")
    @TableField()
    private String checkPersonJson;

    /**
    * 签到时间 
    */
    @ApiModelProperty(value="签到时间", example = "2021-07-06")
    @JsonFormat(pattern = D.FORMAT_DATE_Y4MD)
    @TableField()
    private Date clockDate;

    /**
    * 所得积分 
    */
    @ApiModelProperty(value="所得积分", example = "0.0")
    @TableField()
    private Double pointsGained;

    /**
    * 连续签到天数 
    */
    @ApiModelProperty(value="连续签到天数", example = "0")
    @TableField()
    private Integer continuousCheckDays;


} 
