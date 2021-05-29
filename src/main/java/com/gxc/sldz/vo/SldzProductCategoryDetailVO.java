package com.gxc.sldz.vo;

import com.diboot.core.binding.annotation.*;
import com.gxc.sldz.entity.SldzProductCategory;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.util.List;
import com.gxc.sldz.entity.SldzProduct;

/**
 * 产品类别 DetailVO定义
 * @author Achin
 * @version 1.0
 * @date 2021-05-20
 * Copyright © MyCompany
 */
@Getter
@Setter
@Accessors(chain = true)
public class SldzProductCategoryDetailVO extends SldzProductCategory {

    private static final long serialVersionUID = 898594724442101015L;

    // 绑定sldzProductList
    @BindEntityList(entity = SldzProduct.class, condition = "this.id=product_category")
    private List<SldzProduct> sldzProductList;
}
