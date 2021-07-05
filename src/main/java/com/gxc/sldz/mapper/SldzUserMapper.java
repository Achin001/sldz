package com.gxc.sldz.mapper;

import com.diboot.core.mapper.BaseCrudMapper;
import com.gxc.sldz.entity.SldzUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
* 用户Mapper
* @author Achin
* @version 1.0
* @date 2021-05-31
 * Copyright © MyCompany
*/
@Mapper
public interface SldzUserMapper extends BaseCrudMapper<SldzUser> {


    //改积分
    @Update("UPDATE sldz_user  SET integral=#{integral} WHERE random = #{random}")
    boolean ChangePoints(@Param("integral") double integral,
                         @Param("random") String random);

    //改奖励金
    @Update("UPDATE sldz_user  SET bonus =#{bonus} WHERE random = #{random}")
    boolean ChangeBonus(@Param("bonus") double bonus,
                         @Param("random") String random);


}

