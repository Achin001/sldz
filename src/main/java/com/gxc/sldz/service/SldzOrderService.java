package com.gxc.sldz.service;

import com.diboot.core.vo.JsonResult;
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


    boolean AddOrderNotesByOrderNum(@Param("Remarks") String Remarks,
                                    @Param("orderNumber") String orderNumber);

    boolean ChangeOrderSigneds(@Param("paymentMethod") int paymentMethod,
                               @Param("amountActuallyPaid") double amountActuallyPaid,
                               @Param("paymentTime") String paymentTime,
                               @Param("wxPaySerialNum") String wxPaySerialNum,
                               @Param("orderNumber") String orderNumber);




    JsonResult ObtainCouponsAccordingOrderProducts(SldzOrder SldzOrder);

    JsonResult orderPay(SldzOrder SldzOrder ,int paymentMethod);

    JsonResult UndeliveredRefund(SldzOrder SldzOrder);

    Integer ChangeLogisticsNumber();

}