package com.gxc.sldz.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.UpdateChainWrapper;
import com.gxc.sldz.dto.SldzAgentDTO;
import com.gxc.sldz.entity.SldzAdmin;
import com.gxc.sldz.entity.SldzAgent;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import com.lly835.bestpay.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
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
    public JsonResult keywords(SldzOrderDTO queryDto, Pagination pagination) throws Exception {
        String orderNumber = "order_number";
        String buyersRandom = "buyers_random";
        String buyersName = "buyers_name";
        String logisticsNumber = "logistics_number";

        QueryWrapper<SldzOrder> wrapper = new QueryWrapper();
        wrapper.like(StrUtil.isNotBlank(queryDto.getOrderNumber()), orderNumber, queryDto.getOrderNumber());
        wrapper.like(StrUtil.isNotBlank(queryDto.getBuyersRandom()), buyersRandom, queryDto.getBuyersRandom());
        wrapper.like(StrUtil.isNotBlank(queryDto.getBuyersRandom()), buyersName, queryDto.getBuyersRandom());
        wrapper.like(StrUtil.isNotBlank(queryDto.getLogisticsNumber()), logisticsNumber, queryDto.getLogisticsNumber());
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
        boolean s = sldzOrderService.updateEntity(updateWrapper);
        if (s){
            return JsonResult.OK().data("物流单号修改成功");
        }
        return JsonResult.FAIL_OPERATION("物流单号修改失败");
    }


    /**
     * 退款
     */
//    @GetMapping(value = "/refund")
//    public JsonResult refund(@RequestParam("ordernum") String ordernum, @RequestParam("price") double price) {
//        RefundRequest refundRequest = new RefundRequest();
//        refundRequest.setOrderId(ordernum);
//        refundRequest.setOrderAmount(price);
//        refundRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_MINI);
//        log.info("【微信退款】request={}", JsonUtil.toJson(refundRequest));
//        RefundResponse refundResponse = bestPayService.refund(refundRequest);
//        log.info("【微信退款】response={}", JsonUtil.toJson(refundResponse));
//        return JsonResult.OK().data(PayServer.refund(refundResponse));
//    }


}
