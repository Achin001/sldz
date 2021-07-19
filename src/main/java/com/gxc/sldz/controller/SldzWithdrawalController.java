package com.gxc.sldz.controller;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.gxc.sldz.entity.*;
import com.gxc.sldz.service.SldzAgentBonusLogService;
import com.gxc.sldz.service.SldzAgentService;
import com.gxc.sldz.service.SldzUserService;
import com.gxc.sldz.service.impl.SldzOrderServiceImpl;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.diboot.core.vo.*;
import com.gxc.sldz.dto.SldzWithdrawalDTO;
import com.gxc.sldz.vo.SldzWithdrawalListVO;
import com.gxc.sldz.vo.SldzWithdrawalDetailVO;
import com.gxc.sldz.service.SldzWithdrawalService;

import lombok.extern.slf4j.Slf4j;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
* 提现 相关Controller
* @author Achin
* @version 1.0
* @date 2021-07-15
* Copyright © MyCompany
*/
@Api(tags = {"提现后台接口"})
@RestController
@RequestMapping("admin/sldzWithdrawal")
@Slf4j
public class SldzWithdrawalController extends BaseCustomCrudRestController<SldzWithdrawal> {
    @Autowired
    private SldzWithdrawalService sldzWithdrawalService;

    //奖励金记录服务
    @Autowired
    SldzAgentBonusLogService SldzAgentBonusLogService;

    //用户服务
    @Autowired
    SldzUserService SldzUserServic;
    //代理商服务
    @Autowired
    SldzAgentService SldzAgentService;

    @Autowired
    private SldzOrderServiceImpl SldzOrderServiceImpl;

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

//    /***
//    * 根据资源id查询ViewObject
//    * @param id ID
//    * @return
//    * @throws Exception
//    */
//    @ApiOperation(value = "根据ID获取详情数据")
//    @GetMapping("/{id}")
//    public JsonResult getViewObjectMapping(@PathVariable("id")Long id) throws Exception{
//        return super.getViewObject(id, SldzWithdrawalDetailVO.class);
//    }
//
//    /***
//    * 创建资源对象
//    * @param entity
//    * @return JsonResult
//    * @throws Exception
//    */
//    @ApiOperation(value = "新建数据")
//    @PostMapping("/")
//    public JsonResult createEntityMapping(@Valid @RequestBody SldzWithdrawal entity) throws Exception {
//        return super.createEntity(entity);
//    }

    /***
    * 根据ID更新资源对象
    * @param
    * @return JsonResult
    * @throws Exception
    */
    @ApiOperation(value = "根据ID处理数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "提现id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "type", value = "处理类型（2发放，3驳回）", required = true, dataType = "Long"),
    })
    @PutMapping("/{id}")
    public JsonResult updateEntityMapping(@PathVariable("id")Long id,Long type) throws Exception {
        if (type == 2){
            //发放
            //改同意状态
            UpdateWrapper<SldzWithdrawal> SldzWithdrawalupdateWrapper = new UpdateWrapper<>();
            SldzWithdrawalupdateWrapper.eq("id", id);
            SldzWithdrawalupdateWrapper.set("state", type);
            SldzWithdrawalupdateWrapper.set("date_after", DateUtil.now());
            if (sldzWithdrawalService.updateEntity(SldzWithdrawalupdateWrapper)){
                return JsonResult.OK();
            }
        }else if(type == 3){
            //驳回
            SldzWithdrawal SldzWithdrawal =  sldzWithdrawalService.getEntity(id);
            Map getUser =  SldzOrderServiceImpl.getUser(SldzWithdrawal.getRandom());
            int types  = (int) getUser.get("type");
            if (types == 1 ){
                //1代表消费者
                SldzUser SldzUser  = (SldzUser) getUser.get("SldzUser");

                boolean o = false;
                o =  SldzUserServic.ChangeBonus(SldzUser.getBonus() + SldzWithdrawal.getWithdrawalAmount(),SldzUser.getRandom());
                //记录奖励金支出记录
                SldzAgentBonusLogService.createEntity(new SldzAgentBonusLog()
                        .setAgentRandom(SldzUser.getRandom())
                        .setRonusDate(DateUtil.now())
                        .setRonusEvent("申请提现驳回："+SldzWithdrawal.getWithdrawalAmount())
                        .setRonusMoney(SldzWithdrawal.getWithdrawalAmount())
                        .setRonusType(1l));

                UpdateWrapper<SldzWithdrawal> SldzWithdrawalupdateWrapper = new UpdateWrapper<>();
                SldzWithdrawalupdateWrapper.eq("id", id);
                SldzWithdrawalupdateWrapper.set("state", type);
                SldzWithdrawalupdateWrapper.set("date_after", DateUtil.now());
                sldzWithdrawalService.updateEntity(SldzWithdrawalupdateWrapper);
                if (o){
                    return JsonResult.OK();
                }
            } else if (types == 2 ){
                SldzAgent SldzAgent  = (SldzAgent) getUser.get("SldzAgent");
                boolean o = false;
                o =  SldzAgentService.ChangeBonus(SldzAgent.getAgentBonus() + SldzWithdrawal.getWithdrawalAmount(),SldzAgent.getAgentRandom());
                //记录奖励金支出记录
                SldzAgentBonusLogService.createEntity(new SldzAgentBonusLog()
                        .setAgentRandom(SldzAgent.getAgentRandom())
                        .setRonusDate(DateUtil.now())
                        .setRonusEvent("申请提现驳回："+SldzWithdrawal.getWithdrawalAmount())
                        .setRonusMoney(SldzWithdrawal.getWithdrawalAmount())
                        .setRonusType(1l));

                UpdateWrapper<SldzWithdrawal> SldzWithdrawalupdateWrapper = new UpdateWrapper<>();
                SldzWithdrawalupdateWrapper.eq("id", id);
                SldzWithdrawalupdateWrapper.set("state", type);
                SldzWithdrawalupdateWrapper.set("date_after", DateUtil.now());
                sldzWithdrawalService.updateEntity(SldzWithdrawalupdateWrapper);
                if (o){
                    return JsonResult.OK();
                }
            }
            //加回奖励金
            //记录奖励金
            //改驳回状态
        }
        return JsonResult.FAIL_OPERATION("处理失败");
    }

//    /***
//    * 根据id删除资源对象
//    * @param id
//    * @return
//    * @throws Exception
//    */
//    @ApiOperation(value = "根据ID删除数据")
//    @DeleteMapping("/{id}")
//    public JsonResult deleteEntityMapping(@PathVariable("id")Long id) throws Exception {
//        return super.deleteEntity(id);
//    }

} 