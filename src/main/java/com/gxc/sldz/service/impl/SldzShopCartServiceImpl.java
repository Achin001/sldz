package com.gxc.sldz.service.impl;

import com.diboot.core.util.BeanUtils;
import com.gxc.sldz.entity.SldzShopCart;
import com.gxc.sldz.mapper.SldzShopCartMapper;
import com.gxc.sldz.service.SldzShopCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* 购物车相关Service实现
* @author Achin
* @version 1.0
* @date 2021-06-18
 * Copyright © MyCompany
*/
@Service
@Slf4j
public class SldzShopCartServiceImpl extends BaseCustomServiceImpl<SldzShopCartMapper, SldzShopCart> implements SldzShopCartService {


    @Autowired
    SldzShopCartMapper SldzShopCartMapper;

    @Override
    public boolean cartNumPlus(String Random, long id) {
        return SldzShopCartMapper.cartNumPlus(Random,id);
    }

    @Override
    public boolean cartNumReduce(String Random, long id) {
        return SldzShopCartMapper.cartNumReduce(Random,id);
    }

    @Override
    public String cartNumByRandom(String Random) {
        return SldzShopCartMapper.cartNumByRandom(Random);
    }


}
