package com.gxc.sldz.mapper;

import com.diboot.core.mapper.BaseCrudMapper;
import com.gxc.sldz.entity.SldzOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

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
    @Update("\n" +
            "UPDATE sldz_order SET  " +
            "payment_method = #{paymentMethod}," +
            "amount_actually_paid = #{amountActuallyPaid}," +
            "state = 2 ," +
            "payment_time = #{paymentTime}" +
            "wx_pay_serial_num = #{wxPaySerialNum}" +
            "WHERE  order_number =#{orderNumber}\n")
    boolean ChangeOrderSigneds(@Param("paymentMethod") int paymentMethod,
                              @Param("amountActuallyPaid") double amountActuallyPaid,
                              @Param("paymentTime") String paymentTime,
                               @Param("wxPaySerialNum") String wxPaySerialNum,
                              @Param("orderNumber") String orderNumber);




}

