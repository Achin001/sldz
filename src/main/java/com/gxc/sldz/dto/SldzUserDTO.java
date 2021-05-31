package com.gxc.sldz.dto;

import com.diboot.core.binding.query.BindQuery;
import com.diboot.core.binding.query.Comparison;
import com.gxc.sldz.entity.SldzUser;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
* 用户 DTO定义
* @author Achin
* @version 1.0
* @date 2021-05-31
 * Copyright © MyCompany
*/
@Getter @Setter @Accessors(chain = true)
public class SldzUserDTO extends SldzUser  {
    private static final long serialVersionUID = 5304869049066528841L;

}