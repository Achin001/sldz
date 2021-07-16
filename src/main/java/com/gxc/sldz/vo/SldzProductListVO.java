package com.gxc.sldz.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.diboot.core.binding.annotation.*;
import com.gxc.sldz.entity.SldzProduct;
import com.gxc.sldz.entity.SldzProductCategory;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 产品 ListVO定义
* @author Achin
* @version 1.0
* @date 2021-05-20
 * Copyright © MyCompany
*/
@Getter @Setter @Accessors(chain = true)
public class SldzProductListVO extends SldzProduct  {
    private static final long serialVersionUID = -2010968612120515711L;

    /** 
    * 关联字段：SldzProductCategory.categoryName
    */
    @BindField(entity = SldzProductCategory.class, field = "categoryName", condition = "this.product_category=id")
    private String sldzProductCategoryCategoryName;

    /**
     * 产品优惠价格
     */
    @ApiModelProperty(value = "产品优惠价格",  example = "0.0")
    private Double favorablePrice = 0.00;




}