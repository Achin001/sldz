package com.gxc.sldz.dto;

import com.diboot.core.binding.query.BindQuery;
import com.diboot.core.binding.query.Comparison;
import com.gxc.sldz.entity.SldzPunchClock;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
* 签到 DTO定义
* @author Achin
* @version 1.0
* @date 2021-07-06
 * Copyright © MyCompany
*/
@Getter @Setter @Accessors(chain = true)
public class SldzPunchClockDTO extends SldzPunchClock  {
    private static final long serialVersionUID = 3377774817944366859L;

}