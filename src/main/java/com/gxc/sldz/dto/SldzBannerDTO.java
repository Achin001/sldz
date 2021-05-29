package com.gxc.sldz.dto;

import com.diboot.core.binding.query.BindQuery;
import com.diboot.core.binding.query.Comparison;
import com.gxc.sldz.entity.SldzBanner;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
* 轮播图 DTO定义
* @author Achin
* @version 1.0
* @date 2021-05-19
 * Copyright © MyCompany
*/
@Getter @Setter @Accessors(chain = true)
public class SldzBannerDTO extends SldzBanner  {
    private static final long serialVersionUID = 4199687681107697926L;

}