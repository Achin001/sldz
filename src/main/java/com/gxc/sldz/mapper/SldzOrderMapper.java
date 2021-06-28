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


}

