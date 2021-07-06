package com.gxc.sldz.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.diboot.core.vo.JsonResult;
import com.gxc.sldz.Utils.RedisUtils;
import com.gxc.sldz.entity.SldzOrder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"首页管理后台接口"})
@RestController
@RequestMapping("admin/Home")
@Slf4j
public class SldzHomeController {


    @Autowired
    private RedisUtils redisUtils;


    @ApiOperation(value = "添加今日折扣")
    @PostMapping("/ToDaysDiscount")
    public JsonResult ToDaysDiscount(@RequestBody String s) throws Exception {
        return JsonResult.OK().data(redisUtils.set("ToDaysDiscount", s));
    }

    @ApiOperation(value = "获取今日折扣")
    @PostMapping("/GetToDaysDiscount")
    public JsonResult GetToDaysDiscount() throws Exception {
        return JsonResult.OK().data(redisUtils.get("ToDaysDiscount"));
    }


    @ApiOperation(value = "添加热销榜单")
    @PostMapping("/HotList")
    public JsonResult HotList(@RequestBody String s) throws Exception {
        return JsonResult.OK().data(redisUtils.set("HotList", s));
    }

    @ApiOperation(value = "获取热销榜单")
    @PostMapping("/GetHotList")
    public JsonResult GetHotList() throws Exception {
        return JsonResult.OK().data(redisUtils.get("HotList"));
    }


    @ApiOperation(value = "添加精选推荐")
    @PostMapping("/SelectedRecommendation")
    public JsonResult SelectedRecommendation(@RequestBody String s) throws Exception {
        return JsonResult.OK().data(redisUtils.set("SelectedRecommendation", s));
    }

    @ApiOperation(value = "获取精选推荐")
    @PostMapping("/GetSelectedRecommendation")
    public JsonResult GetSelectedRecommendation() throws Exception {
        return JsonResult.OK().data(redisUtils.get("SelectedRecommendation"));
    }


}

