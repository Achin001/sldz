package com.gxc.sldz.mapper;

import com.diboot.core.mapper.BaseCrudMapper;
import com.gxc.sldz.entity.SldzAgentRel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* 代理商关系表Mapper
* @author Achin
* @version 1.0
* @date 2021-06-02
 * Copyright © MyCompany
*/
@Mapper
public interface SldzAgentRelMapper extends BaseCrudMapper<SldzAgentRel> {



    //根据一级编号查询出所有二级
    @Select("SELECT * FROM sldz_agent_rel WHERE sup_random IN(SELECT sub_random FROM sldz_agent_rel WHERE sup_random = #{sup_random})")
    List<SldzAgentRel> SldzAgentRels (@Param("sup_random") String sup_random);


    //根据二级编号查出上级编号
    @Select("SELECT * FROM sldz_agent_rel WHERE sub_random  = #{sub_random}")
    SldzAgentRel sub_find_sup (@Param("sub_random") String sub_random);

    //根据二级编号查出上上级编号
    @Select("SELECT * FROM sldz_agent_rel WHERE sub_random  IN (SELECT sup_random FROM sldz_agent_rel WHERE sub_random = #{sub_random})")
    SldzAgentRel sub_find_supsup (@Param("sub_random") String sub_random);
}

