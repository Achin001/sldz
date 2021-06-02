package com.gxc.sldz.vo;

import com.diboot.core.binding.annotation.*;
import com.gxc.sldz.entity.SldzAgent;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.util.List;
import com.gxc.sldz.entity.SldzAgentGrade;

/**
 * 代理商 DetailVO定义
 * @author Achin
 * @version 1.0
 * @date 2021-05-31
 * Copyright © MyCompany
 */
@Getter
@Setter
@Accessors(chain = true)
public class SldzAgentDetailVO extends SldzAgent {

    private static final long serialVersionUID = 7852831771899436034L;

    /**
     * 关联字段：SldzAgentGrade.gradeName
     */
    @BindField(entity = SldzAgentGrade.class, field = "gradeName", condition = "this.agent_grade_id=id")
    private String sldzAgentGradeGradeName;
}
