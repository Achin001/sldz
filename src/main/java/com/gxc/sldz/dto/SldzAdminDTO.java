package com.gxc.sldz.dto;

import com.diboot.core.binding.query.BindQuery;
import com.diboot.core.binding.query.Comparison;
import com.gxc.sldz.entity.SldzAdmin;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
* 管理员 DTO定义
* @author Achin
* @version 1.0
* @date 2021-06-01
 * Copyright © MyCompany
*/
@Getter @Setter @Accessors(chain = true)
public class SldzAdminDTO extends SldzAdmin  {
    private static final long serialVersionUID = -7920912525981790449L;

}