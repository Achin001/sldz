package com.gxc.sldz.dto;

import com.diboot.core.binding.query.BindQuery;
import com.diboot.core.binding.query.Comparison;
import com.gxc.sldz.entity.SldzOrderRewardDueLog;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
* 订单奖励金发放记录 DTO定义
* @author Achin
* @version 1.0
* @date 2021-08-16
 * Copyright © MyCompany
*/
@Getter @Setter @Accessors(chain = true)
public class SldzOrderRewardDueLogDTO extends SldzOrderRewardDueLog  {
    private static final long serialVersionUID = 1483062767226740541L;

}