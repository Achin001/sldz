package com.gxc.sldz.vo;

import com.diboot.core.binding.annotation.*;
import com.gxc.sldz.entity.SldzOrder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
* 订单 DetailVO定义
* @author Achin
* @version 1.0
* @date 2021-06-22
 * Copyright © MyCompany
*/
@Getter @Setter @Accessors(chain = true)
public class SldzOrderDetailVO extends SldzOrder  {
    private static final long serialVersionUID = -2315487051370462196L;

}