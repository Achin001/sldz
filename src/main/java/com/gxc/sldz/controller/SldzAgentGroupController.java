package com.gxc.sldz.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gxc.sldz.entity.*;
import com.gxc.sldz.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.diboot.core.vo.*;
import com.gxc.sldz.dto.SldzAgentGroupDTO;
import com.gxc.sldz.vo.SldzAgentGroupListVO;
import com.gxc.sldz.vo.SldzAgentGroupDetailVO;

import lombok.extern.slf4j.Slf4j;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* 代理商分组 相关Controller
* @author Achin
* @version 1.0
* @date 2021-06-09
* Copyright © MyCompany
*/
@Api(tags = {"代理商分组后台接口"})
@RestController
@RequestMapping("admin/sldzAgentGroup")
@Slf4j
public class SldzAgentGroupController extends BaseCustomCrudRestController<SldzAgentGroup> {
    @Autowired
    private SldzAgentGroupService sldzAgentGroupService;

    @Autowired
    private SldzAgentService sldzAgentService;

    @Autowired
    private SldzBonuSsettingService sldzBonuSsettingService;

    @Autowired
    private SldzAgentProductPriceService sldzAgentProductPriceService;

    @Autowired
    private SldzAgentLevelRewardService sldzAgentLevelRewardService;

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
    public JsonResult getViewObjectListMapping(SldzAgentGroupDTO queryDto, Pagination pagination) throws Exception{
        return super.getViewObjectList(queryDto, pagination, SldzAgentGroupListVO.class);
    }

    /***
    * 根据资源id查询ViewObject
    * @param id ID
    * @return
    * @throws Exception
    */
//    @ApiOperation(value = "根据ID获取详情数据")
//    @GetMapping("/{id}")
//    public JsonResult getViewObjectMapping(@PathVariable("id")Long id) throws Exception{
//        return super.getViewObject(id, SldzAgentGroupDetailVO.class);
//    }

    /***
    * 创建资源对象
    * @param entity
    * @return JsonResult
    * @throws Exception
    */
    @ApiOperation(value = "新建数据")
    @PostMapping("/")
    public JsonResult createEntityMapping(@Valid @RequestBody SldzAgentGroup entity) throws Exception {
        return super.createEntity(entity);
    }

    /***
    * 根据ID更新资源对象
    * @param entity
    * @return JsonResult
    * @throws Exception
    */
    @ApiOperation(value = "根据ID更新数据")
    @PutMapping("/{id}")
    public JsonResult updateEntityMapping(@PathVariable("id")Long id, @Valid @RequestBody SldzAgentGroup entity) throws Exception {
        return super.updateEntity(id, entity);
    }





    @ApiOperation(value = "获取未分组的代理商")
    @GetMapping("/getUngroupedAgents")
    public JsonResult getUngroupedAgents( ) throws Exception{
        LambdaQueryWrapper<SldzAgent> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SldzAgent::getAgentGroupId,0);
        return JsonResult.OK().data(sldzAgentService.getEntityList(wrapper));
    }

    @ApiOperation(value = "获取该分组成员")
    @GetMapping("/getgroupedAgents")
    public JsonResult getgroupedAgents(Long groupId) throws Exception{
        LambdaQueryWrapper<SldzAgent> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SldzAgent::getAgentGroupId,groupId);
        return JsonResult.OK().data(sldzAgentService.getEntityList(wrapper));
    }

    @ApiOperation(value = "为分组添加代理商")
    @PutMapping("/agentToGroupAdd")
    public JsonResult agentToGroupAdd(Long groupId, Long agentId) throws Exception {
        //把该代理商的id改成该分组id
        UpdateWrapper<SldzAgent> wrapper = new UpdateWrapper();
        wrapper.set("agent_group_id",groupId);
        wrapper.eq("id",agentId);
         if (sldzAgentService.updateEntity(wrapper)){
             return JsonResult.OK().data("添加成功");
         }
        return JsonResult.FAIL_OPERATION("添加失败");
    }

    @ApiOperation(value = "为分组移除代理商")
    @PutMapping("/agentToGroupRemove")
    public JsonResult agentToGroupRemove(Long agentId) throws Exception {
        //把该代理商的id改成该分组id
        UpdateWrapper<SldzAgent> wrapper = new UpdateWrapper();
        wrapper.set("agent_group_id",0);
        wrapper.eq("id",agentId);
        if (sldzAgentService.updateEntity(wrapper)){
            return JsonResult.OK().data("移除成功");
        }
        return JsonResult.FAIL_OPERATION("移除失败");
    }


    @ApiOperation(value = "为分组设置推广佣金")
    @PutMapping("/groupBonusSetting")
    public JsonResult groupBonusSetting(Long groupId,
                                        Long productId,
                                        double Bonus) throws Exception {
        //记录成功次数
        int los = 0;
        //获取该分组下所有代理商
        LambdaQueryWrapper<SldzAgent> wrapper = new LambdaQueryWrapper();
        wrapper.eq(SldzAgent::getAgentGroupId,groupId);
        List<SldzAgent> SldzAgents =  sldzAgentService.getEntityList(wrapper);
        LambdaQueryWrapper<SldzBonuSsetting> BonuSsettingwrapper = new LambdaQueryWrapper();
        for (SldzAgent s :SldzAgents){
            //查询该代理商有无奖励金
            BonuSsettingwrapper.eq(SldzBonuSsetting::getAgentRandom,s.getAgentRandom());
            BonuSsettingwrapper.eq(SldzBonuSsetting::getProductId,productId);
            SldzBonuSsetting SldzBonuSsettings =  sldzBonuSsettingService.getSingleEntity(BonuSsettingwrapper);
            if (ObjectUtil.isNotNull(SldzBonuSsettings)){
                //有记录则更改
                UpdateWrapper<SldzAgent> UpdatewrapperBonuSsetting = new UpdateWrapper();
                UpdatewrapperBonuSsetting.set("bonus",Bonus);
                UpdatewrapperBonuSsetting.eq("product_id",productId);
                UpdatewrapperBonuSsetting.eq("agent_random",s.getAgentRandom());
                if(sldzBonuSsettingService. updateEntity(UpdatewrapperBonuSsetting)){
                    los+=1;
                }
            }else {
                //无记录则新增
                if ( sldzBonuSsettingService.createEntity(
                        new SldzBonuSsetting()
                        .setAgentRandom(s.getAgentRandom())
                        .setBonus(Bonus)
                        .setProductId(productId))){
                    los+=1;
                }
            }
        }
        Map map = new HashMap();
        map.put("msg",los+"人已更改为："+Bonus);
        return JsonResult.OK().data(map);
    }


    @ApiOperation(value = "为分组设置产品价格")
    @PutMapping("/groupProductPriceSetting")
    public JsonResult groupProductPriceSetting(Long groupId,
                                        Long productId,
                                        double price) throws Exception {
        //记录成功次数
        int los = 0;
        //获取该分组下所有代理商
        LambdaQueryWrapper<SldzAgent> wrapper = new LambdaQueryWrapper();
        wrapper.eq(SldzAgent::getAgentGroupId,groupId);
        List<SldzAgent> SldzAgents =  sldzAgentService.getEntityList(wrapper);
        LambdaQueryWrapper<SldzAgentProductPrice> SldzAgentProductPricewrapper = new LambdaQueryWrapper();
        for (SldzAgent s :SldzAgents){
            //查询该代理商有无该产的价格
            SldzAgentProductPricewrapper.eq(SldzAgentProductPrice::getAgentRandom,s.getAgentRandom());
            SldzAgentProductPricewrapper.eq(SldzAgentProductPrice::getProductId,productId);
            SldzAgentProductPrice SldzAgentProductPrice = sldzAgentProductPriceService.getSingleEntity(SldzAgentProductPricewrapper);
            if (ObjectUtil.isNotNull(SldzAgentProductPrice)){
                //有记录则更改
                UpdateWrapper<SldzAgentProductPrice> UpdatewrapperSldzAgentProductPrice = new UpdateWrapper();
                UpdatewrapperSldzAgentProductPrice.set("product_price",price);
                UpdatewrapperSldzAgentProductPrice.eq("product_id",productId);
                UpdatewrapperSldzAgentProductPrice.eq("agent_random",s.getAgentRandom());
                if(sldzAgentProductPriceService. updateEntity(UpdatewrapperSldzAgentProductPrice)){
                    los+=1;
                }
            }else {
                //无记录则新增
                if ( sldzAgentProductPriceService.createEntity(
                        new SldzAgentProductPrice()
                                .setAgentRandom(s.getAgentRandom())
                                .setProductPrice(price)
                                .setProductId(productId))){
                    los+=1;
                }
            }
        }
        Map map = new HashMap();
        map.put("msg",los+"人已更改为："+price);
        return JsonResult.OK().data(map);
    }


    @ApiOperation(value = "为分组设置直推间推报酬")
    @PutMapping("/groupRemuneration")
    public JsonResult groupRemuneration(Long groupId,
                                        double RewardDirect,
                                        double RewardIndirect) throws Exception {

        //记录成功次数
        int los = 0;
        //获取该分组下所有代理商
        LambdaQueryWrapper<SldzAgent> wrapper = new LambdaQueryWrapper();
        wrapper.eq(SldzAgent::getAgentGroupId,groupId);
        List<SldzAgent> SldzAgents =  sldzAgentService.getEntityList(wrapper);

        for (SldzAgent s :SldzAgents){
            //查询该代理商有无奖励
            LambdaQueryWrapper<SldzAgentLevelReward> SldzAgentLevelRewardwrapper = new LambdaQueryWrapper();
            SldzAgentLevelRewardwrapper.eq(SldzAgentLevelReward::getAgentRandom,s.getAgentRandom());
            SldzAgentLevelReward SldzAgentLevelReward = sldzAgentLevelRewardService.getSingleEntity(SldzAgentLevelRewardwrapper);
            if (ObjectUtil.isNotNull(SldzAgentLevelReward)){
                //有记录则更改
                UpdateWrapper<SldzAgentLevelReward> UpdatewrapperSldzAgentLevelReward = new UpdateWrapper();
                UpdatewrapperSldzAgentLevelReward.set("reward_direct",RewardDirect);
                UpdatewrapperSldzAgentLevelReward.set("reward_indirect",RewardIndirect);
                UpdatewrapperSldzAgentLevelReward.eq("agent_random",s.getAgentRandom());
                if(sldzAgentLevelRewardService. updateEntity(UpdatewrapperSldzAgentLevelReward)){
                    los+=1;
                }
            }else {
                //无记录则新增
                if ( sldzAgentLevelRewardService.createEntity(
                        new SldzAgentLevelReward()
                                .setAgentRandom(s.getAgentRandom())
                                .setRewardDirect(RewardDirect)
                                .setRewardIndirect(RewardIndirect))){
                    los+=1;
                }
            }
        }
        Map map = new HashMap();
        map.put("msg",los+"人已更改");
        return JsonResult.OK().data(map);
    }






    /***
    * 根据id删除资源对象
    * @param id
    * @return
    * @throws Exception
    */
//    @ApiOperation(value = "根据ID删除数据")
//    @DeleteMapping("/{id}")
//    public JsonResult deleteEntityMapping(@PathVariable("id")Long id) throws Exception {
//        return super.deleteEntity(id);
//    }


} 