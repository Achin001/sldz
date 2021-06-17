package com.gxc.sldz.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.gxc.sldz.entity.SldzAdmin;
import com.gxc.sldz.entity.SldzAgentIntegralLog;
import com.gxc.sldz.entity.SldzAgentRel;
import com.gxc.sldz.service.*;
import com.gxc.sldz.vo.SuperiorShouldBeRewardedVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.diboot.core.vo.*;
import com.gxc.sldz.entity.SldzAgent;
import com.gxc.sldz.dto.SldzAgentDTO;
import com.gxc.sldz.vo.SldzAgentListVO;
import com.gxc.sldz.vo.SldzAgentDetailVO;
import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * 代理商 相关Controller
 *
 * @author Achin
 * @version 1.0
 * @date 2021-05-31
 * Copyright © MyCompany
 */
@Api(tags = {"代理商后台接口"})
@RestController
@RequestMapping("admin/sldzAgent")
@Slf4j
public class SldzAgentController extends BaseCustomCrudRestController<SldzAgent> {

    //代理商服务
    @Autowired
    private SldzAgentService sldzAgentService;

    //代理商上下级
    @Autowired
    private SldzAgentRelService sldzAgentRelService;

    @Autowired
    RandomServer RandomServer;

    //代理商奖励模版
    @Autowired
    private SldzAgentLevelRewardService sldzAgentLevelRewardService;

    //代理商积分记录
    @Autowired
    private SldzAgentIntegralLogService sldzAgentIntegralLogService;


    /**
     * 查询ViewObject的分页数据
     * <p>
     * url请求参数示例: /list?field=abc&pageIndex=1&orderBy=abc:DESC
     * </p>
     *
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "获取列表分页数据")
    @GetMapping("/list")
    public JsonResult getViewObjectListMapping(SldzAgentDTO queryDto, Pagination pagination) throws Exception {
        return super.getViewObjectList(queryDto, pagination, SldzAgentListVO.class);
    }

    @ApiOperation(value = "代理商模糊搜索")
    @GetMapping("/keywords")
    public JsonResult keywords(SldzAgentDTO queryDto, Pagination pagination) throws Exception {
        String name = "agent_name";
        String agent_grade_id = "agent_grade_id";
        QueryWrapper<SldzAgent> wrapper = new QueryWrapper();
        wrapper.like(StrUtil.isNotBlank(queryDto.getAgentName()), name, queryDto.getAgentName());
        // SldzAgent SldzAgent =  sldzAgentService.getSingleEntity(wrapper);
        return super.getEntityListWithPaging(wrapper, pagination);
        // return super.getViewObjectList(queryDto, pagination, SldzAgentListVO.class);
    }

    /**
     * 根据资源id查询ViewObject
     *
     * @param id ID
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "根据ID获取详情数据")
    @GetMapping("/{id}")
    public JsonResult getViewObjectMapping(@PathVariable("id") Long id) throws Exception {
        return super.getViewObject(id, SldzAgentDetailVO.class);
    }

    /**
     * 创建资源对象
     *
     * @param entity
     * @return JsonResult
     * @throws Exception
     */
    @ApiOperation(value = "新建代理商数据")
    @PostMapping("/")
    public JsonResult createEntityMapping(@Valid @RequestBody SldzAgent entity) throws Exception {
        entity.setAgentPasword(SecureUtil.md5(entity.getAgentPhone()));
        entity.setAgentRandom(RandomServer.getRandom());
        return super.createEntity(entity);
    }

    /**
     * 根据ID更新资源对象
     *
     * @param entity
     * @return JsonResult
     * @throws Exception
     */
    @ApiOperation(value = "根据ID更新代理商数据")
    @PutMapping("/{id}")
    public JsonResult updateEntityMapping(@PathVariable("id") Long id, @Valid @RequestBody SldzAgent entity) throws Exception {
        return super.updateEntity(id, entity);
    }

    @ApiOperation(value = "确认充值积分")
    @PutMapping("Recharge")
    public JsonResult RechargeByid(
            String Random,
            double RechargePoints,
            @Valid @RequestBody List<SuperiorShouldBeRewardedVO> SuperiorShouldBeRewardedVO) throws Exception {

        //充值人加积分
        if (sldzAgentService.RechargeByRandom(RechargePoints, Random)) {
           //充值成功
            //记录充值记录（收入）
            sldzAgentIntegralLogService.createEntity(new SldzAgentIntegralLog()
                    .setAgentRandom(Random)
                    .setIntegralDate(DateUtil.now())
                    .setIntegralEvent("充值积分："+RechargePoints)
                    .setIntegralMoney(RechargePoints)
                    .setIntegralType(1l));
            //奖励人加奖励金
            for(SuperiorShouldBeRewardedVO  s :  SuperiorShouldBeRewardedVO){
                //记录奖励金记录（收入）

                s.getAgentRandom();
                sldzAgentIntegralLogService.createEntity(new SldzAgentIntegralLog());
            }

        }







        return JsonResult.FAIL_OPERATION("添加失败");
    }

    @ApiOperation(value = "根据充值对象查询该上级应得奖励")
    @GetMapping("AccordingToRechargeObjectQuerySuperiorShouldBeRewarded")
    public JsonResult AccordingToRechargeObjectQuerySuperiorShouldBeRewarded(String Random, double RechargePoints) throws Exception {
        SuperiorShouldBeRewardedVO SuperiorShouldBeRewardedVO1 = new SuperiorShouldBeRewardedVO();
        SuperiorShouldBeRewardedVO SuperiorShouldBeRewardedVO2 = new SuperiorShouldBeRewardedVO();
        List<SuperiorShouldBeRewardedVO> SuperiorShouldBeRewardedVOs = new ArrayList<>();
        // 获取上级对象
        SldzAgent AgentSup = getSup(Random);
        LambdaQueryWrapper<SldzAgent> supReward = new LambdaQueryWrapper<>();
        supReward.eq(SldzAgent::getAgentRandom, AgentSup.getAgentRandom());
        //得到上级奖励比例
        double SupRewardRatio = sldzAgentLevelRewardService.getSingleEntity(supReward).getRewardDirect();
        //奖金=充值金额*比例
        SuperiorShouldBeRewardedVO1.setAgentName(AgentSup.getAgentName());
        SuperiorShouldBeRewardedVO1.setAgentRandom(AgentSup.getAgentRandom());
        SuperiorShouldBeRewardedVO1.setProportion(SupRewardRatio);
        SuperiorShouldBeRewardedVO1.setRelationship("上级");
        SuperiorShouldBeRewardedVO1.setBonus(NumberUtil.mul(RechargePoints, SupRewardRatio));


        // 获取上上级对象
        SldzAgent AgentSupSup = getSupSup(Random);
        LambdaQueryWrapper<SldzAgent> supsupReward = new LambdaQueryWrapper<>();
        supsupReward.eq(SldzAgent::getAgentRandom, AgentSupSup.getAgentRandom());
        //得到上上级奖励比例
        double SupSupRewardRatio = sldzAgentLevelRewardService.getSingleEntity(supsupReward).getRewardIndirect();
        //奖金=充值金额*比例
        SuperiorShouldBeRewardedVO2.setAgentName(AgentSupSup.getAgentName());
        SuperiorShouldBeRewardedVO2.setAgentRandom(AgentSupSup.getAgentRandom());
        SuperiorShouldBeRewardedVO2.setProportion(SupSupRewardRatio);
        SuperiorShouldBeRewardedVO2.setRelationship("上上级");
        SuperiorShouldBeRewardedVO2.setBonus(NumberUtil.mul(RechargePoints, SupSupRewardRatio));

        SuperiorShouldBeRewardedVOs.add(SuperiorShouldBeRewardedVO1);
        SuperiorShouldBeRewardedVOs.add(SuperiorShouldBeRewardedVO2);
        return JsonResult.OK().data(SuperiorShouldBeRewardedVOs);
    }


    /**
     * 根据本级Random获取上级对象
     *
     * @param Random
     * @return SldzAgent 上级对象
     * @throws Exception
     */
    public SldzAgent getSup(String Random) {
        // 查询上级
        SldzAgentRel SldzAgentRel = sldzAgentRelService.sub_find_sup(Random);
        LambdaQueryWrapper<SldzAgent> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SldzAgent::getAgentRandom, SldzAgentRel.getSupRandom());
        return sldzAgentService.getSingleEntity(wrapper);
    }


    /**
     * 根据本级Random获取上上级对象
     *
     * @param Random
     * @return SldzAgent 上上级对象
     * @throws Exception
     */
    public SldzAgent getSupSup(String Random) {
        // 查询上级
        SldzAgentRel SldzAgentRel = sldzAgentRelService.sub_find_supsup(Random);
        LambdaQueryWrapper<SldzAgent> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SldzAgent::getAgentRandom, SldzAgentRel.getSupRandom());
        return sldzAgentService.getSingleEntity(wrapper);
    }

}
