package com.gxc.sldz.service;

import com.diboot.core.vo.JsonResult;
import com.gxc.sldz.entity.SldzOrder;
import com.gxc.sldz.vo.OrderRewardDueVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    //获取已发货的订单
    List<SldzOrder> GetOrderBeenDelivered();


    //已完成订单发放奖励金-预览
    JsonResult AwardCompletedCrdersPreview(String orderNumber);

    //已完成订单发放奖励金-发放
    JsonResult AwardCompletedCrdersPreviewGrant(String orderNumber,List<OrderRewardDueVo> OrderRewardDueVo);

    //已完成订单发放奖励金-查询
    JsonResult AwardCompletedCrdersPreviewQuery(String orderNumber);

}