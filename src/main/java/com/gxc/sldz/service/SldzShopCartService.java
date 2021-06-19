package com.gxc.sldz.service;

import com.gxc.sldz.entity.SldzShopCart;
import org.apache.ibatis.annotations.Param;

/**
* 购物车相关Service
* @author Achin
* @version 1.0
* @date 2021-06-18
 * Copyright © MyCompany
*/
public interface SldzShopCartService extends BaseCustomService<SldzShopCart> {



    boolean cartNumPlus(@Param("Random") String Random,
                        @Param("id") long id);

    boolean cartNumReduce(@Param("Random") String Random,
                          @Param("id") long id);

}