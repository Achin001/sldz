package com.gxc.sldz.vo;

import com.diboot.core.binding.annotation.*;
import com.gxc.sldz.entity.SldzOrder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
* 订单 ListVO定义
* @author Achin
* @version 1.0
* @date 2021-06-22
 * Copyright © MyCompany
*/
@Getter @Setter @Accessors(chain = true)
public class SldzOrderListVO extends SldzOrder  {
    private static final long serialVersionUID = -7031633868624964782L;

}