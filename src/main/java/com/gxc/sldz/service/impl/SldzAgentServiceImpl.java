package com.gxc.sldz.service.impl;

import com.diboot.core.util.BeanUtils;
import com.gxc.sldz.entity.SldzAgent;
import com.gxc.sldz.mapper.SldzAgentMapper;
import com.gxc.sldz.service.SldzAgentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.Action;

/**
* 代理商相关Service实现
* @author Achin
* @version 1.0
* @date 2021-05-31
 * Copyright © MyCompany
*/
@Service
@Slf4j
public class SldzAgentServiceImpl extends BaseCustomServiceImpl<SldzAgentMapper, SldzAgent> implements SldzAgentService {


    @Autowired
    SldzAgentMapper SldzAgentMapper;

    @Override
    public boolean RechargeByRandom(double integral, String Random) {
        return SldzAgentMapper.RechargeByRandom(integral,Random);
    }

    @Override
    public boolean PluszBonusByRandom(double bonus, String Random) {
        return SldzAgentMapper.PluszBonusByRandom(bonus,Random);
    }

    @Override
    public boolean ChangePoints(double integral, String random) {
        return SldzAgentMapper.ChangePoints(integral,random);
    }

    @Override
    public boolean ChangeBonus(double bonus, String random) {
        return SldzAgentMapper.ChangeBonus(bonus,random);
    }

    @Override
    public SldzAgent getAgentByagentRandom(String agentRandom) {
        return SldzAgentMapper.getAgentByagentRandom(agentRandom);
    }

    @Override
    public SldzAgent getAgentByOpenid(String openid) {
        return SldzAgentMapper.getAgentByOpenid(openid);
    }
}
