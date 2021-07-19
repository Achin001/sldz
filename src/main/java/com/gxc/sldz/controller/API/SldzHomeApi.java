package com.gxc.sldz.controller.API;


import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.diboot.core.vo.JsonResult;
import com.gxc.sldz.Utils.OrderUtil;
import com.gxc.sldz.Utils.RedisUtils;
import com.gxc.sldz.entity.SldzAgentProductPrice;
import com.gxc.sldz.entity.SldzProduct;
import com.gxc.sldz.service.SldzAgentProductPriceService;
import com.gxc.sldz.vo.SldzProductListVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Api(tags = {"首页管理前台接口"})
@RestController
@RequestMapping("api/Home")
@Slf4j
public class SldzHomeApi {


    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    SldzAgentProductPriceService SldzAgentProductPriceService;


    @ApiOperation(value = "获取今日折扣")
    @PostMapping("/GetToDaysDiscount")
    public JsonResult GetToDaysDiscount(String Random) throws Exception {
        return JsonResult.OK().data(gt(Random,"ToDaysDiscount"));
    }


    @ApiOperation(value = "获取热销榜单")
    @PostMapping("/GetHotList")
    public JsonResult GetHotList(String Random) throws Exception {
        return JsonResult.OK().data(gt(Random,"HotList"));
    }


    @ApiOperation(value = "获取精选推荐")
    @PostMapping("/GetSelectedRecommendation")
    public JsonResult GetSelectedRecommendation(String Random) throws Exception {
        return JsonResult.OK().data(gt(Random,"SelectedRecommendation"));
    }




    public  List<SldzProductListVO> gt(String Random,String key) {
        List<SldzProductListVO> SldzProductListVOs =  OrderUtil.AnalysisHomeProducts(redisUtils.get(key));
        for (SldzProductListVO sasa : SldzProductListVOs){
            if (StrUtil.isNotBlank(Random)){
                LambdaQueryWrapper<SldzAgentProductPrice> SldzAgentProductPricewrapper = new LambdaQueryWrapper<>();
                SldzAgentProductPricewrapper.eq(SldzAgentProductPrice::getProductId, sasa.getId());
                SldzAgentProductPricewrapper.eq(SldzAgentProductPrice::getAgentRandom, Random);
                SldzAgentProductPrice SldzAgentProductPrice =  SldzAgentProductPriceService.getSingleEntity(SldzAgentProductPricewrapper);
                if (ObjectUtil.isNotNull(SldzAgentProductPrice)){
                    sasa.setFavorablePrice(SldzAgentProductPrice.getProductPrice());
                }else {
                    sasa.setFavorablePrice(sasa.getProductPrice());
                }
            }else {
                sasa.setFavorablePrice(sasa.getProductPrice());
            }

        }
        return SldzProductListVOs;
    }

}
