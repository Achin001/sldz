package com.gxc.sldz.vo;

import com.diboot.core.binding.annotation.*;
import com.gxc.sldz.entity.SldzUser;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
* 用户 ListVO定义
* @author Achin
* @version 1.0
* @date 2021-05-31
 * Copyright © MyCompany
*/
@Getter @Setter @Accessors(chain = true)
public class SldzUserListVO extends SldzUser  {
    private static final long serialVersionUID = 3140297094165104847L;

}