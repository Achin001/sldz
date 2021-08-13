package com.gxc.sldz.service.impl;

import com.diboot.core.util.BeanUtils;
import com.gxc.sldz.entity.SldzAgentRel;
import com.gxc.sldz.entity.SldzUserRel;
import com.gxc.sldz.mapper.SldzUserRelMapper;
import com.gxc.sldz.service.SldzUserRelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* 用户绑定相关Service实现
* @author Achin
* @version 1.0
* @date 2021-07-15
 * Copyright © MyCompany
*/
@Service
@Slf4j
public class SldzUserRelServiceImpl extends BaseCustomServiceImpl<SldzUserRelMapper, SldzUserRel> implements SldzUserRelService {

    @Autowired
    SldzUserRelMapper SldzUserRelMapper;

    @Override
    public SldzUserRel sub_find_sup(String sub_random) {
        return SldzUserRelMapper.sub_find_sup(sub_random);
    }
}
