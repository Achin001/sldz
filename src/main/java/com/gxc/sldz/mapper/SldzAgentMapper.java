package com.gxc.sldz.mapper;

import com.diboot.core.mapper.BaseCrudMapper;
import com.diboot.core.util.S;
import com.gxc.sldz.entity.SldzAgent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
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



    //加积分
    @Update("UPDATE sldz_agent SET agent_integral = agent_integral+#{integral} WHERE agent_random = #{Random}")
    boolean RechargeByRandom(@Param("integral") double integral, @Param("Random") String Random);


//    加奖励金
    @Update("UPDATE sldz_agent SET agent_bonus = agent_bonus+ #{bonus} WHERE agent_random = #{Random}")
    boolean PluszBonusByRandom(@Param("bonus") double bonus, @Param("Random") String Random);






    //改积分
    @Update("UPDATE sldz_agent  SET agent_integral=#{integral} WHERE agent_random = #{random}")
    boolean ChangePoints(@Param("integral") double integral,
                         @Param("random") String random);

    //改奖励金
    @Update("UPDATE sldz_agent  SET agent_bonus=#{bonus} WHERE agent_random = #{random}")
    boolean ChangeBonus(@Param("bonus") double bonus,
                        @Param("random") String random);


    @Select("SELECT * FROM sldz_agent WHERE  agent_random = #{agentRandom}")
    SldzAgent getAgentByagentRandom(@Param("agentRandom") String agentRandom);

    @Select("SELECT * FROM sldz_agent WHERE  openid = #{openid}")
    SldzAgent getAgentByOpenid(@Param("openid") String openid);


}

