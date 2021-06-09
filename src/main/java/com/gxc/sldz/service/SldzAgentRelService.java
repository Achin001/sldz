package com.gxc.sldz.service;

import com.gxc.sldz.entity.SldzAgentRel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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


    SldzAgentRel sub_find_sup (@Param("sub_random") String sub_random);

    SldzAgentRel sub_find_supsup (@Param("sub_random") String sub_random);

}