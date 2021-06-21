package com.gxc.sldz.service;

import com.gxc.sldz.entity.SldzShopCart;
import org.apache.ibatis.annotations.Param;

import java.util.Random;

/**
* 购物车相关Service
* @author Achin
* @version 1.0
* @date 2021-06-18
 * Copyright © MyCompany
*/
public interface SldzShopCartService extends BaseCustomService<SldzShopCart> {



    boolean cartNumPlus(String Random,
                        long id);

    boolean cartNumReduce(String Random,
                          long id);

    String cartNumByRandom(String Random);

}