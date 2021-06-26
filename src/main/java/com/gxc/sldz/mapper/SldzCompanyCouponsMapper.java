package com.gxc.sldz.mapper;

import com.diboot.core.mapper.BaseCrudMapper;
import com.gxc.sldz.entity.SldzCompanyCoupons;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* 公司优惠券Mapper
* @author Achin
* @version 1.0
* @date 2021-06-22
 * Copyright © MyCompany
*/
@Mapper
public interface SldzCompanyCouponsMapper extends BaseCrudMapper<SldzCompanyCoupons> {


    @Select("SELECT * FROM sldz_company_coupons")
    List<SldzCompanyCoupons> SldzCompanyCouponss();


}

