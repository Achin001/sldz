package com.gxc.sldz.service;

import com.gxc.sldz.entity.SldzOrder;
import org.apache.ibatis.annotations.Param;

/**
* 订单相关Service
* @author Achin
* @version 1.0
* @date 2021-06-22
 * Copyright © MyCompany
*/
public interface SldzOrderService extends BaseCustomService<SldzOrder> {



    boolean ChangeShippingAddress(@Param("addresJson") String addresJson,
                                     @Param("orderNumber") String orderNumber,
                                  @Param("Random") String Random);

}