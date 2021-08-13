package com.gxc.sldz.mapper;

import com.diboot.core.mapper.BaseCrudMapper;
import com.gxc.sldz.entity.SldzAgentRel;
import com.gxc.sldz.entity.SldzUserRel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
* 用户绑定Mapper
* @author Achin
* @version 1.0
* @date 2021-07-15
 * Copyright © MyCompany
*/
@Mapper
public interface SldzUserRelMapper extends BaseCrudMapper<SldzUserRel> {

    //根据本级编号查出上级编号
    @Select("SELECT * FROM sldz_user_rel WHERE sub_random  = #{sub_random}")
    SldzUserRel sub_find_sup (@Param("sub_random") String sub_random);

}

