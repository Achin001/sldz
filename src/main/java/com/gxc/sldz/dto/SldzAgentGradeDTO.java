package com.gxc.sldz.dto;

import com.diboot.core.binding.query.BindQuery;
import com.diboot.core.binding.query.Comparison;
import com.gxc.sldz.entity.SldzAgentGrade;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
* 代理商等级 DTO定义
* @author Achin
* @version 1.0
* @date 2021-06-02
 * Copyright © MyCompany
*/
@Getter @Setter @Accessors(chain = true)
public class SldzAgentGradeDTO extends SldzAgentGrade  {
    private static final long serialVersionUID = 5611004793834593820L;

}