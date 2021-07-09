package com.gxc.sldz.controller.API;


import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.diboot.core.util.S;
import com.diboot.core.vo.JsonResult;
import com.diboot.core.vo.Pagination;
import com.gxc.sldz.controller.BaseCustomCrudRestController;
import com.gxc.sldz.dto.SldzProductReviewsDTO;
import com.gxc.sldz.entity.SldzOrder;
import com.gxc.sldz.entity.SldzProductReviews;
import com.gxc.sldz.service.SldzOrderService;
import com.gxc.sldz.service.SldzProductReviewsService;
import com.gxc.sldz.vo.SldzProductReviewsListVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Api(tags = {"产品评论前台接口"})
@RestController
@RequestMapping("api/sldzProductReviews")
@Slf4j
public class SldzProductReviewsApi extends BaseCustomCrudRestController<SldzProductReviews> {
    @Autowired
    private SldzProductReviewsService sldzProductReviewsService;

    @Autowired
    private SldzOrderService sldzOrderService;


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
    public JsonResult getViewObjectListMapping(SldzProductReviewsDTO queryDto, Pagination pagination) throws Exception{
        return super.getViewObjectList(queryDto, pagination, SldzProductReviewsListVO.class);
    }



    /***
     * 创建资源对象
     * @param entity
     * @return JsonResult
     * @throws Exception
     */
    @ApiOperation(value = "新建数据")
    @PostMapping("/")
    public JsonResult createEntityMapping(@Valid @RequestBody SldzProductReviews entity,  String orderNumber) throws Exception {
        entity.setReviewerDate(DateUtil.date());
        SldzOrder s = GetOrderObjectByOrderNumbers(orderNumber);
        if (StrUtil.isBlank(s.getProductsIdReviewed())){
            s.setProductsIdReviewed(String.valueOf(entity.getProductId()));
            //插入数据库
            UpdateWrapper<SldzOrder> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("order_number", orderNumber);
            updateWrapper.set("products_id_reviewed", entity.getProductId());
            sldzOrderService.updateEntity(updateWrapper);
        }else {
            String sa =s.getProductsIdReviewed()+","+entity.getProductId();
            UpdateWrapper<SldzOrder> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("order_number", orderNumber);
            updateWrapper.set("products_id_reviewed", sa);
            sldzOrderService.updateEntity(updateWrapper);
        }
        return super.createEntity(entity);
    }


    //根据订单号获取已完成订单对象
    public SldzOrder GetOrderObjectByOrderNumbers(String orderNumber) throws Exception {
        LambdaQueryWrapper<SldzOrder> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SldzOrder::getOrderNumber, orderNumber);
        wrapper.eq(SldzOrder::getState, 3);
        SldzOrder s = sldzOrderService.getSingleEntity(wrapper);

        return s;
    }
}
