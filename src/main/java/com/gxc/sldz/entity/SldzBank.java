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
* 银行卡 Entity定义
* @author Achin
* @version 1.0
* @date 2021-07-15
* Copyright © MyCompany
*/
@ApiModel(value = "银行卡")
@Getter @Setter @Accessors(chain = true)
public class SldzBank extends BaseCustomEntity {
    private static final long serialVersionUID = -6739357704959976379L;

    /**
    * 唯一编码 
    */
    @ApiModelProperty(value="唯一编码", example = "")
    @Length(max=100, message="唯一编码长度应小于100")
    @TableField()
    private String random;

    /**
    * 银行卡图片 
    */
    @ApiModelProperty(value="银行卡图片", example = "")
    @Length(max=255, message="银行卡图片长度应小于255")
    @TableField()
    private String bankImg;

    /**
    * 开户行 
    */
    @ApiModelProperty(value="开户行", example = "")
    @Length(max=100, message="开户行长度应小于100")
    @TableField()
    private String bankName;

    /**
    * 账号 
    */
    @ApiModelProperty(value="账号", example = "")
    @Length(max=100, message="账号长度应小于100")
    @TableField()
    private String bankNumber;

    /**
    * 开户名 
    */
    @ApiModelProperty(value="开户名", example = "")
    @Length(max=100, message="开户名长度应小于100")
    @TableField()
    private String bankCcountName;

    /**
    * 开户省 
    */
    @ApiModelProperty(value="开户省", example = "")
    @Length(max=100, message="开户省长度应小于100")
    @TableField()
    private String bankCcountSheng;

    /**
    * 开户城市 
    */
    @ApiModelProperty(value="开户城市", example = "")
    @Length(max=100, message="开户城市长度应小于100")
    @TableField()
    private String bankCcountShi;

    /**
    * 开户网点/支行 
    */
    @ApiModelProperty(value="开户网点/支行", example = "")
    @Length(max=100, message="开户网点/支行长度应小于100")
    @TableField()
    private String bankBranchName;


} 
