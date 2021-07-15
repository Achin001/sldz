package com.gxc.sldz.controller.API;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.diboot.core.util.S;
import com.diboot.core.vo.JsonResult;
import com.diboot.core.vo.Pagination;
import com.gxc.sldz.entity.SldzAgentBonusLog;
import com.gxc.sldz.entity.SldzAgentIntegralLog;
import com.gxc.sldz.entity.SldzUser;
import com.gxc.sldz.service.SldzAgentBonusLogService;
import com.gxc.sldz.service.SldzAgentIntegralLogService;
import com.gxc.sldz.vo.SldzUserDetailVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Api(tags = {"收入支出前台接口"})
@RestController
@RequestMapping("api/SldzIncomeDetails")
@Slf4j
public class SldzIncomeDetailsApi {


    //奖励金记录服务
    @Autowired
   SldzAgentBonusLogService SldzAgentBonusLogService;


    //积分记录服务
    @Autowired
    SldzAgentIntegralLogService sldzAgentIntegralLogService;



    @ApiOperation(value = "获取收入明细")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "random", value = "唯一编号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "type", value = "类型（1收入，2支出）", required = true, dataType = "String"),
            @ApiImplicitParam(name = "pol", value = "切换（1奖励金，2积分）", required = true, dataType = "String"),
            @ApiImplicitParam(name = "date", value = "日期（2012-03）", required = true, dataType = "String"),
    })
    @GetMapping("/GetiIncomeDetails")
    public JsonResult GetiIncomeDetails(String random,
                                        int type,
                                        int pol,
                                        String date, Pagination pagination) throws Exception {
        if (pol ==1 ){
            //1奖励金
            LambdaQueryWrapper<SldzAgentBonusLog> wrapperUser = new LambdaQueryWrapper<>();
            wrapperUser.eq(SldzAgentBonusLog::getRonusType, type);
            wrapperUser.eq(SldzAgentBonusLog::getAgentRandom, random);
            wrapperUser.likeRight(SldzAgentBonusLog::getRonusDate,date);
            return JsonResult.OK().data(SldzAgentBonusLogService.getEntityList(wrapperUser,pagination));
        }else if (pol == 2 ){
            //2积分
            LambdaQueryWrapper<SldzAgentIntegralLog> wrapperUser = new LambdaQueryWrapper<>();
            wrapperUser.eq(SldzAgentIntegralLog::getIntegralType, type);
            wrapperUser.eq(SldzAgentIntegralLog::getAgentRandom, random);
            wrapperUser.likeRight(SldzAgentIntegralLog::getIntegralDate,date);
            return JsonResult.OK().data(sldzAgentIntegralLogService.getEntityList(wrapperUser,pagination));
        }
        return null;
    }



}
