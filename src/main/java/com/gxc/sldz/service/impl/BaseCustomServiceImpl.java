package com.gxc.sldz.service.impl;

import com.diboot.core.service.impl.BaseServiceImpl;
import com.diboot.core.mapper.BaseCrudMapper;
import com.gxc.sldz.service.BaseCustomService;
import lombok.extern.slf4j.Slf4j;
/**
* 自定义 BaseService 接口实现
* @author Achin
* @version 1.0
* @date 2021-05-19
 * Copyright © MyCompany
*/
@Slf4j
public class BaseCustomServiceImpl<M extends BaseCrudMapper<T>, T> extends BaseServiceImpl<M, T> implements BaseCustomService<T> {

}
