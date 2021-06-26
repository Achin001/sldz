package com.gxc.sldz.service;

import com.gxc.sldz.entity.SldzCompanyCoupons;

import java.io.Serializable;
import java.util.List;

/**
* 公司优惠券相关Service
* @author Achin
* @version 1.0
* @date 2021-06-22
 * Copyright © MyCompany
*/
public interface SldzCompanyCouponsService extends BaseCustomService<SldzCompanyCoupons> {


    void DeleteExpiredSldzCompanyCoupons();
}