package com.gxc.sldz.mapper;

import com.diboot.core.mapper.BaseCrudMapper;
import com.diboot.core.util.S;
import com.gxc.sldz.entity.SldzAgent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
* 代理商Mapper
* @author Achin
* @version 1.0
* @date 2021-05-31
 * Copyright © MyCompany
*/
@Mapper
public interface SldzAgentMapper extends BaseCrudMapper<SldzAgent> {



    @Update("UPDATE sldz_agent SET agent_integral = agent_integral+#{integral} WHERE agent_random = #{Random}")
    boolean RechargeByRandom(@Param("integral") double integral, @Param("Random") String Random);
}

