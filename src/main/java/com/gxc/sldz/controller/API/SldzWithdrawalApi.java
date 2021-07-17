package com.gxc.sldz.controller.API;


import cn.hutool.core.date.DateUtil;
import com.diboot.core.vo.JsonResult;
import com.diboot.core.vo.Pagination;
import com.gxc.sldz.controller.BaseCustomCrudRestController;
import com.gxc.sldz.dto.SldzWithdrawalDTO;
import com.gxc.sldz.entity.*;
import com.gxc.sldz.service.SldzAgentBonusLogService;
import com.gxc.sldz.service.SldzAgentService;
import com.gxc.sldz.service.SldzUserService;
import com.gxc.sldz.service.SldzWithdrawalService;
import com.gxc.sldz.service.impl.SldzOrderServiceImpl;
import com.gxc.sldz.vo.SldzWithdrawalListVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Api(tags = {"提现前台接口"})
@RestController
@RequestMapping("api/sldzWithdrawal")
@Slf4j
public class SldzWithdrawalApi extends BaseCustomCrudRestController<SldzWithdrawal> {


    @Autowired
    private SldzWithdrawalService sldzWithdrawalService;

    @Autowired
    private SldzOrderServiceImpl SldzOrderServiceImpl;


    //奖励金记录服务
    @Autowired
    SldzAgentBonusLogService SldzAgentBonusLogService;

    //用户服务
    @Autowired
    SldzUserService SldzUserServic;
    //代理商服务
    @Autowired
    SldzAgentService SldzAgentService;


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
    public JsonResult getViewObjectListMapping(SldzWithdrawalDTO queryDto, Pagination pagination) throws Exception{
        return super.getViewObjectList(queryDto, pagination, SldzWithdrawalListVO.class);
    }

        /***
    * 创建资源对象
    * @param entity
    * @return JsonResult
    * @throws Exception
    */
    @ApiOperation(value = "新建数据")
    @PostMapping("/")
    public JsonResult createEntityMapping(@Valid @RequestBody SldzWithdrawal entity) throws Exception {
        sldzWithdrawalService.createEntity(entity);
        Map getUser =  SldzOrderServiceImpl.getUser(entity.getRandom());
       int type  = (int) getUser.get("type");
       if (type == 1 ){
           //1代表消费者
           SldzUser SldzUser  = (SldzUser) getUser.get("SldzUser");

           boolean o = false;
           o =  SldzUserServic.ChangeBonus(SldzUser.getBonus() - entity.getWithdrawalAmount(),SldzUser.getRandom());
           //记录奖励金支出记录
           SldzAgentBonusLogService.createEntity(new SldzAgentBonusLog()
                   .setAgentRandom(SldzUser.getRandom())
                   .setRonusDate(DateUtil.now())
                   .setRonusEvent("申请提现："+entity.getWithdrawalAmount())
                   .setRonusMoney(entity.getWithdrawalAmount())
                   .setRonusType(2l));
           if (o){
               return JsonResult.OK();
           }
       }else if (type == 2 ){
           SldzAgent SldzAgent  = (SldzAgent) getUser.get("SldzAgent");
           boolean o = false;
           o =  SldzAgentService.ChangeBonus(SldzAgent.getAgentBonus() - entity.getWithdrawalAmount(),SldzAgent.getAgentRandom());
           //记录奖励金支出记录
           SldzAgentBonusLogService.createEntity(new SldzAgentBonusLog()
                   .setAgentRandom(SldzAgent.getAgentRandom())
                   .setRonusDate(DateUtil.now())
                   .setRonusEvent("申请提现："+entity.getWithdrawalAmount())
                   .setRonusMoney(entity.getWithdrawalAmount())
                   .setRonusType(2l));
           if (o){
               return JsonResult.OK();
           }
       }


        return JsonResult.FAIL_OPERATION("失败");
    }

}
