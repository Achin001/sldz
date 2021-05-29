package com.gxc.sldz.dto;

import com.diboot.core.binding.query.BindQuery;
import com.diboot.core.binding.query.Comparison;
import com.gxc.sldz.entity.SldzProductCategory;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
* 产品类别 DTO定义
* @author Achin
* @version 1.0
* @date 2021-05-20
 * Copyright © MyCompany
*/
@Getter @Setter @Accessors(chain = true)
public class SldzProductCategoryDTO extends SldzProductCategory  {
    private static final long serialVersionUID = 3547278147416891496L;

}