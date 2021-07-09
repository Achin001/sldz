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
 * 产品评论 Entity定义
 * @author Achin
 * @version 1.0
 * @date 2021-07-06
 * Copyright © MyCompany
 */
@ApiModel(value = "产品评论")
@Getter
@Setter
@Accessors(chain = true)
public class SldzProductReviews extends BaseCustomEntity {

    private static final long serialVersionUID = -7117491176335541267L;

    /**
     * 产品id
     */
    @ApiModelProperty(value = "产品id", example = "")
    @TableField()
    private Long productId;

    /**
     * 评论人json {唯一编码,昵称,头像}
     */
    @ApiModelProperty(value = "评论人json {唯一编码,昵称,头像}", example = "")
    @Length(max = 300, message = "评论人json {唯一编码,昵称,头像}长度应小于300")
    @TableField()
    private String reviewerJson;

    /**
     * 产品描述指数
     */
    @ApiModelProperty(value = "产品描述指数", example = "5")
    @TableField()
    private Integer productDescriptionIndex;

    /**
     * 评论内容
     */
    @ApiModelProperty(value = "评论内容 ", example = "")
    @Length(max = 2000, message = "评论内容 长度应小于2000")
    @TableField()
    private String commentContent;

    /**
     * 实拍图json
     */
    @ApiModelProperty(value = "实拍图json", example = "")
    @Length(max = 2000, message = "实拍图json长度应小于2000")
    @TableField()
    private String picturesJson;

    /**
     * 时间
     */
    @ApiModelProperty(value = "时间", example = "2021-07-06 00:00")
    @JsonFormat(pattern = D.FORMAT_DATETIME_Y4MDHM)
    @TableField()
    private Date reviewerDate;

    /**
     * 服务指数
     */
    @ApiModelProperty(value = "服务指数 ", example = "5")
    @TableField()
    private Integer serviceIndex;

    /**
     * 物流指数
     */
    @ApiModelProperty(value = "物流指数", example = "5")
    @TableField()
    private Integer logisticsIndex;
}
