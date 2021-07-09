package com.gxc.sldz.controller.API;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.UpdateChainWrapper;
import com.diboot.core.util.S;
import com.diboot.core.vo.JsonResult;
import com.diboot.core.vo.Pagination;
import com.gxc.sldz.Utils.Logistics;
import com.gxc.sldz.Utils.OrderNumberTimeUtil;
import com.gxc.sldz.Utils.OrderUtil;
import com.gxc.sldz.Utils.RedisUtils;
import com.gxc.sldz.controller.BaseCustomCrudRestController;
import com.gxc.sldz.dto.SldzOrderDTO;
import com.gxc.sldz.entity.SldzAdmin;
import com.gxc.sldz.entity.SldzOrder;
import com.gxc.sldz.service.SldzOrderService;
import com.gxc.sldz.vo.OrderProductJsonVo;
import com.gxc.sldz.vo.SldzOrderDetailVO;
import com.gxc.sldz.vo.SldzOrderListVO;
import io.netty.handler.codec.json.JsonObjectDecoder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;


@Api(tags = {"前台订单接口"})
@RestController
@RequestMapping("api/sldzOrder")
@Slf4j
public class SldzOrderApi extends BaseCustomCrudRestController<SldzOrder> {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private SldzOrderService sldzOrderService;


    @ApiOperation(value = "生成订单")
    @PostMapping("/")
    public JsonResult createEntityMapping(@Valid @RequestBody SldzOrder entity) throws Exception {
        entity.setOrderNumber(OrderNumberTimeUtil.getOrderIdByTime());
        entity.getProductJson();
        return super.createEntity(entity);
    }


    @ApiOperation(value = "根据订单产品获取符合条件的优惠券列表")
    @PostMapping("/ObtainCouponsAccordingOrderProducts")
    public JsonResult ObtainCouponsAccordingOrderProducts(@RequestBody String orderNumber) throws Exception {
        SldzOrder SldzOrder = GetOrderObjectByOrderNumber(orderNumber);
        if (ObjectUtil.isNull(SldzOrder)) {
            return JsonResult.FAIL_OPERATION("获取优惠券列表失败");
        }
//        //唯一编码
//        SldzOrder.getBuyersRandom();
//        // 获取order producJson
//        List<Long> orderProductIds  =  OrderUtil.getOrderJsonProductId(SldzOrder.getProductJson());
//        List<Long> CouponproductIds = new ArrayList<>();
//        //从缓存拿出该人的优惠券列表
//        String key = SldzOrder.getBuyersRandom()+"_coupon"+"*";
//        Set<String> CouponList = redisUtils.getByKeys(key);
////        可用优惠券列表
//        List<String> availableCoupons = new ArrayList<>();
////        拿出各组优惠券
//        for (String Coupon :CouponList){
//            int oap = 0;
////            CouponproductIds  .addAll(OrderUtil.GetProductIdByCoupon(Coupon));
//                //循环优惠券productId
//                for(Long CouponPrductid :OrderUtil.GetProductIdByCoupon(Coupon)){
//                    if (!orderProductIds.contains(CouponPrductid)){
//                        //为false的时候跳出
//                        break;
//                    }else {
//                        oap =1;
//                    }
//                }
//
//                if (oap>1){
//                    availableCoupons.add(Coupon);
//                }
//            }
        return sldzOrderService.ObtainCouponsAccordingOrderProducts(SldzOrder);
    }

    @ApiOperation(value = "根据订单编号更改收货地址")
    @PutMapping("/ChangeAddressByoOrderNumber")
    public JsonResult ChangeAddressByoOrderNumber(String addresJson, String orderNumber, String Random) throws Exception {
        if (sldzOrderService.ChangeShippingAddress(addresJson, orderNumber, Random)) {
            return JsonResult.OK().data("改收货地址修改成功");
        }
        return JsonResult.FAIL_OPERATION("改收货地址修改失败");
    }

    @ApiOperation(value = "根据订单编号添加订单备注")
    @PutMapping("/AddOrderNotesByOrderNum")
    public JsonResult AddOrderNotesByOrderNum(String buyersRemarks, String orderNumber) throws Exception {
        if (sldzOrderService.AddOrderNotesByOrderNum(buyersRemarks, orderNumber)) {
            return JsonResult.OK().data("备注添加成功");
        }

        return JsonResult.FAIL_OPERATION("备注添加失败");
    }

    @ApiOperation(value = "选择优惠券调整应付金额")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "discount", value = "优惠金额", required = true, dataType = "String"),
            @ApiImplicitParam(name = "orderNumber", value = "订单号", required = true, dataType = "String"),
    })
    @PutMapping("/CouponAdjustAmountPayable")
    public JsonResult CouponAdjustAmountPayable(double discount, String orderNumber) throws Exception {
        SldzOrder SldzOrder = GetOrderObjectByOrderNumber(orderNumber);
        if (ObjectUtil.isNull(SldzOrder)) {
            return JsonResult.FAIL_OPERATION("应付金额计算失败");
        }

        //获取订单总金额
        List<OrderProductJsonVo> getOrderProductJsonVo = OrderUtil.getOrderProductJsonVo(SldzOrder.getProductJson());


        // 订单应付钱金额
        double AmountOrderPayable = 0.00;
        for (OrderProductJsonVo salk : getOrderProductJsonVo) {
            //购买数量 * 单品金额
            AmountOrderPayable += NumberUtil.mul(salk.getCartNum(), salk.getProductPrice());
        }


        //优惠后金额
        double AmountAfterDiscount = 0.00;
//        应付金额 = 应付金额 - 优惠金额
        AmountAfterDiscount = NumberUtil.sub(AmountOrderPayable, discount);


        //写入库 优惠金额 应付金额
        UpdateWrapper<SldzOrder> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq(SldzOrder.getOrderNumber(), orderNumber);
        updateWrapper.set("amount_payable", AmountOrderPayable);
//        updateWrapper.set("discount",discount);
//        updateWrapper.set("coupon_json",couponJson);
        Map map = new HashMap();
        map.put("AmountAfterDiscount", AmountAfterDiscount);
        map.put("msg", sldzOrderService.updateEntity(updateWrapper) ? "调整优惠后金额成功" : "调整优惠后金额失败");
        return JsonResult.OK().data(map);
    }

    @ApiOperation(value = "确定最终应付金额")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "couponId", value = "优惠券id", required = true, dataType = "int"),
            @ApiImplicitParam(name = "couponJson", value = "优惠券json", required = true, dataType = "String"),
            @ApiImplicitParam(name = "discount", value = "优惠金额", required = true, dataType = "double"),
            @ApiImplicitParam(name = "AmountAfterDiscount", value = "优惠后金额", required = true, dataType = "double"),
            @ApiImplicitParam(name = "orderNumber", value = "订单号", required = true, dataType = "String"),
    })
    @PutMapping("/DetermineFinalAmount")
    public JsonResult DetermineFinalAmount(Integer couponId,
                                           @RequestBody String couponJson,
                                           double discount,
                                           double AmountAfterDiscount,
                                           String orderNumber) throws Exception {
        SldzOrder SldzOrder = GetOrderObjectByOrderNumber(orderNumber);
        if (ObjectUtil.isNull(SldzOrder)) {
            return JsonResult.FAIL_OPERATION("失败");
        }

        //删除缓存优惠券
        String key = SldzOrder.getBuyersRandom() + "_coupon" + couponId;
        redisUtils.delete(key);

        //把优惠券json 优惠金额 优惠后金额 写入库
        UpdateWrapper<SldzOrder> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("order_number", orderNumber);
        updateWrapper.set("amount_payable", AmountAfterDiscount);
        updateWrapper.set("discount", discount);
        updateWrapper.set("coupon_json", couponJson);
        return JsonResult.OK().data(sldzOrderService.updateEntity(updateWrapper) ? "调整优惠后金额成功" : "调整优惠后金额失败");
    }


    @ApiOperation(value = "根据订单号获取订单详情")
    @GetMapping("/orderDetailsByOrderNumber")
    public JsonResult orderDetailsByOrderNumber(String orderNumber) throws Exception {
        SldzOrder SldzOrder = GetOrderObjectByOrderNumber(orderNumber);
        return JsonResult.OK().data(SldzOrder);
    }

    /***
     * 查询ViewObject的分页数据
     * <p>
     * url请求参数示例: /list?field=abc&pageIndex=1&orderBy=abc:DESC
     * </p>
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "获取列表分页数据")
    @GetMapping("/list")
    public JsonResult getViewObjectListMapping(SldzOrderDTO queryDto, Pagination pagination) throws Exception {
        return super.getViewObjectList(queryDto, pagination, SldzOrderListVO.class);
    }


    /***
     * 根据资源id查询ViewObject
     * @param id ID
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "根据ID获取详情数据")
    @GetMapping("/{id}")
    public JsonResult getViewObjectMapping(@PathVariable("id") Long id) throws Exception {
        return super.getViewObject(id, SldzOrderDetailVO.class);
    }

    /***
     * 根据资源id查询ViewObject
     * @param id ID
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "未付款-取消订单")
    @DeleteMapping("/{id}")
    public JsonResult deleteEntity(@PathVariable("id") Long id) throws Exception {
        return super.deleteEntity(id);
    }


    @ApiOperation(value = "未发货-退款")
    @PostMapping("/UndeliveredRefund")
    public JsonResult UndeliveredRefund(String orderNumber) throws Exception {
        return sldzOrderService.UndeliveredRefund(GetOrderObjectByOrderNumbers(orderNumber));
    }


    @ApiOperation(value = "确认收货")
    @PostMapping("/ConfirmReceipt")
    public JsonResult ConfirmReceipt(String orderNumber) throws Exception {
        UpdateWrapper<SldzOrder> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("order_number", orderNumber);
        updateWrapper.set("state", 3);
        updateWrapper.set("confirm_receiving_time", DateUtil.now());
        return JsonResult.OK().data(sldzOrderService.updateEntity(updateWrapper));
    }


    @ApiOperation(value = "获取物流详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "LogisticsNumber", value = "快递单号", required = true, dataType = "String"),
    })
    @RequestMapping(value = "GetLogisticsDetails", method = RequestMethod.GET)
    public JsonResult GetLogisticsDetails(String LogisticsNumber) throws Exception {
        JSONObject jsonObject = new JSONObject();
        if (StrUtil.isNotEmpty(LogisticsNumber)) {
            // 根据物流单号查询 redis有没有数据
            String ss = redisUtils.get(LogisticsNumber);
            // 如果物流单号不在redis中就查询第三方api
            if (StrUtil.isBlank(ss)) {
                ss = Logistics.main(LogisticsNumber);
                //获取到数据后 写入redis 设置30分钟后过期
                redisUtils.set(LogisticsNumber, ss, 1800);
                return JsonResult.OK().data(ss);
            } else {
                return JsonResult.OK().data(ss);
            }
        } else {
            return JsonResult.FAIL_OPERATION("运单号不能为空");
        }
    }


    //根据订单号获取订单对象
    public SldzOrder GetOrderObjectByOrderNumber(String orderNumber) throws Exception {
        LambdaQueryWrapper<SldzOrder> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SldzOrder::getOrderNumber, orderNumber);
        wrapper.eq(SldzOrder::getState, 1);
        return sldzOrderService.getSingleEntity(wrapper);
    }

    //根据订单号获取已付款订单对象
    public SldzOrder GetOrderObjectByOrderNumbers(String orderNumber) throws Exception {
        LambdaQueryWrapper<SldzOrder> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SldzOrder::getOrderNumber, orderNumber);
        wrapper.eq(SldzOrder::getState, 2);
        SldzOrder s = sldzOrderService.getSingleEntity(wrapper);
        return s;
    }


}
