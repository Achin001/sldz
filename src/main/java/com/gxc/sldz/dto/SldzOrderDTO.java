package com.gxc.sldz.dto;

import com.diboot.core.binding.query.BindQuery;
import com.diboot.core.binding.query.Comparison;
import com.gxc.sldz.entity.SldzOrder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
* 订单 DTO定义
* @author Achin
* @version 1.0
* @date 2021-06-22
 * Copyright © MyCompany
*/
@Getter @Setter @Accessors(chain = true)
public class SldzOrderDTO extends SldzOrder  {
    private static final long serialVersionUID = 7687376419071941178L;

}