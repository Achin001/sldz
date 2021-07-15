package com.gxc.sldz.vo;

import com.diboot.core.binding.annotation.*;
import com.gxc.sldz.entity.SldzBank;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
* 银行卡 DetailVO定义
* @author Achin
* @version 1.0
* @date 2021-07-15
 * Copyright © MyCompany
*/
@Getter @Setter @Accessors(chain = true)
public class SldzBankDetailVO extends SldzBank  {
    private static final long serialVersionUID = 8692012255360371482L;

}