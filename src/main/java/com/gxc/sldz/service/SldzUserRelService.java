package com.gxc.sldz.service;

import com.gxc.sldz.entity.SldzAgentRel;
import com.gxc.sldz.entity.SldzUserRel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
* 用户绑定相关Service
* @author Achin
* @version 1.0
* @date 2021-07-15
 * Copyright © MyCompany
*/
public interface SldzUserRelService extends BaseCustomService<SldzUserRel> {


    SldzUserRel sub_find_sup (@Param("sub_random") String sub_random);

}