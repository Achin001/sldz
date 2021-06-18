package com.gxc.sldz.dto;

import com.diboot.core.binding.query.BindQuery;
import com.diboot.core.binding.query.Comparison;
import com.gxc.sldz.entity.SldzShopCart;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
* 购物车 DTO定义
* @author Achin
* @version 1.0
* @date 2021-06-18
 * Copyright © MyCompany
*/
@Getter @Setter @Accessors(chain = true)
public class SldzShopCartDTO extends SldzShopCart  {
    private static final long serialVersionUID = -8104209240838861132L;

}