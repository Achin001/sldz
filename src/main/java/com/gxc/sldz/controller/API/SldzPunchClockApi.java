package com.gxc.sldz.controller.API;


import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.diboot.core.vo.JsonResult;
import com.diboot.core.vo.Pagination;
import com.gxc.sldz.controller.BaseCustomCrudRestController;
import com.gxc.sldz.dto.SldzPunchClockDTO;
import com.gxc.sldz.entity.SldzAgentIntegralLog;
import com.gxc.sldz.entity.SldzOrder;
import com.gxc.sldz.entity.SldzPunchClock;
import com.gxc.sldz.entity.SldzUser;
import com.gxc.sldz.service.SldzAgentIntegralLogService;
import com.gxc.sldz.service.SldzPunchClockService;
import com.gxc.sldz.service.impl.SldzAgentIntegralLogServiceImpl;
import com.gxc.sldz.vo.SldzPunchClockListVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Api(tags = {"签到前台接口"})
@RestController
@RequestMapping("api/sldzPunchClock")
@Slf4j
public class SldzPunchClockApi extends BaseCustomCrudRestController<SldzPunchClock> {


    @Autowired
    private SldzPunchClockService sldzPunchClockService;
    //积分记录服务
    @Autowired
    SldzAgentIntegralLogService sldzAgentIntegralLogService;


    @ApiOperation(value = "获取列表分页数据")
    @GetMapping("/list")
    public JsonResult getViewObjectListMapping(SldzPunchClockDTO queryDto, Pagination pagination) throws Exception{
        return super.getViewObjectList(queryDto, pagination, SldzPunchClockListVO.class);
    }


    /***
     * 创建资源对象
     * @param entity
     * @return JsonResult
     * @throws Exception
     */
    @Transactional
    @ApiOperation(value = "新建数据")
    @PostMapping("/")
    public JsonResult createEntityMapping(@Valid @RequestBody SldzPunchClock entity) throws Exception {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        LambdaQueryWrapper<SldzPunchClock> wrapper1 = new LambdaQueryWrapper<>();
//        wrapper1.eq(SldzPunchClock::getRandom, entity.getRandom());
//        wrapper1.eq(SldzPunchClock::getClockDate, entity.getClockDate());
//        //判断数据库有无当天签到数据
//        if (ObjectUtil.isNotNull(sldzAgentIntegralLogService.getSingleEntity(wrapper1))){
//            return JsonResult.FAIL_OPERATION("请勿重复签到");
//        }
        entity.setPointsGained(1.00);
        LambdaQueryWrapper<SldzPunchClock> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SldzPunchClock::getRandom, entity.getRandom());
        int  s = sldzPunchClockService.getEntityList(wrapper).size() +1;
        entity.setContinuousCheckDays(s);
        //记录积分
        sldzAgentIntegralLogService.createEntity(new SldzAgentIntegralLog()
                .setAgentRandom(entity.getRandom())
                .setIntegralDate(DateUtil.now())
                .setIntegralEvent("签到获得积分：1.00,连续签到"+s+"天。")
                .setIntegralMoney(1.00)
                .setIntegralType(1l));
        return super.createEntity(entity);
    }

//    @Transactional
//    @ApiOperation(value = "判断本日有无签到")
//    @GetMapping("/CheckToday")
//    public JsonResult CheckToday(String Random) throws Exception {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        LambdaQueryWrapper<SldzPunchClock> wrapper1 = new LambdaQueryWrapper<>();
//        wrapper1.eq(SldzPunchClock::getRandom, Random);
//        wrapper1.eq(SldzPunchClock::getClockDate, sdf.format(new Date()));
//        //判断数据库有无当天签到数据
//        if (ObjectUtil.isNotNull(sldzAgentIntegralLogService.getSingleEntity(wrapper1))){
//            return JsonResult.FAIL_OPERATION("请勿重复签到");
//        }
//        return null;
//    }


//    @ApiOperation(value = "获取本月签到数据")
//    @GetMapping("/GetPunchClockDataThisMonth")
//    public JsonResult GetPunchClockDataThisMonth(String random) throws Exception {
//        //总积分
//        LambdaQueryWrapper<SldzPunchClock> wrapper = new LambdaQueryWrapper<>();
//        wrapper.eq(SldzPunchClock::getRandom, random);
//       List <SldzPunchClock>  SldzPunchClock =  sldzPunchClockService.getEntityList(wrapper);
//
//        return null;
//    }



    public static int getDaysOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

}
