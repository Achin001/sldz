package com.gxc.sldz.vo;

import com.diboot.core.binding.annotation.*;
import com.gxc.sldz.entity.SldzBank;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
* 银行卡 ListVO定义
* @author Achin
* @version 1.0
* @date 2021-07-15
 * Copyright © MyCompany
*/
@Getter @Setter @Accessors(chain = true)
public class SldzBankListVO extends SldzBank  {
    private static final long serialVersionUID = -6214167176043675720L;

}