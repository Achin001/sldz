package com.gxc.sldz.dto;

import com.diboot.core.binding.query.BindQuery;
import com.diboot.core.binding.query.Comparison;
import com.gxc.sldz.entity.SldzUserRel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
* 用户绑定 DTO定义
* @author Achin
* @version 1.0
* @date 2021-07-15
 * Copyright © MyCompany
*/
@Getter @Setter @Accessors(chain = true)
public class SldzUserRelDTO extends SldzUserRel  {
    private static final long serialVersionUID = 8286924559616933223L;

}