package com.gxc.sldz.dto;

import com.diboot.core.binding.query.BindQuery;
import com.diboot.core.binding.query.Comparison;
import com.gxc.sldz.entity.SldzProduct;
import com.gxc.sldz.entity.SldzProductCategory;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
* 产品 DTO定义
* @author Achin
* @version 1.0
* @date 2021-05-20
 * Copyright © MyCompany
*/
@Getter @Setter @Accessors(chain = true)
public class SldzProductDTO extends SldzProduct  {
    private static final long serialVersionUID = -1954223618061454942L;

    /**
    * 关联字段 SldzProductCategory.categoryName
    */
    @BindQuery(comparison = Comparison.EQ, entity = SldzProductCategory.class, field = "categoryName", condition = "this.product_category=id")
    private String sldzProductCategoryCategoryName;
}