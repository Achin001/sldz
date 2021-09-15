package com.gxc.sldz.controller;


import com.diboot.core.vo.JsonResult;
import com.diboot.core.vo.Pagination;
import com.gxc.sldz.Utils.DateUtils;
import com.gxc.sldz.dto.SldzProductDTO;
import com.gxc.sldz.service.SldzOrderService;
import com.gxc.sldz.vo.SldzProductListVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = {"后台首页接口"})
@RestController
@RequestMapping("admin/HomePage")
@Slf4j
public class SldzHomePageController {



    //订单服务
    @Autowired
    private SldzOrderService sldzOrderService;


    @ApiOperation(value = "获取首页轮播图数据")
    @GetMapping("/GetHomePageCarouselData")
    public JsonResult GetHomePageCarouselData() throws Exception {
        //近12个月份
        List<String> getThisyear = DateUtils.getThisyear();

        //近12个月订单
        List<Integer> AnnualOrder = new ArrayList();

        //近12个月GMV
        List<Double> AnnualSalesVolume = new ArrayList();

        //近12个月月活
        List<Integer> AnnualMonthlyLiving = new ArrayList();

        for (String month : getThisyear){
            AnnualOrder.add(sldzOrderService.ObtainMonthlyOrderQuantityAccordingOrderStatus(3,month));
            AnnualSalesVolume.add(sldzOrderService.GetAmountPaidInOrdersInTheMonthAccordingOrderStatus(3,month));
            AnnualMonthlyLiving.add(12);
        }
        Map map = new HashMap();
        map.put("getThisyear",getThisyear);
        map.put("AnnualOrder",AnnualOrder);
        map.put("AnnualSalesVolume",AnnualSalesVolume);
        map.put("AnnualMonthlyLiving",AnnualMonthlyLiving);
        return JsonResult.OK().data(map);
    }


    @ApiOperation(value = "获取首页产品热销数据数据")
    @GetMapping("/GetBestSellingDataProductsHomePage")
    public JsonResult GetBestSellingDataProductsHomePage() throws Exception {
        //近12个月份
        List<String> getThisyear = DateUtils.getThisyear();

        //近12个月订单
        List<Integer> AnnualOrder = new ArrayList();

        //近12个月GMV
        List<Double> AnnualSalesVolume = new ArrayList();

        //近12个月月活
        List<Integer> AnnualMonthlyLiving = new ArrayList();

        for (String month : getThisyear){
            AnnualOrder.add(sldzOrderService.ObtainMonthlyOrderQuantityAccordingOrderStatus(3,month));
            AnnualSalesVolume.add(sldzOrderService.GetAmountPaidInOrdersInTheMonthAccordingOrderStatus(3,month));
            AnnualMonthlyLiving.add(12);
        }
        Map map = new HashMap();
        map.put("getThisyear",getThisyear);
        map.put("AnnualOrder",AnnualOrder);
        map.put("AnnualSalesVolume",AnnualSalesVolume);
        map.put("AnnualMonthlyLiving",AnnualMonthlyLiving);

        return JsonResult.OK().data(map);
    }


}
