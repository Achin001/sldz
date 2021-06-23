package com.gxc.sldz.controller.API;

import com.diboot.core.vo.JsonResult;
import com.diboot.core.vo.Pagination;
import com.gxc.sldz.Utils.OrderNumberTimeUtil;
import com.gxc.sldz.controller.BaseCustomCrudRestController;
import com.gxc.sldz.dto.SldzOrderDTO;
import com.gxc.sldz.entity.SldzOrder;
import com.gxc.sldz.service.SldzOrderService;
import com.gxc.sldz.vo.SldzOrderDetailVO;
import com.gxc.sldz.vo.SldzOrderListVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Api(tags = {"前台订单接口"})
@RestController
@RequestMapping("api/sldzOrder")
@Slf4j
public class SldzOrderApi extends BaseCustomCrudRestController<SldzOrder> {



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
    public JsonResult ObtainCouponsAccordingOrderProducts(@Valid @RequestBody SldzOrder entity) throws Exception {
       //唯一编码
        // 获取订单产品id列表


        return super.createEntity(entity);
    }

//    @ApiOperation(value = "选择优惠券，更改应付金额")
//    @PostMapping("/")
//    public JsonResult createEntityMapping(@Valid @RequestBody SldzOrder entity) throws Exception {
//        entity.setOrderNumber(OrderNumberTimeUtil.getOrderIdByTime());
//
//        return super.createEntity(entity);
//    }


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






}
