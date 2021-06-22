package com.gxc.sldz.controller.API;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.api.R;
import com.diboot.core.util.D;
import com.diboot.core.vo.JsonResult;
import com.diboot.core.vo.Pagination;
import com.gxc.sldz.Utils.RedisUtils;
import com.gxc.sldz.controller.BaseCustomCrudRestController;
import com.gxc.sldz.dto.SldzCompanyCouponsDTO;
import com.gxc.sldz.entity.SldzCompanyCoupons;
import com.gxc.sldz.service.SldzCompanyCouponsService;
import com.gxc.sldz.vo.SldzCompanyCouponsListVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Api(tags = {"用户优惠券管理前台接口"})
@RestController
@RequestMapping("api/sldzCompanyCoupons")
@Slf4j
public class SldzCompanyCouponsApi extends BaseCustomCrudRestController<SldzCompanyCoupons> {



    @Autowired
    private SldzCompanyCouponsService sldzCompanyCouponsService;

    @Autowired
    private RedisUtils redisUtils;




    @ApiOperation(value = "获取列表分页数据")
    @GetMapping("/list")
    public JsonResult getViewObjectListMapping(SldzCompanyCouponsDTO queryDto, Pagination pagination) throws Exception{
        return super.getViewObjectList(queryDto, pagination, SldzCompanyCouponsListVO.class);
    }


    @ApiOperation(value = "领取优惠券")
    @PostMapping("/receive")
    public JsonResult receive(String random ,SldzCompanyCouponsDTO queryDto) throws Exception{
        String key = random+"_coupon"+queryDto.getId();
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(queryDto);
        //结束时间
         Date d1 =  queryDto.getCouponsFailureTime();
         //现在时间
        Date d2 = DateUtil.date();
        //这样得到的差值是毫秒级别
        long diff = d1.getTime() - d2.getTime();
        //转换成秒
         diff = diff/1000;
         if (diff<0){
             return JsonResult.FAIL_OPERATION("领取失败");
         }
         //该优惠券只能领取一张
        String s = redisUtils.get(key);
        if (StrUtil.isNotBlank(s)){
            return JsonResult.FAIL_OPERATION("不能重复领取领取");
        }
        redisUtils.set(key, String.valueOf(jsonObject),diff);
        return JsonResult.OK().data("领取成功");
    }



}
