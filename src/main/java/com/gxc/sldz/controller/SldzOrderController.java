package com.gxc.sldz.controller;

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
 * @author Achin
 * @version 1.0
 * @date 2021-06-22
 * Copyright © MyCompany
 */
@Api(tags = { "后台订单管理" })
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
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "获取列表分页数据")
    @GetMapping("/list")
    public JsonResult getViewObjectListMapping(SldzOrderDTO queryDto, Pagination pagination) throws Exception {
        return super.getViewObjectList(queryDto, pagination, SldzOrderListVO.class);
    }

    /**
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

    // 
    // /***
    // * 创建资源对象
    // * @param entity
    // * @return JsonResult
    // * @throws Exception
    // */
    // @ApiOperation(value = "新建数据")
    // @PostMapping("/")
    // public JsonResult createEntityMapping(@Valid @RequestBody SldzOrder entity) throws Exception {
    // return super.createEntity(entity);
    // }
    /**
     * 根据ID更新资源对象
     * @param entity
     * @return JsonResult
     * @throws Exception
     */
    @ApiOperation(value = "根据ID更新数据")
    @PutMapping("/{id}")
    public JsonResult updateEntityMapping(@PathVariable("id") Long id, @Valid @RequestBody SldzOrder entity) throws Exception {
        return super.updateEntity(id, entity);
    }
    // /***
    // * 根据id删除资源对象
    // * @param id
    // * @return
    // * @throws Exception
    // */
    // @ApiOperation(value = "根据ID删除数据")
    // @DeleteMapping("/{id}")
    // public JsonResult deleteEntityMapping(@PathVariable("id")Long id) throws Exception {
    // return super.deleteEntity(id);
    // }


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
