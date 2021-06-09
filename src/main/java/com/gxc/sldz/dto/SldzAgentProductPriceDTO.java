package com.gxc.sldz.dto;

import com.diboot.core.binding.query.BindQuery;
import com.diboot.core.binding.query.Comparison;
import com.gxc.sldz.entity.SldzAgentProductPrice;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
* 代理商产品价格 DTO定义
* @author Achin
* @version 1.0
* @date 2021-06-09
 * Copyright © MyCompany
*/
@Getter @Setter @Accessors(chain = true)
public class SldzAgentProductPriceDTO extends SldzAgentProductPrice  {
    private static final long serialVersionUID = 2208560945518357018L;

}