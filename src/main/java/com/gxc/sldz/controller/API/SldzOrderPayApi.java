package com.gxc.sldz.controller.API;


import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.diboot.core.vo.JsonResult;
import com.gxc.sldz.Utils.OrderUtil;
import com.gxc.sldz.Utils.RedisUtils;
import com.gxc.sldz.controller.BaseCustomCrudRestController;
import com.gxc.sldz.entity.SldzOrder;
import com.gxc.sldz.entity.SldzProduct;
import com.gxc.sldz.mapper.SldzOrderMapper;
import com.gxc.sldz.service.SldzOrderService;
import com.gxc.sldz.service.SldzProductService;
import com.gxc.sldz.vo.OrderProductJsonVo;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.service.BestPayService;
import com.lly835.bestpay.utils.JsonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"前台订单支付接口"})
@RestController
@RequestMapping("api/sldzOrder")
@Slf4j
public class SldzOrderPayApi extends BaseCustomCrudRestController<SldzOrder> {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private SldzOrderService sldzOrderService;

    @Autowired
    BestPayService bestPayService;

    @Autowired
    SldzProductService SldzProductService;


    @ApiOperation(value = "订单支付")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderNumber", value = "订单号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "paymentMethod", value = "支付方式(付款方式 1微信钱包,2积分,3,奖励金)", required = true, dataType = "int"),
    })
    @PutMapping("/orderPay")
    public JsonResult orderPay(String orderNumber ,int paymentMethod) throws Exception {
            return  sldzOrderService.orderPay(GetOrderObjectByOrderNumber(orderNumber),paymentMethod);
    }


    @ApiOperation(value = "异步回调")
    @PostMapping(value = "/notify")
    public void notify(@RequestBody String notifyData ) throws Exception {
//        log.info("asynchronous【异步回调】request={}", notifyData);
        PayResponse response = bestPayService.asyncNotify(notifyData);
//        log.info("asynchronous【异步回调】response={}", JsonUtil.toJson(response));
        //获取订单号
        String orderId = response.getOrderId();
        log.info("【异步回调获取订单号】orderId={}", orderId);
        System.out.println("获取订单号"+orderId);
        //实际支付金额
        double AmountPayable = response.getOrderAmount();
        System.out.println("实际支付金额"+AmountPayable);
        //微信支付流水号
        String TradeNo = response.getOutTradeNo();
        System.out.println("微信支付流水号"+TradeNo);
        //取出订单
        SldzOrder SldzOrder = GetOrderObjectByOrderNumberss(orderId);
        System.out.println("取出订单"+SldzOrder);


        //扣除库存
        List<OrderProductJsonVo> getOrderProductJsonVo = OrderUtil.getOrderProductJsonVo(SldzOrder.getProductJson());
        for (OrderProductJsonVo asfssa :getOrderProductJsonVo) {
            System.out.println("取出产品："+asfssa);
            //库存 = 库存 - 购买数量  根据ID减产品库存
            SldzProductService.productStockByIdloa(asfssa.getCartNum(),asfssa.getProductId());
        }
        System.out.println("扣除库存流程走完");


        UpdateWrapper<SldzOrder> SldzOrderupdateWrapper = new UpdateWrapper<>();
        SldzOrderupdateWrapper.eq("order_number",orderId);
        SldzOrderupdateWrapper.set("amount_actually_paid",AmountPayable);
        SldzOrderupdateWrapper.set("state",2);
        SldzOrderupdateWrapper.set("wx_pay_serial_num",TradeNo);
        SldzOrderupdateWrapper.set("payment_time",DateUtil.now());
        sldzOrderService.updateEntity(SldzOrderupdateWrapper);
//        改订单状态  待收货  记录时间
//        sldzOrderService.ChangeOrderSigneds (
//                1,
//                AmountPayable,
//                DateUtil.now(),
//                TradeNo,
//                orderId);
        System.out.println("回调流程走完");

    }


    @ApiOperation(value = "检查订单支付状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderNumber", value = "订单号", required = true, dataType = "String"),
    })
    @GetMapping("/CheckOrderPaymentStatus")
    public JsonResult CheckOrderPaymentStatus(String orderNumber) throws Exception {
        SldzOrder  SldzOrder  =  GetOrderObjectByOrderNumbers(orderNumber);
        if (ObjectUtil.isNull(SldzOrder)){
            int lo= 10;
            for (int i=0;i< lo;i++){
                Thread.sleep(500);
                SldzOrder  =  GetOrderObjectByOrderNumbers(orderNumber);
                if (ObjectUtil.isNotNull(SldzOrder) ){
                    return JsonResult.OK().data("订单支付成功");
                }
            }
        }else if (ObjectUtil.isNotNull(SldzOrder)){
            return JsonResult.OK().data("订单支付成功");
        }
        return JsonResult.FAIL_OPERATION("订单支付失败");
    }



    //根据订单号获取未付款订单对象
    public SldzOrder GetOrderObjectByOrderNumberss(String orderNumber) throws Exception {
        LambdaQueryWrapper<SldzOrder> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SldzOrder::getOrderNumber, orderNumber);
        SldzOrder s = sldzOrderService.getSingleEntity(wrapper);
        return  s;
    }


    //根据订单号获取未付款订单对象
    public SldzOrder GetOrderObjectByOrderNumber(String orderNumber) throws Exception {
        LambdaQueryWrapper<SldzOrder> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SldzOrder::getOrderNumber, orderNumber);
        wrapper.eq(SldzOrder::getState, 1);
        SldzOrder s = sldzOrderService.getSingleEntity(wrapper);
        return  s;
    }


    //根据订单号获取已付款订单对象
    public SldzOrder GetOrderObjectByOrderNumbers(String orderNumber) throws Exception {
        LambdaQueryWrapper<SldzOrder> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SldzOrder::getOrderNumber, orderNumber);
        wrapper.eq(SldzOrder::getState, 2);
        SldzOrder s = sldzOrderService.getSingleEntity(wrapper);
        return  s;
    }


}
