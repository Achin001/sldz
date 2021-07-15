package com.gxc.sldz.dto;

import com.diboot.core.binding.query.BindQuery;
import com.diboot.core.binding.query.Comparison;
import com.gxc.sldz.entity.SldzBank;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
* 银行卡 DTO定义
* @author Achin
* @version 1.0
* @date 2021-07-15
 * Copyright © MyCompany
*/
@Getter @Setter @Accessors(chain = true)
public class SldzBankDTO extends SldzBank  {
    private static final long serialVersionUID = 7222089989566402061L;

}