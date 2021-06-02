package com.gxc.sldz.dto;

import com.diboot.core.binding.query.BindQuery;
import com.diboot.core.binding.query.Comparison;
import com.gxc.sldz.entity.SldzAgent;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import com.gxc.sldz.entity.SldzAgentGrade;

/**
 * 代理商 DTO定义
 * @author Achin
 * @version 1.0
 * @date 2021-05-31
 * Copyright © MyCompany
 */
@Getter
@Setter
@Accessors(chain = true)
public class SldzAgentDTO extends SldzAgent {

    private static final long serialVersionUID = -778166419632003528L;

    /**
     * 关联字段 SldzAgentGrade.gradeName
     */
    @BindQuery(comparison = Comparison.EQ, entity = SldzAgentGrade.class, field = "gradeName", condition = "this.agent_grade_id=id")
    private String sldzAgentGradeGradeName;
}
