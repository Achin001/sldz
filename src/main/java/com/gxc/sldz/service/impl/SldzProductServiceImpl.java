package com.gxc.sldz.service.impl;

import com.diboot.core.util.BeanUtils;
import com.gxc.sldz.entity.SldzProduct;
import com.gxc.sldz.mapper.SldzProductMapper;
import com.gxc.sldz.service.SldzProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* 产品相关Service实现
* @author Achin
* @version 1.0
* @date 2021-05-20
 * Copyright © MyCompany
*/
@Service
@Slf4j
public class SldzProductServiceImpl extends BaseCustomServiceImpl<SldzProductMapper, SldzProduct> implements SldzProductService {



    @Autowired
    SldzProductMapper SldzProductMapper;

    @Override
    public boolean productStockById(long stock, long id) {
        return SldzProductMapper.productStockById(stock,id);
    }

    @Override
    public boolean productStockByIdloa(long stock, long id) {
        return SldzProductMapper.productStockByIdloa(stock,id);
    }
}
