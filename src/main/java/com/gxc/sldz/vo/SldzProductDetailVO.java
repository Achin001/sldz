package com.gxc.sldz.vo;

import com.diboot.core.binding.annotation.*;
import com.gxc.sldz.entity.SldzProduct;
import com.gxc.sldz.entity.SldzProductCategory;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
* 产品 DetailVO定义
* @author Achin
* @version 1.0
* @date 2021-05-20
 * Copyright © MyCompany
*/
@Getter @Setter @Accessors(chain = true)
public class SldzProductDetailVO extends SldzProduct  {
    private static final long serialVersionUID = -7380502476746905597L;

    /**
    * 关联字段：SldzProductCategory.categoryName
    */
    @BindField(entity = SldzProductCategory.class, field = "categoryName", condition = "this.product_category=id")
    private String sldzProductCategoryCategoryName;

    /**
    * 关联对象：SldzProductCategory
    */ 
    @BindEntity(entity = SldzProductCategory.class, condition = "this.product_category=id")
    private SldzProductCategory sldzProductCategory;

}