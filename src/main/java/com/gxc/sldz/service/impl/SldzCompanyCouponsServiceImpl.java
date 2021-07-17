package com.gxc.sldz.service.impl;

import cn.hutool.core.date.DateUtil;
import com.diboot.core.util.BeanUtils;
import com.diboot.core.vo.JsonResult;
import com.gxc.sldz.entity.SldzCompanyCoupons;
import com.gxc.sldz.mapper.SldzCompanyCouponsMapper;
import com.gxc.sldz.service.SldzCompanyCouponsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
* 公司优惠券相关Service实现
* @author Achin
* @version 1.0
* @date 2021-06-22
 * Copyright © MyCompany
*/
@Service
@Slf4j
public class SldzCompanyCouponsServiceImpl extends BaseCustomServiceImpl<SldzCompanyCouponsMapper, SldzCompanyCoupons> implements SldzCompanyCouponsService {

    @Autowired
    SldzCompanyCouponsMapper SldzCompanyCouponsMapper;

    @Override
    public void DeleteExpiredSldzCompanyCoupons() {
        List<SldzCompanyCoupons> SldzCompanyCouponss =  SldzCompanyCouponsMapper.SldzCompanyCouponss();
        for (SldzCompanyCoupons s: SldzCompanyCouponss){
            //结束时间
            Date d1 =  s.getCouponsFailureTime();
            //现在时间
            Date d2 = DateUtil.date();
            //这样得到的差值是毫秒级别
            long diff = d1.getTime() - d2.getTime();
            //转换成秒
            diff = diff/1000;
            if (diff<0){
                SldzCompanyCouponsMapper.deleteById(s.getId());
            }
            if(s.getCouponsTotal() <= 0){
                SldzCompanyCouponsMapper.deleteById(s.getId());
            }

        }
    }


}
