package com.gxc.sldz.controller.API;


import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.diboot.core.vo.JsonResult;
import com.gxc.sldz.Utils.RedisUtils;
import com.gxc.sldz.controller.BaseCustomCrudRestController;
import com.gxc.sldz.entity.SldzOrder;
import com.gxc.sldz.service.SldzOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"前台订单支付接口"})
@RestController
@RequestMapping("api/sldzOrder")
@Slf4j
public class SldzOrderPayApi extends BaseCustomCrudRestController<SldzOrder> {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private SldzOrderService sldzOrderService;



    @ApiOperation(value = "订单支付")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderNumber", value = "订单号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "paymentMethod", value = "支付方式", required = true, dataType = "int"),
    })
    @PutMapping("/orderPay")
    public JsonResult orderPay(String orderNumber ,int paymentMethod) throws Exception {
            return  sldzOrderService.orderPay(GetOrderObjectByOrderNumber(orderNumber),paymentMethod);
    }







    //根据订单号获取订单对象
    public SldzOrder GetOrderObjectByOrderNumber(String orderNumber) throws Exception {
        LambdaQueryWrapper<SldzOrder> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SldzOrder::getOrderNumber, orderNumber);
        wrapper.eq(SldzOrder::getState, 1);
        return  sldzOrderService.getSingleEntity(wrapper);
    }

}
