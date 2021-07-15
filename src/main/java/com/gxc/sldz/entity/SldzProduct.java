package com.gxc.sldz.entity;

import java.util.Date;
import java.lang.Double;
import java.math.BigDecimal;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
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
 * 产品 Entity定义
 * @author Achin
 * @version 1.0
 * @date 2021-05-20
 * Copyright © MyCompany
 */

@Data
@ApiModel(value = "产品")
@Accessors(chain = true)
public class SldzProduct extends BaseCustomEntity {

    private static final long serialVersionUID = -8489311193336104364L;

    /**
     * 产品分类id
     */
    @ApiModelProperty(value = "产品分类id", example = "")
    @TableField()
    private Long productCategory;

    /**
     * 产品主图
     */
    @ApiModelProperty(value = "产品主图", example = "")
    @Length(max = 500, message = "产品主图长度应小于500")
    @TableField()
    private String productImgs;

    /**
     * 产品名称
     */
    @ApiModelProperty(value = "产品名称", example = "")
    @Length(max = 100, message = "产品名称长度应小于100")
    @TableField()
    private String productName;

    /**
     * 产品价格
     */
    @ApiModelProperty(value = "产品价格", required = true, example = "0.0")
    @NotNull(message = "产品价格不能为空")
    @TableField()
    private Double productPrice;

    /**
     * 产品视频
     */
    @ApiModelProperty(value = "产品视频", example = "")
    @Length(max = 150, message = "产品视频长度应小于150")
    @TableField()
    private String productVideo;

    /**
     * 产品详情
     */
    @ApiModelProperty(value = "产品详情", example = "")
    @Length(max = 5000, message = "产品详情长度应小于5000")
    @TableField()
    private String productDetails;

    /**
     * 产品库存
     */
    @ApiModelProperty(value = "产品库存", example = "0")
    @TableField()
    private Long productStock;


    /**
     * 产品优惠价格
     */
    @ApiModelProperty(value = "产品优惠价格",  example = "0.0")
    @TableField()
    private Double favorablePrice = 0.00;


}
