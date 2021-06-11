package com.gxc.sldz.entity;

import java.util.Date;
import java.lang.Double;
import java.math.BigDecimal;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;
import java.util.List;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.FieldFill;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.diboot.core.binding.query.BindQuery;
import com.diboot.core.binding.query.Comparison;
import com.diboot.core.util.D;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 代理层级奖励设置 Entity定义
 * @author Achin
 * @version 1.0
 * @date 2021-06-11
 * Copyright © MyCompany
 */
@ApiModel(value = "代理层级奖励设置")
@Getter
@Setter
@Accessors(chain = true)
public class SldzAgentLevelReward extends BaseCustomEntity {

    private static final long serialVersionUID = 2939756494468181400L;

    /**
     * 代理商编码
     */
    @ApiModelProperty(value = "代理商编码", example = "")
    @Length(max = 100, message = "代理商编码长度应小于100")
    @TableField()
    private String agentRandom;

    /**
     * 间推奖励
     */
    @ApiModelProperty(value = "间推奖励", example = "")
    @TableField()
    private Double rewardIndirect;

    /**
     * 直推奖励
     */
    @ApiModelProperty(value = "直推奖励", example = "")
    @TableField()
    private Double rewardDirect;
}
