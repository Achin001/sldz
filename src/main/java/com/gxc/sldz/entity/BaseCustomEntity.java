package com.gxc.sldz.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.diboot.core.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
* 自定义BaseEntity，对diboot-core的BaseEntity做差异化定义
* @author Achin
* @version 1.0
* @date 2021-05-19
* Copyright © MyCompany
*/
@Data
@Getter @Setter @Accessors(chain = true)
public abstract class BaseCustomEntity extends BaseEntity {
    private static final long serialVersionUID = -7651466480138000162L;

    @ApiModelProperty(hidden = true)
    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(hidden = true)
    @JsonIgnore
    @TableField("is_deleted")
    private boolean deleted = false;

}
