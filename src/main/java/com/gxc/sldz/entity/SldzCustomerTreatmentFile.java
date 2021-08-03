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
* 客户疗程档案 Entity定义
* @author Achin
* @version 1.0
* @date 2021-08-02
* Copyright © MyCompany
*/
@ApiModel(value = "客户疗程档案")
@Getter @Setter @Accessors(chain = true)
public class SldzCustomerTreatmentFile extends BaseCustomEntity {
    private static final long serialVersionUID = -4183384785484997461L;

    /**
    * 客户档案Id 
    */
    @ApiModelProperty(value="客户档案Id", example = "")
    @TableField()
    private Long customerProFile;

    /**
    * 左脸图片 
    */
    @ApiModelProperty(value="左脸图片", example = "")
    @Length(max=200, message="左脸图片长度应小于200")
    @TableField()
    private String leftFacePic;

    /**
    * 正脸图片 
    */
    @ApiModelProperty(value="正脸图片", example = "")
    @Length(max=200, message="正脸图片长度应小于200")
    @TableField()
    private String justFacePic;

    /**
    * 右脸图片 
    */
    @ApiModelProperty(value="右脸图片", example = "")
    @Length(max=200, message="右脸图片长度应小于200")
    @TableField()
    private String rightFacePic;

    /**
    * 详情 
    */
    @ApiModelProperty(value="详情", example = "")
    @Length(max=500, message="详情长度应小于500")
    @TableField()
    private String details;

    /**
    * 运单号 
    */
    @ApiModelProperty(value="运单号", example = "")
    @Length(max=100, message="运单号长度应小于100")
    @TableField()
    private String logistics;

    /**
    * 客户填写时间 
    */
    @ApiModelProperty(value="客户填写时间", example = "2021-08-02 00:00")
    @JsonFormat(pattern = D.FORMAT_DATETIME_Y4MDHM)
    @TableField()
    private Date customerInTime;


} 
