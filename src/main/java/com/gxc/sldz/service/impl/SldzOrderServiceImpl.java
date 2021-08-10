package com.gxc.sldz.service.impl;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.diboot.core.vo.JsonResult;
import com.gxc.sldz.Utils.OrderUtil;
import com.gxc.sldz.Utils.RedisUtils;
import com.gxc.sldz.entity.*;
import com.gxc.sldz.mapper.SldzOrderMapper;
import com.gxc.sldz.service.*;
import com.gxc.sldz.vo.OrderProductJsonVo;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import com.lly835.bestpay.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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


    //微信支付服务
    @Autowired
    private BestPayServiceImpl bestPayService;

    //奖励金记录服务
    @Autowired
    SldzAgentBonusLogService SldzAgentBonusLogService;
    //订单服务
    @Autowired
    SldzOrderMapper SldzOrderMapper;
    //用户服务
    @Autowired
    SldzUserService SldzUserServic;
    //代理商服务
    @Autowired
    SldzAgentService SldzAgentService;
    //积分记录服务
    @Autowired
    SldzAgentIntegralLogService sldzAgentIntegralLogService;
    //产品服务
    @Autowired
    SldzProductService SldzProductService;

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
    public boolean ChangeOrderSigneds(int paymentMethod, double amountActuallyPaid, String paymentTime, String wxPaySerialNum, String orderNumber) {
        return SldzOrderMapper.ChangeOrderSigneds(paymentMethod, amountActuallyPaid, paymentTime, orderNumber, wxPaySerialNum);
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
                apoq += NumberUtil.mul(orderProductJsonVo.getProductPrice(), orderProductJsonVo.getCartNum());
            }

            //判断总金额是否满足满减
            if (GetConditionsCoupon <= apoq) {
                availableCoupons.add(Coupon);
            }

        }
        return JsonResult.OK().data(availableCoupons);
    }

    @Transactional
    @Override
    public JsonResult orderPay(SldzOrder SldzOrder, int paymentMethod) {
        Map map = getUser(SldzOrder.getBuyersRandom());
        int type = (int) map.get("type");
        if (type == 1) {      //消费者
            SldzUser SldzUser = (SldzUser) map.get("SldzUser");

            //扣除金额
            if (paymentMethod == 1) {
                //微信支付
                PayRequest payRequest = new PayRequest();
                payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_MINI);
                payRequest.setOrderId(SldzOrder.getOrderNumber());
                payRequest.setOrderName("私脸小程序支付订单");
                payRequest.setOrderAmount(0.01);
                payRequest.setOpenid(SldzUser.getOpenid());
                PayResponse payResponse = bestPayService.pay(payRequest);
                //删除优惠券
                deleterCoupon(SldzOrder);
                return JsonResult.OK().data(payResponse);
            } else if (paymentMethod == 2) {
                //剩余积分
                double ResidualIntegral = 0.00;
                //积分
                double Integral = SldzUser.getIntegral();
                //应付金额
                double AmountPayable = SldzOrder.getAmountPayable();
                if (Integral < AmountPayable) {
                    return JsonResult.FAIL_OPERATION("积分余额不足,您的积分余额：" + Integral);
                }

                ResidualIntegral = NumberUtil.sub(Integral, AmountPayable);
                //扣积分
                if (SldzUserServic.ChangePoints(ResidualIntegral, SldzUser.getRandom())) {
                    //记录积分消费记录
                    sldzAgentIntegralLogService.createEntity(new SldzAgentIntegralLog()
                            .setAgentRandom(SldzUser.getRandom())
                            .setIntegralDate(DateUtil.now())
                            .setIntegralEvent("消费" + AmountPayable + "积分,订单号:" + SldzOrder.getOrderNumber())
                            .setIntegralMoney(AmountPayable)
                            .setIntegralType(2l));
                    //扣除库存
                    List<OrderProductJsonVo> getOrderProductJsonVo = OrderUtil.getOrderProductJsonVo(SldzOrder.getProductJson());
                    for (OrderProductJsonVo asfssa : getOrderProductJsonVo) {
                        //库存
                        int stock = Math.toIntExact(SldzProductService.getEntity(asfssa.getProductId()).getProductStock());
                        stock = (int) NumberUtil.sub(stock, asfssa.getCartNum());
                        //库存 = 库存 - 购买数量
                        SldzProductService.productStockById(stock, asfssa.getProductId());
                    }
                    //改订单状态  待收货  记录时间
                    SldzOrderMapper.ChangeOrderSigned(paymentMethod, AmountPayable, DateUtil.now(), SldzOrder.getOrderNumber());
                    deleterCoupon(SldzOrder);
                    return JsonResult.OK().data("支付成功，积分扣除：" + AmountPayable);
                }

            } else if (paymentMethod == 3) {
                //奖励金支付
                //剩余奖励金
                double RemainingBonus = 0.00;
                //奖励金
                double Bonus = SldzUser.getBonus();
                //应付金额
                double AmountPayable = SldzOrder.getAmountPayable();
                if (Bonus < AmountPayable) {
                    return JsonResult.FAIL_OPERATION("奖励金余额不足,您的奖励金余额：" + Bonus);
                }

                RemainingBonus = NumberUtil.sub(Bonus, AmountPayable);
                //扣除奖励金
                if (SldzUserServic.ChangeBonus(RemainingBonus, SldzUser.getRandom())) {
                    //记录奖励金消费记录
                    SldzAgentBonusLogService.createEntity(new SldzAgentBonusLog()
                            .setAgentRandom(SldzUser.getRandom())
                            .setRonusDate(DateUtil.now())
                            .setRonusEvent("消费" + AmountPayable + "积分,订单号:" + SldzOrder.getOrderNumber())
                            .setRonusMoney(AmountPayable)
                            .setRonusType(2l));
                    //扣除库存
                    List<OrderProductJsonVo> getOrderProductJsonVo = OrderUtil.getOrderProductJsonVo(SldzOrder.getProductJson());
                    for (OrderProductJsonVo asfssa : getOrderProductJsonVo) {
                        //库存
                        int stock = Math.toIntExact(SldzProductService.getEntity(asfssa.getProductId()).getProductStock());
                        stock = (int) NumberUtil.sub(stock, asfssa.getCartNum());
                        //库存 = 库存 - 购买数量
                        SldzProductService.productStockById(stock, asfssa.getProductId());
                    }
                    //改订单状态  待收货  记录时间
                    SldzOrderMapper.ChangeOrderSigned(paymentMethod, AmountPayable, DateUtil.now(), SldzOrder.getOrderNumber());
                    deleterCoupon(SldzOrder);
                    return JsonResult.OK().data("支付成功，奖励金扣除：" + AmountPayable);
                }


            }
        } else if (type == 2) {//代理商
            SldzAgent SldzAgen = (SldzAgent) map.get("SldzAgent");
            if (paymentMethod == 1) {
                //微信支付
                PayRequest payRequest = new PayRequest();
                payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_MINI);
                payRequest.setOrderId(SldzOrder.getOrderNumber());
                payRequest.setOrderName("私脸小程序支付订单");
                payRequest.setOrderAmount(0.01);
                payRequest.setOpenid(SldzAgen.getOpenid());
                PayResponse payResponse = bestPayService.pay(payRequest);
                deleterCoupon(SldzOrder);
                return JsonResult.OK().data(payResponse);
            } else if (paymentMethod == 2) {
                //积分支付
                //剩余积分
                double ResidualIntegral = 0.00;
                //积分
                double Integral = SldzAgen.getAgentIntegral();
                //应付金额
                double AmountPayable = SldzOrder.getAmountPayable();
                if (Integral < AmountPayable) {
                    return JsonResult.FAIL_OPERATION("积分余额不足,您的积分余额：" + Integral);
                }

                ResidualIntegral = NumberUtil.sub(Integral, AmountPayable);
                //扣积分
                if (SldzAgentService.ChangePoints(ResidualIntegral, SldzAgen.getAgentRandom())) {
                    //记录积分消费记录
                    sldzAgentIntegralLogService.createEntity(new SldzAgentIntegralLog()
                            .setAgentRandom(SldzAgen.getAgentRandom())
                            .setIntegralDate(DateUtil.now())
                            .setIntegralEvent("消费" + AmountPayable + "积分,订单号:" + SldzOrder.getOrderNumber())
                            .setIntegralMoney(AmountPayable)
                            .setIntegralType(2l));
                    //扣除库存
                    List<OrderProductJsonVo> getOrderProductJsonVo = OrderUtil.getOrderProductJsonVo(SldzOrder.getProductJson());
                    for (OrderProductJsonVo asfssa : getOrderProductJsonVo) {
                        //库存
                        int stock = Math.toIntExact(SldzProductService.getEntity(asfssa.getProductId()).getProductStock());
                        stock = (int) NumberUtil.sub(stock, asfssa.getCartNum());
                        //库存 = 库存 - 购买数量
                        SldzProductService.productStockById(stock, asfssa.getProductId());
                    }
                    //改订单状态  待收货  记录时间
                    SldzOrderMapper.ChangeOrderSigned(paymentMethod, AmountPayable, DateUtil.now(), SldzOrder.getOrderNumber());
                    deleterCoupon(SldzOrder);
                    return JsonResult.OK().data("支付成功，积分扣除：" + AmountPayable);
                }

            } else if (paymentMethod == 3) {
                //奖励金支付
                //剩余奖励金
                double RemainingBonus = 0.00;
                //奖励金
                double Bonus = SldzAgen.getAgentBonus();
                //应付金额
                double AmountPayable = SldzOrder.getAmountPayable();
                if (Bonus < AmountPayable) {
                    return JsonResult.FAIL_OPERATION("奖励金余额不足,您的奖励金余额：" + Bonus);
                }


                RemainingBonus = NumberUtil.sub(Bonus, AmountPayable);
                //扣除奖励金
                if (SldzUserServic.ChangeBonus(RemainingBonus, SldzAgen.getAgentRandom())) {
                    //记录奖励金消费记录
                    SldzAgentBonusLogService.createEntity(new SldzAgentBonusLog()
                            .setAgentRandom(SldzAgen.getAgentRandom())
                            .setRonusDate(DateUtil.now())
                            .setRonusEvent("消费" + AmountPayable + "积分,订单号:" + SldzOrder.getOrderNumber())
                            .setRonusMoney(AmountPayable)
                            .setRonusType(2l));
                    //扣除库存
                    List<OrderProductJsonVo> getOrderProductJsonVo = OrderUtil.getOrderProductJsonVo(SldzOrder.getProductJson());
                    for (OrderProductJsonVo asfssa : getOrderProductJsonVo) {
                        //库存
                        int stock = Math.toIntExact(SldzProductService.getEntity(asfssa.getProductId()).getProductStock());
                        stock = (int) NumberUtil.sub(stock, asfssa.getCartNum());
                        //库存 = 库存 - 购买数量
                        SldzProductService.productStockById(stock, asfssa.getProductId());
                    }
                    //改订单状态  待收货  记录时间 把支付方式改成相应的 记录实际支付
                    SldzOrderMapper.ChangeOrderSigned(paymentMethod, AmountPayable, DateUtil.now(), SldzOrder.getOrderNumber());
                    deleterCoupon(SldzOrder);
                    return JsonResult.OK().data("支付成功，奖励金扣除：" + AmountPayable);
                }


            }
        }


        return null;
    }

    @Transactional
    @Override
    public JsonResult UndeliveredRefund(SldzOrder SldzOrder) {
        String lo = SldzOrder.getLogisticsNumber();

        if (lo == null || "".equals(lo) || "null".equals(lo)) {
            lo = "";
        }
        //如果运单号为空允许退款
        if (StrUtil.isNotBlank(lo)) {
            return JsonResult.FAIL_OPERATION("退款失败").data("该订单已发货，不支持退款");
        }
        Map map = getUser(SldzOrder.getBuyersRandom());
        int type = (int) map.get("type");
        if (type == 1) {
            //消费者
            SldzUser SldzUser = (SldzUser) map.get("SldzUser");
            //退款流程 加回积分/奖励金/微信
            if (SldzOrder.getPaymentMethod() == 1) {
                //微信支付
                RefundRequest refundRequest = new RefundRequest();
                refundRequest.setOrderId(SldzOrder.getOrderNumber());
                refundRequest.setOrderAmount(SldzOrder.getAmountActuallyPaid());
                refundRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_MINI);
                log.info("【微信退款】request={}", JsonUtil.toJson(refundRequest));
                RefundResponse refundResponse = bestPayService.refund(refundRequest);
                log.info("【微信退款】response={}", JsonUtil.toJson(refundResponse));
                //订单改为退款售后
                SldzOrderMapper.ChangeOrderAfterSales(SldzOrder.getOrderNumber());
                //库存加回
                List<OrderProductJsonVo> getOrderProductJsonVo = OrderUtil.getOrderProductJsonVo(SldzOrder.getProductJson());
                for (OrderProductJsonVo asfssa : getOrderProductJsonVo) {
                    //库存
                    int stock = Math.toIntExact(SldzProductService.getEntity(asfssa.getProductId()).getProductStock());
                    stock = (int) NumberUtil.add(stock, asfssa.getCartNum());
                    //库存 = 库存 + 购买数量
                    SldzProductService.productStockById(stock, asfssa.getProductId());
                }
                return JsonResult.OK().data("退款退货成功,退款金额为：" + refundResponse.getOrderAmount());
            } else if (SldzOrder.getPaymentMethod() == 2) {
                //积分支付
                //订单改为退款售后
                SldzOrderMapper.ChangeOrderAfterSales(SldzOrder.getOrderNumber());
                //库存加回
                List<OrderProductJsonVo> getOrderProductJsonVo = OrderUtil.getOrderProductJsonVo(SldzOrder.getProductJson());
                for (OrderProductJsonVo asfssa : getOrderProductJsonVo) {
                    //库存
                    int stock = Math.toIntExact(SldzProductService.getEntity(asfssa.getProductId()).getProductStock());
                    stock = (int) NumberUtil.add(stock, asfssa.getCartNum());
                    //库存 = 库存 + 购买数量
                    SldzProductService.productStockById(stock, asfssa.getProductId());
                }
                //记录 回积分/奖励金 收入记录
                //应该加回的积分
                double ResidualIntegral = 0.00;
                //积分余额
                double Integral = SldzUser.getIntegral();
                //实付金额
                double AmountPayable = SldzOrder.getAmountActuallyPaid();

                ResidualIntegral = NumberUtil.add(Integral, AmountPayable);
                //加积分
                if (SldzUserServic.ChangePoints(ResidualIntegral, SldzUser.getRandom())) {
                    //记录积分消费记录
                    sldzAgentIntegralLogService.createEntity(new SldzAgentIntegralLog()
                            .setAgentRandom(SldzUser.getRandom())
                            .setIntegralDate(DateUtil.now())
                            .setIntegralEvent("订单退款退货" + AmountPayable + "积分,订单号:" + SldzOrder.getOrderNumber())
                            .setIntegralMoney(AmountPayable)
                            .setIntegralType(1l));
                }
                return JsonResult.OK().data("退款退货成功，积分加回：" + AmountPayable);
            } else if (SldzOrder.getPaymentMethod() == 3) {
                //奖励金支付
                //订单改为退款售后
                SldzOrderMapper.ChangeOrderAfterSales(SldzOrder.getOrderNumber());
                //库存加回
                List<OrderProductJsonVo> getOrderProductJsonVo = OrderUtil.getOrderProductJsonVo(SldzOrder.getProductJson());
                for (OrderProductJsonVo asfssa : getOrderProductJsonVo) {
                    //库存
                    int stock = Math.toIntExact(SldzProductService.getEntity(asfssa.getProductId()).getProductStock());
                    stock = (int) NumberUtil.add(stock, asfssa.getCartNum());
                    //库存 = 库存 + 购买数量
                    SldzProductService.productStockById(stock, asfssa.getProductId());
                }
                double RemainingBonus = 0.00;
                //奖励金
                double Bonus = SldzUser.getBonus();
                //实付金额
                double AmountPayable = SldzOrder.getAmountActuallyPaid();

                RemainingBonus = NumberUtil.add(Bonus, AmountPayable);
                //加奖励金
                if (SldzUserServic.ChangeBonus(RemainingBonus, SldzUser.getRandom())) {
                    //记录积分消费记录
                    sldzAgentIntegralLogService.createEntity(new SldzAgentIntegralLog()
                            .setAgentRandom(SldzUser.getRandom())
                            .setIntegralDate(DateUtil.now())
                            .setIntegralEvent("订单退款退货" + AmountPayable + "奖励金,订单号:" + SldzOrder.getOrderNumber())
                            .setIntegralMoney(AmountPayable)
                            .setIntegralType(1l));
                }
                return JsonResult.OK().data("退款退货成功，积分加回：" + AmountPayable);

            }
        } else if (type == 2) {
            //代理商
            SldzAgent SldzAgen = (SldzAgent) map.get("SldzAgent");
            //退款流程 加回积分/奖励金/微信
            if (SldzOrder.getPaymentMethod() == 1) {
                //微信支付
                RefundRequest refundRequest = new RefundRequest();
                refundRequest.setOrderId(SldzOrder.getOrderNumber());
                refundRequest.setOrderAmount(SldzOrder.getAmountActuallyPaid());
                refundRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_MINI);
                log.info("【微信退款】request={}", JsonUtil.toJson(refundRequest));
                RefundResponse refundResponse = bestPayService.refund(refundRequest);
                log.info("【微信退款】response={}", JsonUtil.toJson(refundResponse));
                //订单改为退款售后
                SldzOrderMapper.ChangeOrderAfterSales(SldzOrder.getOrderNumber());
                //库存加回
                List<OrderProductJsonVo> getOrderProductJsonVo = OrderUtil.getOrderProductJsonVo(SldzOrder.getProductJson());
                for (OrderProductJsonVo asfssa : getOrderProductJsonVo) {
                    //库存
                    int stock = Math.toIntExact(SldzProductService.getEntity(asfssa.getProductId()).getProductStock());
                    stock = (int) NumberUtil.add(stock, asfssa.getCartNum());
                    //库存 = 库存 + 购买数量
                    SldzProductService.productStockById(stock, asfssa.getProductId());
                }
                return JsonResult.OK().data("退款退货成功,退款金额为：" + refundResponse.getOrderAmount());
            } else if (SldzOrder.getPaymentMethod() == 2) {
                //积分支付
                //订单改为退款售后
                SldzOrderMapper.ChangeOrderAfterSales(SldzOrder.getOrderNumber());
                //库存加回
                List<OrderProductJsonVo> getOrderProductJsonVo = OrderUtil.getOrderProductJsonVo(SldzOrder.getProductJson());
                for (OrderProductJsonVo asfssa : getOrderProductJsonVo) {
                    //库存
                    int stock = Math.toIntExact(SldzProductService.getEntity(asfssa.getProductId()).getProductStock());
                    stock = (int) NumberUtil.add(stock, asfssa.getCartNum());
                    //库存 = 库存 + 购买数量
                    SldzProductService.productStockById(stock, asfssa.getProductId());
                }
                //记录 回积分/奖励金 收入记录
                //应该加回的积分
                double ResidualIntegral = 0.00;
                //积分余额
                double Integral = SldzAgen.getAgentIntegral();
                //实付金额
                double AmountPayable = SldzOrder.getAmountActuallyPaid();

                ResidualIntegral = NumberUtil.add(Integral, AmountPayable);
                //加积分
                if (SldzUserServic.ChangePoints(ResidualIntegral, SldzAgen.getAgentRandom())) {
                    //记录积分消费记录
                    sldzAgentIntegralLogService.createEntity(new SldzAgentIntegralLog()
                            .setAgentRandom(SldzAgen.getAgentRandom())
                            .setIntegralDate(DateUtil.now())
                            .setIntegralEvent("订单退款退货" + AmountPayable + "积分,订单号:" + SldzOrder.getOrderNumber())
                            .setIntegralMoney(AmountPayable)
                            .setIntegralType(1l));
                }
                return JsonResult.OK().data("退款退货成功，积分加回：" + AmountPayable);
            } else if (SldzOrder.getPaymentMethod() == 3) {
                //奖励金支付
                //订单改为退款售后
                SldzOrderMapper.ChangeOrderAfterSales(SldzOrder.getOrderNumber());
                //库存加回
                List<OrderProductJsonVo> getOrderProductJsonVo = OrderUtil.getOrderProductJsonVo(SldzOrder.getProductJson());
                for (OrderProductJsonVo asfssa : getOrderProductJsonVo) {
                    //库存
                    int stock = Math.toIntExact(SldzProductService.getEntity(asfssa.getProductId()).getProductStock());
                    stock = (int) NumberUtil.add(stock, asfssa.getCartNum());
                    //库存 = 库存 + 购买数量
                    SldzProductService.productStockById(stock, asfssa.getProductId());
                }
                double RemainingBonus = 0.00;
                //奖励金
                double Bonus = SldzAgen.getAgentBonus();
                //实付金额
                double AmountPayable = SldzOrder.getAmountActuallyPaid();

                RemainingBonus = NumberUtil.add(Bonus, AmountPayable);
                //加奖励金
                if (SldzUserServic.ChangeBonus(RemainingBonus, SldzAgen.getAgentRandom())) {
                    //记录积分消费记录
                    sldzAgentIntegralLogService.createEntity(new SldzAgentIntegralLog()
                            .setAgentRandom(SldzAgen.getAgentRandom())
                            .setIntegralDate(DateUtil.now())
                            .setIntegralEvent("订单退款退货" + AmountPayable + "奖励金,订单号:" + SldzOrder.getOrderNumber())
                            .setIntegralMoney(AmountPayable)
                            .setIntegralType(1l));

                }
                return JsonResult.OK().data("退款退货成功，积分加回：" + AmountPayable);
            }

        }


        return null;
    }

    @Override
    public Integer ChangeLogisticsNumber() {
        return SldzOrderMapper.ChangeLogisticsNumber();
    }

    @Override
    public List<SldzOrder> GetOrderBeenDelivered() {
        return SldzOrderMapper.GetOrderBeenDelivered();
    }

//    @Override
//    public JsonResult AwardCompletedCrdersPreview(String orderNumber) {
//        //获取订单
//        SldzOrder SldzOrder  =  SldzOrderMapper.GetOrderAccordingByOrderNumber(orderNumber);
//        if(SldzOrder.getCommissionPayment() ==1){//否
//            //查询该客户是用户还是消费者
//            Map map =  getUser(SldzOrder.getBuyersRandom());
//            int type= (int) map.get("type");
//            if (type== 1 ){
//                //1代表消费者
//                SldzUser SldzUser =  (SldzUser) map.get("SldzUser");
//
//            }else if (type== 2 ){
//                //2代理商
//                SldzAgent SldzAgent= (SldzAgent) map.get("SldzAgent");
//            }
//        }
//
//
//        return null;
//    }


    public Map getUser(String random) {
        Map map = new HashMap();

        LambdaQueryWrapper<SldzUser> wrapperUser = new LambdaQueryWrapper<>();
        wrapperUser.eq(SldzUser::getRandom, random);
        //根据wrapper 查找对应数据
        SldzUser SldzUser = SldzUserServic.getSingleEntity(wrapperUser);
        if (ObjectUtil.isNull(SldzUser)) {
            LambdaQueryWrapper<SldzAgent> wrapperAgent = new LambdaQueryWrapper<>();
            wrapperAgent.eq(SldzAgent::getAgentRandom, random);
            //根据wrapper 查找对应数据
            SldzAgent SldzAgent = SldzAgentService.getSingleEntity(wrapperAgent);
            map.put("type", 2);//1代表消费者 2代理商
            map.put("SldzAgent", SldzAgent);//1代表消费者 2代理商
            return map;
        }
        map.put("type", 1);//1代表消费者 2代理商
        map.put("SldzUser", SldzUser);//1代表消费者 2代理商
        return map;
    }

    public void deleterCoupon(SldzOrder SldzOrder) {
        if (SldzOrder.getDiscount() > 0.00) {
            if (StrUtil.isNotBlank(SldzOrder.getCouponJson())) {
                JSONObject rowData = JSONObject.parseObject(SldzOrder.getCouponJson());
                JSONObject rowData2 = JSONObject.parseObject(rowData.getString("couponJson"));
                //得到优惠券id
                String couponId = rowData2.getString("primaryKeyVal");
                //得到唯一编码
                String Random = SldzOrder.getBuyersRandom();
                String _coupon = "_coupon";
                String s = redisUtils.get(Random + _coupon + couponId);
                if (StrUtil.isNotBlank(s)) {
                    redisUtils.delete(Random + _coupon + couponId);
                }
            }
        }
    }

}

