package com.gxc.sldz.service.impl;

import com.diboot.core.util.BeanUtils;
import com.gxc.sldz.entity.SldzOrder;
import com.gxc.sldz.mapper.SldzOrderMapper;
import com.gxc.sldz.service.SldzOrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* 订单相关Service实现
* @author Achin
* @version 1.0
* @date 2021-06-22
 * Copyright © MyCompany
*/
@Service
@Slf4j
public class SldzOrderServiceImpl extends BaseCustomServiceImpl<SldzOrderMapper, SldzOrder> implements SldzOrderService {

    @Autowired
    SldzOrderMapper SldzOrderMapper;


    @Override
    public boolean ChangeShippingAddress(String addresJson, String orderNumber, String Random) {
        return SldzOrderMapper.ChangeShippingAddress(addresJson,orderNumber,Random);
    }
}
