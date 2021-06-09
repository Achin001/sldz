package com.gxc.sldz.service.impl;

import com.diboot.core.util.BeanUtils;
import com.gxc.sldz.entity.SldzAgentRel;
import com.gxc.sldz.mapper.SldzAgentRelMapper;
import com.gxc.sldz.service.SldzAgentRelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* 代理商关系表相关Service实现
* @author Achin
* @version 1.0
* @date 2021-06-02
 * Copyright © MyCompany
*/
@Service
@Slf4j
public class SldzAgentRelServiceImpl extends BaseCustomServiceImpl<SldzAgentRelMapper, SldzAgentRel> implements SldzAgentRelService {


    @Autowired
    SldzAgentRelMapper SldzAgentRelMapper;


    @Override
    public List<SldzAgentRel> SldzAgentRels(String sup_random) {
        return SldzAgentRelMapper.SldzAgentRels(sup_random);
    }

    @Override
    public SldzAgentRel sub_find_sup(String sub_random) {
        return SldzAgentRelMapper.sub_find_sup(sub_random);
    }

    @Override
    public SldzAgentRel sub_find_supsup(String sub_random) {
        return SldzAgentRelMapper.sub_find_supsup(sub_random);
    }


}
