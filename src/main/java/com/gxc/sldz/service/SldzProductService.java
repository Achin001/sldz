package com.gxc.sldz.service;

import com.gxc.sldz.entity.SldzProduct;
import org.apache.ibatis.annotations.Param;

/**
* 产品相关Service
* @author Achin
* @version 1.0
* @date 2021-05-20
 * Copyright © MyCompany
*/
public interface SldzProductService extends BaseCustomService<SldzProduct> {

    boolean productStockById (@Param("stock") long stock, @Param("id") long id);
}