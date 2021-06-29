package com.gxc.sldz.service.impl;

import com.diboot.core.util.BeanUtils;
import com.gxc.sldz.entity.SldzUser;
import com.gxc.sldz.mapper.SldzUserMapper;
import com.gxc.sldz.service.SldzUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* 用户相关Service实现
* @author Achin
* @version 1.0
* @date 2021-05-31
 * Copyright © MyCompany
*/
@Service
@Slf4j
public class SldzUserServiceImpl extends BaseCustomServiceImpl<SldzUserMapper, SldzUser> implements SldzUserService {


    @Autowired
    SldzUserMapper SldzUserService;



    @Override
    public boolean ChangePoints(double integral, String random) {
        return SldzUserService.ChangePoints(integral,random);
    }

    @Override
    public boolean ChangeBonus(double bonus, String random) {
        return SldzUserService.ChangeBonus(bonus,random);
    }




}
