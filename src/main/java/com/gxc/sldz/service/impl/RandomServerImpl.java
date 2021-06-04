package com.gxc.sldz.service.impl;


import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gxc.sldz.Utils.StringRandomUtil;
import com.gxc.sldz.entity.SldzAgent;
import com.gxc.sldz.entity.SldzUser;
import com.gxc.sldz.service.RandomServer;
import com.gxc.sldz.service.SldzAgentService;
import com.gxc.sldz.service.SldzUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RandomServerImpl implements RandomServer {


    @Autowired
    private SldzUserService SldzUserService;

    @Autowired
    private SldzAgentService sldzAgentService;



    @Override
    public String getRandom() {
       String Random =  StringRandomUtil.getStringRandom();
        //查询 agent 表有无该编码
        LambdaQueryWrapper<SldzAgent> wrapperAgent = new LambdaQueryWrapper<>();
        wrapperAgent.eq(SldzAgent::getAgentRandom, Random);
        SldzAgent SldzAgent =  sldzAgentService.getSingleEntity(wrapperAgent);
        if (ObjectUtil.isNull(SldzAgent)){
            //查询 user 表有无该编码
            LambdaQueryWrapper<SldzUser> wrapperUser = new LambdaQueryWrapper<>();
            wrapperUser.eq(SldzUser::getRandom, Random);
            SldzUser SldzUser =  SldzUserService.getSingleEntity(wrapperUser);
            if (ObjectUtil.isNull(SldzUser)){
                return Random;
            }
        }
        return getRandom();
    }



}
