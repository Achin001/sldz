package com.gxc.sldz.service;

import com.gxc.sldz.entity.SldzUser;
import org.apache.ibatis.annotations.Param;

/**
* 用户相关Service
* @author Achin
* @version 1.0
* @date 2021-05-31
 * Copyright © MyCompany
*/
public interface SldzUserService extends BaseCustomService<SldzUser> {


    boolean ChangePoints(@Param("integral") double integral,
                         @Param("random") String random);

    boolean ChangeBonus(@Param("bonus") double bonus,
                        @Param("random") String random);

    SldzUser getUserByOpenid(@Param("openid") String openid);

}