package com.gxc.sldz.vo;

import com.diboot.core.binding.annotation.*;
import com.gxc.sldz.entity.SldzCustomerProfile;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
* 客户档案 ListVO定义
* @author Achin
* @version 1.0
* @date 2021-07-29
 * Copyright © MyCompany
*/
@Getter @Setter @Accessors(chain = true)
public class SldzCustomerProfileListVO extends SldzCustomerProfile  {
    private static final long serialVersionUID = 6193732019219636009L;

}