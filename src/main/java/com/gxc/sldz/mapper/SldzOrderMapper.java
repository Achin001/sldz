package com.gxc.sldz.mapper;

import com.diboot.core.mapper.BaseCrudMapper;
import com.gxc.sldz.entity.SldzOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
* 订单Mapper
* @author Achin
* @version 1.0
* @date 2021-06-22
 * Copyright © MyCompany
*/
@Mapper
public interface SldzOrderMapper extends BaseCrudMapper<SldzOrder> {


    @Update("UPDATE sldz_order SET addres_json = #{addresJson}  WHERE order_number = #{orderNumber} " +
            "and buyers_random =#{Random} ")
    boolean ChangeShippingAddress(@Param("addresJson") String addresJson,
                                  @Param("orderNumber") String orderNumber,
                                  @Param("Random") String Random);


    @Update("UPDATE sldz_order SET buyers_remarks = #{Remarks} WHERE order_number = #{orderNumber}")
    boolean AddOrderNotesByOrderNum(@Param("Remarks") String Remarks,
                                  @Param("orderNumber") String orderNumber);



    //改订单状态  待收货  记录时间 把支付方式改成相应的 记录实际支付
    @Update("UPDATE sldz_order SET  " +
            "payment_method = #{paymentMethod}," +
            "amount_actually_paid = #{amountActuallyPaid}," +
            "state = 2 ," +
            "payment_time = #{paymentTime}" +
            "WHERE  order_number =#{orderNumber}")
    boolean ChangeOrderSigned(@Param("paymentMethod") int paymentMethod,
                              @Param("amountActuallyPaid") double amountActuallyPaid,
                              @Param("paymentTime") String paymentTime,
                              @Param("orderNumber") String orderNumber);


    //改订单状态  待收货  记录时间 把支付方式改成相应的 记录实际支付 记录微信支付流水号
    @Update("UPDATE sldz_order SET " +
            "payment_method = #{paymentMethod}," +
            "amount_actually_paid = #{amountActuallyPaid}," +
            "state = 2 ," +
            "payment_time = #{paymentTime}," +
            "wx_pay_serial_num = #{wxPaySerialNum} " +
            "WHERE  order_number =#{orderNumber}")
    boolean ChangeOrderSigneds(@Param("paymentMethod") int paymentMethod,
                              @Param("amountActuallyPaid") double amountActuallyPaid,
                              @Param("paymentTime") String paymentTime,
                               @Param("wxPaySerialNum") String wxPaySerialNum,
                              @Param("orderNumber") String orderNumber);




    //改订单状态4  售后
    @Update("UPDATE sldz_order SET state = 4  WHERE  order_number =#{orderNumber}")
    boolean ChangeOrderAfterSales(@Param("orderNumber") String orderNumber);

    //改订单奖励金发放状态 改成 已发放
    @Update("UPDATE sldz_order SET commission_payment = 2  WHERE  order_number =#{orderNumber} and commission_payment = 1")
    boolean AwardCompletedCrdersPreviewGrant(@Param("orderNumber") String orderNumber);

    //获取未发货的数量
    @Select("SELECT COUNT(*) FROM sldz_order WHERE state = 2 AND logistics_number is NULL OR logistics_number ='' ")
    Integer ChangeLogisticsNumber();


    //获取已发货的订单
    @Select("SELECT * FROM sldz_order WHERE state = 2 AND logistics_number is not null")
    List<SldzOrder> GetOrderBeenDelivered();



    //根据订单号获取订单
    @Select("SELECT * FROM sldz_order WHERE  order_number =#{orderNumber}")
    SldzOrder GetOrderAccordingByOrderNumber(@Param("orderNumber") String orderNumber);




}

