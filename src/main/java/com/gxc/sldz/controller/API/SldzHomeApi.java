package com.gxc.sldz.controller.API;


import com.diboot.core.vo.JsonResult;
import com.gxc.sldz.Utils.RedisUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"首页管理前台接口"})
@RestController
@RequestMapping("api/Home")
@Slf4j
public class SldzHomeApi {


    @Autowired
    private RedisUtils redisUtils;


    @ApiOperation(value = "获取今日折扣")
    @PostMapping("/GetToDaysDiscount")
    public JsonResult GetToDaysDiscount() throws Exception {
        return JsonResult.OK().data(redisUtils.get("ToDaysDiscount"));
    }


    @ApiOperation(value = "获取热销榜单")
    @PostMapping("/GetHotList")
    public JsonResult GetHotList() throws Exception {
        return JsonResult.OK().data(redisUtils.get("HotList"));
    }


    @ApiOperation(value = "获取精选推荐")
    @PostMapping("/GetSelectedRecommendation")
    public JsonResult GetSelectedRecommendation() throws Exception {
        return JsonResult.OK().data(redisUtils.get("SelectedRecommendation"));
    }


}
