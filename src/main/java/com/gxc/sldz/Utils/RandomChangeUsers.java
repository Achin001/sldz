package com.gxc.sldz.Utils;


import com.gxc.sldz.service.SldzAgentService;
import com.gxc.sldz.service.SldzUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RandomChangeUsers {


    @Autowired
    SldzUserService SldzUserServic;

    @Autowired
    SldzAgentService SldzAgentService;

    public Object getUser(){

        return null;
    }


}
