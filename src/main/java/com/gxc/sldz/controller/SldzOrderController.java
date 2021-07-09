package com.gxc.sldz.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.UpdateChainWrapper;
import com.gxc.sldz.Utils.Logistics;
import com.gxc.sldz.Utils.RedisUtils;
import com.gxc.sldz.dto.SldzAgentDTO;
import com.gxc.sldz.entity.SldzAdmin;
import com.gxc.sldz.entity.SldzAgent;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import com.lly835.bestpay.utils.JsonUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.diboot.core.vo.*;
import com.gxc.sldz.entity.SldzOrder;
import com.gxc.sldz.dto.SldzOrderDTO;
import com.gxc.sldz.vo.SldzOrderListVO;
import com.gxc.sldz.vo.SldzOrderDetailVO;
import com.gxc.sldz.service.SldzOrderService;
import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 订单 相关Controller
 *
 * @author Achin
 * @version 1.0
 * @date 2021-06-22
 * Copyright © MyCompany
 */
@Api(tags = {"后台订单管理"})
@RestController
@RequestMapping("admin/sldzOrder")
@Slf4j
public class SldzOrderController extends BaseCustomCrudRestController<SldzOrder> {

    @Autowired
    private SldzOrderService sldzOrderService;

    @Autowired
    private BestPayServiceImpl bestPayService;

    @Autowired
    private RedisUtils redisUtils;



    /**
     * 查询ViewObject的分页数据
     * <p>
     * url请求参数示例: /list?field=abc&pageIndex=1&orderBy=abc:DESC
     * </p>
     *
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "获取列表分页数据")
    @GetMapping("/list")
    public JsonResult getViewObjectListMapping(SldzOrderDTO queryDto, Pagination pagination) throws Exception {
        return super.getViewObjectList(queryDto, pagination, SldzOrderListVO.class);
    }


    @ApiOperation(value = "订单模糊搜索")
    @GetMapping("/keywords")
    public JsonResult keywords(int state,String  keyword, Pagination pagination) throws Exception {
        String orderNumber = "order_number";
        String buyersRandom = "buyers_random";
        String buyersName = "buyers_name";
        String logisticsNumber = "logistics_number";

        QueryWrapper<SldzOrder> wrapper = new QueryWrapper();
        wrapper.like(orderNumber, keyword).or()
                .like(buyersRandom,keyword).or()
                .like(buyersName, keyword).or()
                .like(logisticsNumber, keyword);
        return super.getEntityListWithPaging(wrapper, pagination);
        // return super.getViewObjectList(queryDto, pagination, SldzAgentListVO.class);
    }

    /**
     * 根据资源id查询ViewObject
     *
     * @param id ID
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "根据ID获取详情数据")
    @GetMapping("/{id}")
    public JsonResult getViewObjectMapping(@PathVariable("id") Long id) throws Exception {
        return super.getViewObject(id, SldzOrderDetailVO.class);
    }

    /**
     * 根据资源id查询ViewObject
     *
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "获取已付款未发货的数量")
    @GetMapping("/GetPaidNotDeliveredQuantity")
    public JsonResult GetPaidNotDeliveredQuantity() throws Exception {
//        LambdaQueryWrapper<SldzOrder> SldzOrderwrapper = new LambdaQueryWrapper<>();
//        SldzOrderwrapper.eq(SldzOrder::getState, 2);
//        SldzOrderwrapper.eq(SldzOrder::getLogisticsNumber, null);
        Integer s =  sldzOrderService.ChangeLogisticsNumber();
        return JsonResult.OK().data(s);
    }


    @ApiOperation(value = "根据ID更新数据")
    @PutMapping("/{id}")
    public JsonResult updateEntityMapping(@PathVariable("id") Long id, @Valid @RequestBody SldzOrder entity) throws Exception {
        return super.updateEntity(id, entity);
    }


    @ApiOperation(value = "更改物流单号")
    @PutMapping("/ChangeLogisticsNumber")
    public JsonResult ChangeLogisticsNumber(String orderNumber, String LogisticsNumber) throws Exception {
        UpdateWrapper<SldzOrder> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("order_number", orderNumber);
        updateWrapper.set("logistics_number", LogisticsNumber);
        updateWrapper.set("delivery_time", DateUtil.now());
        boolean s = sldzOrderService.updateEntity(updateWrapper);
        if (s){
            return JsonResult.OK().data("物流单号修改成功");
        }
        return JsonResult.FAIL_OPERATION("物流单号修改失败");
    }

    @ApiOperation(value = "无运单号-退款")
    @PostMapping("/UndeliveredRefund")
    public JsonResult UndeliveredRefund(String orderNumber) throws Exception{
        return sldzOrderService.UndeliveredRefund(GetOrderObjectByOrderNumbers(orderNumber));
    }


    @ApiOperation(value = "获取物流详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "LogisticsNumber", value = "快递单号", required = true, dataType = "String"),
    })
    @RequestMapping(value = "GetLogisticsDetails", method = RequestMethod.GET)
    public JsonResult GetLogisticsDetails(String LogisticsNumber) throws Exception {
        JSONObject jsonObject = new JSONObject();
        if (StrUtil.isNotEmpty(LogisticsNumber)){
            // 根据物流单号查询 redis有没有数据
            String ss =  redisUtils.get(LogisticsNumber);
            // 如果物流单号不在redis中就查询第三方api
            if (StrUtil.isBlank(ss)) {
                ss = Logistics.main(LogisticsNumber);
                //获取到数据后 写入redis 设置30分钟后过期
                redisUtils.set(LogisticsNumber,ss,1800);
                return JsonResult.OK().data(ss);
            }else {
                return JsonResult.OK().data(ss);
            }
        }else {
            return JsonResult.FAIL_OPERATION("运单号不能为空");
        }
    }




    @ApiOperation(value = "优惠券json解析")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "couponJson", value = "优惠券json", required = true, dataType = "String"),
    })
    @RequestMapping(value = "couponJsonUtil", method = RequestMethod.POST)
    public JsonResult couponJsonUtil(@RequestBody String couponJson) throws Exception {
        JSONObject rowData = JSONObject.parseObject(couponJson);
        JSONObject rowData2 = JSONObject.parseObject(rowData.getString("couponJson"));
//        rowData2.getString("couponsTotal");
        return JsonResult.OK().data(rowData2);
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
