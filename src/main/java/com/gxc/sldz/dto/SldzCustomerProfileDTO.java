package com.gxc.sldz.dto;

import com.diboot.core.binding.query.BindQuery;
import com.diboot.core.binding.query.Comparison;
import com.gxc.sldz.entity.SldzCustomerProfile;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
* 客户档案 DTO定义
* @author Achin
* @version 1.0
* @date 2021-07-29
 * Copyright © MyCompany
*/
@Getter @Setter @Accessors(chain = true)
public class SldzCustomerProfileDTO extends SldzCustomerProfile  {
    private static final long serialVersionUID = -5949102778305124553L;

}