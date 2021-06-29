package com.gxc.sldz.controller.API;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.UpdateChainWrapper;
import com.diboot.core.util.S;
import com.diboot.core.vo.JsonResult;
import com.diboot.core.vo.Pagination;
import com.gxc.sldz.Utils.OrderNumberTimeUtil;
import com.gxc.sldz.Utils.OrderUtil;
import com.gxc.sldz.Utils.RedisUtils;
import com.gxc.sldz.controller.BaseCustomCrudRestController;
import com.gxc.sldz.dto.SldzOrderDTO;
import com.gxc.sldz.entity.SldzAdmin;
import com.gxc.sldz.entity.SldzOrder;
import com.gxc.sldz.service.SldzOrderService;
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
        return super.createEntity(entity);
    }


    @ApiOperation(value = "根据订单产品获取符合条件的优惠券列表")
    @PostMapping("/ObtainCouponsAccordingOrderProducts")
    public JsonResult ObtainCouponsAccordingOrderProducts(@RequestBody String orderNumber) throws Exception {
        SldzOrder SldzOrder =   GetOrderObjectByOrderNumber(orderNumber);
        if (ObjectUtil.isNull(SldzOrder)){
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

//    @ApiOperation(value = "选择优惠券，更改应付金额")
//    @PostMapping("/")
//    public JsonResult createEntityMapping(@Valid @RequestBody SldzOrder entity) throws Exception {
//        entity.setOrderNumber(OrderNumberTimeUtil.getOrderIdByTime());
//
//        return super.createEntity(entity);
//    }

    @ApiOperation(value = "根据订单编号更改收货地址")
    @PutMapping("/ChangeAddressByoOrderNumber")
    public JsonResult ChangeAddressByoOrderNumber(String addresJson ,String orderNumber,String Random) throws Exception {
        if (sldzOrderService. ChangeShippingAddress(addresJson,orderNumber,Random)){
            return  JsonResult.OK().data("改收货地址修改成功");
        }

        return JsonResult.FAIL_OPERATION("改收货地址修改失败");
    }


    @ApiOperation(value = "根据订单编号添加订单备注")
    @PutMapping("/AddOrderNotesByOrderNum")
    public JsonResult AddOrderNotesByOrderNum(String buyersRemarks,String orderNumber) throws Exception {
        if (sldzOrderService. AddOrderNotesByOrderNum(buyersRemarks,orderNumber)){
            return  JsonResult.OK().data("备注添加成功");
        }

        return JsonResult.FAIL_OPERATION("备注添加失败");
    }

    @ApiOperation(value = "选择优惠券调整应付金额")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "discount", value = "优惠金额", required = true, dataType = "String"),
            @ApiImplicitParam(name = "orderNumber", value = "订单号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "couponJson", value = "优惠券json", required = true, dataType = "String"),
    })
    @PutMapping("/CouponAdjustAmountPayable")
    public JsonResult CouponAdjustAmountPayable(double discount,String orderNumber,@RequestBody String couponJson) throws Exception {
        SldzOrder SldzOrder =   GetOrderObjectByOrderNumber(orderNumber);
        if (ObjectUtil.isNull(SldzOrder)){
            return JsonResult.FAIL_OPERATION("应付金额计算失败");
        }
        double AmountPayable =  SldzOrder.getAmountPayable();
//        应付金额 = 应付金额 - 优惠金额
         AmountPayable = NumberUtil.sub(AmountPayable, discount);


         //写入库 优惠金额 应付金额
        UpdateWrapper<SldzOrder> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq(SldzOrder.getOrderNumber(),orderNumber);
        updateWrapper.set("amount_payable",AmountPayable);
        updateWrapper.set("discount",discount);
        updateWrapper.set("coupon_json",couponJson);
        Map map = new HashMap();
        map.put("AmountPayable",AmountPayable);
        map.put("msg",sldzOrderService.updateEntity(updateWrapper)?"调整应付金额成功":"调整应付金额失败");
        return JsonResult.OK().data(map);
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
    public JsonResult getViewObjectListMapping(SldzOrderDTO queryDto, Pagination pagination) throws Exception{
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
    public JsonResult getViewObjectMapping(@PathVariable("id")Long id) throws Exception{
        return super.getViewObject(id, SldzOrderDetailVO.class);
    }


    //根据订单号获取订单对象
    protected SldzOrder GetOrderObjectByOrderNumber(String orderNumber) throws Exception {
        LambdaQueryWrapper<SldzOrder> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SldzOrder::getOrderNumber, orderNumber);
        wrapper.eq(SldzOrder::getState, 1);
        return  sldzOrderService.getSingleEntity(wrapper);
    }



}
