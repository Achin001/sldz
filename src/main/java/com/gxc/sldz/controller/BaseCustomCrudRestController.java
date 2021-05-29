package com.gxc.sldz.controller;

import com.diboot.core.controller.BaseCrudRestController;
import com.diboot.core.entity.BaseEntity;
import lombok.extern.slf4j.Slf4j;

/**
* 自定义通用CRUD父类RestController
* @author Achin
* @version 1.0
* @date 2021-05-19
* Copyright © MyCompany
*/
@Slf4j
public class BaseCustomCrudRestController<E extends BaseEntity> extends BaseCrudRestController {

}