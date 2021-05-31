package com.gxc.sldz.dto;

import com.diboot.core.binding.query.BindQuery;
import com.diboot.core.binding.query.Comparison;
import com.gxc.sldz.entity.SldzAgent;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
* 代理商 DTO定义
* @author Achin
* @version 1.0
* @date 2021-05-31
 * Copyright © MyCompany
*/
@Getter @Setter @Accessors(chain = true)
public class SldzAgentDTO extends SldzAgent  {
    private static final long serialVersionUID = -778166419632003528L;

}