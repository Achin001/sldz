package com.gxc.sldz.service.impl;

import cn.hutool.core.util.NumberUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.diboot.core.util.BeanUtils;
import com.diboot.core.vo.JsonResult;
import com.gxc.sldz.Utils.OrderUtil;
import com.gxc.sldz.Utils.RedisUtils;
import com.gxc.sldz.controller.API.SldzOrderApi;
import com.gxc.sldz.entity.SldzOrder;
import com.gxc.sldz.mapper.SldzOrderMapper;
import com.gxc.sldz.service.SldzOrderService;
import com.gxc.sldz.vo.OrderProductJsonVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 订单相关Service实现
 *
 * @author Achin
 * @version 1.0
 * @date 2021-06-22
 * Copyright © MyCompany
 */
@Service
@Slf4j
public class SldzOrderServiceImpl extends BaseCustomServiceImpl<SldzOrderMapper, SldzOrder> implements SldzOrderService {

    @Autowired
    SldzOrderMapper SldzOrderMapper;


    @Autowired
    private RedisUtils redisUtils;

    @Override
    public boolean ChangeShippingAddress(String addresJson, String orderNumber, String Random) {
        return SldzOrderMapper.ChangeShippingAddress(addresJson, orderNumber, Random);
    }

    @Override
    public boolean AddOrderNotesByOrderNum(String Remarks, String orderNumber) {
        return SldzOrderMapper.AddOrderNotesByOrderNum(Remarks, orderNumber);
    }

    @Override
    public JsonResult ObtainCouponsAccordingOrderProducts(SldzOrder SldzOrder) {
        //可用优惠券列表
        List<String> availableCoupons = new ArrayList<>();


        //唯一编码
        SldzOrder.getBuyersRandom();
        //得到订单里的产品id 价格 数量
        List<OrderProductJsonVo> OrderProductJsonVo = OrderUtil.getOrderProductJsonVo(SldzOrder.getProductJson());


        //从缓存拿出该人的优惠券列表
        String key = SldzOrder.getBuyersRandom() + "_coupon" + "*";
        Set<String> CouponList = redisUtils.getByKeys(key);
//        拿出各组优惠券
        for (String Coupon : CouponList) {
            //接收符合优惠的Vo
            List<OrderProductJsonVo> OrderProductJsonVos = new ArrayList<>();
            //获取优惠券里面的满足条件
            double GetConditionsCoupon = Double.parseDouble(OrderUtil.GetConditionsCoupon(Coupon));

            //得到优惠的产品id
            List<Long> CouponPrductId = OrderUtil.GetProductIdByCoupon(Coupon);
            for (Long OrderProductid : CouponPrductId) {
                for (OrderProductJsonVo OrderProductJson : OrderProductJsonVo) {
                    if (OrderProductid == OrderProductJson.getProductId()) {
                        OrderProductJsonVos.add(OrderProductJson);
                    }
                }
            }
            double apoq = 0;
            for (OrderProductJsonVo orderProductJsonVo : OrderProductJsonVos) {
                apoq+= NumberUtil.mul(orderProductJsonVo.getProductPrice(),orderProductJsonVo.getCartNum());
            }

            //判断总金额是否满足满减
            if (GetConditionsCoupon <= apoq){
                availableCoupons.add(Coupon);
            }

        }
        return JsonResult.OK().data(availableCoupons);
    }

    @Override
    public JsonResult orderPay(SldzOrder SldzOrder, int paymentMethod) {

        if (paymentMethod == 1){
            //微信支付
        }else if(paymentMethod == 2){
            //积分支付

        }else if (paymentMethod == 3){
            //奖励金支付
        }


        return null;
    }


}
