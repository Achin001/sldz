package com.gxc.sldz.Utils;


import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gxc.sldz.entity.SldzAgent;
import com.gxc.sldz.entity.SldzUser;
import com.gxc.sldz.service.SldzAgentService;
import com.gxc.sldz.service.SldzUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class RandomChangeUsers {


    @Autowired
    SldzUserService SldzUserServic;

    @Autowired
    SldzAgentService SldzAgentService;

    public  Map getUser(String random){
        Map map = new HashMap();

        LambdaQueryWrapper<SldzUser> wrapperUser = new LambdaQueryWrapper<>();
        wrapperUser.eq(SldzUser::getRandom,random);
        //根据wrapper 查找对应数据
        SldzUser SldzUser =  SldzUserServic.getSingleEntity(wrapperUser);
        if (ObjectUtil.isNull(SldzUser)){
            LambdaQueryWrapper<SldzAgent> wrapperAgent = new LambdaQueryWrapper<>();
            wrapperAgent.eq(SldzAgent::getAgentRandom,random);
            //根据wrapper 查找对应数据
            SldzAgent SldzAgent =  SldzAgentService.getSingleEntity(wrapperAgent);
            map.put("type",1);//1代表消费者 2代理商
            map.put("SldzAgent",SldzAgent);//1代表消费者 2代理商
            return map;
        }
        map.put("type",1);//1代表消费者 2代理商
        map.put("SldzUser",SldzUser);//1代表消费者 2代理商
        return map;
    }


}
