package com.gxc.sldz.service;

import com.gxc.sldz.entity.SldzAgentRel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* 代理商关系表相关Service
* @author Achin
* @version 1.0
* @date 2021-06-02
 * Copyright © MyCompany
*/
public interface SldzAgentRelService extends BaseCustomService<SldzAgentRel> {


    List<SldzAgentRel> SldzAgentRels (@Param("sup_random") String sup_random);


}