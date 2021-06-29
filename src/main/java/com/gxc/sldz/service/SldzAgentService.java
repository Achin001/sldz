package com.gxc.sldz.service;

import com.gxc.sldz.entity.SldzAgent;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
* 代理商相关Service
* @author Achin
* @version 1.0
* @date 2021-05-31
 * Copyright © MyCompany
*/
public interface SldzAgentService extends BaseCustomService<SldzAgent> {


    boolean RechargeByRandom(double integral,String Random);

    boolean PluszBonusByRandom(@Param("bonus") double bonus, @Param("Random") String Random);

    boolean ChangePoints(@Param("integral") double integral,
                         @Param("random") String random);

    boolean ChangeBonus(@Param("bonus") double bonus,
                        @Param("random") String random);


}