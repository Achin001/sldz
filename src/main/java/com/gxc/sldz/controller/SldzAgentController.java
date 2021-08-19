package com.gxc.sldz.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.MD5;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.gxc.sldz.entity.*;
import com.gxc.sldz.service.*;
import com.gxc.sldz.vo.SuperiorShouldBeRewardedVO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.diboot.core.vo.*;
import com.gxc.sldz.dto.SldzAgentDTO;
import com.gxc.sldz.vo.SldzAgentListVO;
import com.gxc.sldz.vo.SldzAgentDetailVO;
import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    //奖励金记录服务
    @Autowired
    private SldzAgentBonusLogService sldzAgentBonusLogService;
    //产品价格服务
    @Autowired
    private SldzAgentProductPriceService sldzAgentProductPriceService;
    //推广佣金服务
    @Autowired
    private SldzBonuSsettingService sldzBonuSsettingService;

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

    //客户user rel
    @Autowired
    private SldzUserRelService sldzUserRelService;

    //客户user
    @Autowired
    private SldzUserService SldzUserService;

    //客户user
    @Autowired
    private SldzCustomerProfileService sldzCustomerProfileService;

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

    @ApiOperation(value = "重置密码")
    @PutMapping("/resetPassword")
    public JsonResult resetPassword(@Valid @RequestBody int agentId) throws Exception {
        SldzAgent  entity = sldzAgentService.getEntity(agentId);
        entity.setAgentPasword(SecureUtil.md5(entity.getAgentPhone()));
        return super.updateEntity(agentId,entity);
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
        int i = 0;
        SldzAgent sldzAgent =  getAgent(Random);
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
                if (sldzAgentService. PluszBonusByRandom(s.getBonus(),s.getAgentRandom())){
                    //记录奖励金记录（收入）
                    sldzAgentBonusLogService.createEntity(new SldzAgentBonusLog()
                            .setAgentRandom(s.getAgentRandom())
                            .setRonusType(1l)
                            .setRonusMoney(s.getBonus())
                            .setRonusDate(DateUtil.now())
                            .setRonusEvent(sldzAgent.getAgentName()+"充值："+RechargePoints+",您获得"+s.getBonus()+"("+s.getProportion()+")")
                    );
                    i++;
                }
            }
            return JsonResult.OK().data("充值成功,"+"奖励金发放成功人数："+i);
        }
        return JsonResult.FAIL_OPERATION("充值失败");
    }


    @ApiOperation(value = "根据充值对象查询该上级应得奖励")
    @GetMapping("AccordingToRechargeObjectQuerySuperiorShouldBeRewarded")
    public JsonResult AccordingToRechargeObjectQuerySuperiorShouldBeRewarded(String Random, double RechargePoints) throws Exception {

        SuperiorShouldBeRewardedVO SuperiorShouldBeRewardedVO2 = new SuperiorShouldBeRewardedVO();
        List<SuperiorShouldBeRewardedVO> SuperiorShouldBeRewardedVOs = new ArrayList<>();
        // 获取上级对象
        SldzAgent AgentSup = getSup(Random);
        if (ObjectUtil.isNotNull(AgentSup)){
            SuperiorShouldBeRewardedVO SuperiorShouldBeRewardedVO1 = new SuperiorShouldBeRewardedVO();
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
            SuperiorShouldBeRewardedVOs.add(SuperiorShouldBeRewardedVO1);
        }
        /* 获取上上级对象 */
        SldzAgent AgentSupSup = getSupSup(Random);
        if (ObjectUtil.isNotNull(AgentSupSup)){
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
            SuperiorShouldBeRewardedVOs.add(SuperiorShouldBeRewardedVO2);
        }
        return JsonResult.OK().data(SuperiorShouldBeRewardedVOs);
    }



    @ApiOperation(value = "根据唯一编码获取产品价格")
    @GetMapping("/GetProductPriceByRandom")
    public JsonResult GetProductPriceByRandom(Long prductId,String Random) throws Exception {
        LambdaQueryWrapper<SldzAgentProductPrice> SldzAgentProductPricewrapper = new LambdaQueryWrapper<>();
        SldzAgentProductPricewrapper.eq(SldzAgentProductPrice::getProductId, prductId);
        SldzAgentProductPricewrapper.eq(SldzAgentProductPrice::getAgentRandom,Random);
        return JsonResult.OK().data(sldzAgentProductPriceService.getSingleEntity(SldzAgentProductPricewrapper));
    }




    @ApiOperation(value = "为代理商设置推广佣金")
    @PutMapping("/AgentBonusSetting")
    public JsonResult AgentBonusSetting(Long AgentId,
                                        Long productId,
                                        double Bonus) throws Exception {
        //记录成功次数
        int los = 0;
        SldzAgent SldzAgent =  sldzAgentService.getEntity(AgentId);
        LambdaQueryWrapper<SldzBonuSsetting> BonuSsettingwrapper = new LambdaQueryWrapper();
            //查询该代理商有无奖励金
            BonuSsettingwrapper.eq(SldzBonuSsetting::getAgentRandom,SldzAgent.getAgentRandom());
            BonuSsettingwrapper.eq(SldzBonuSsetting::getProductId,productId);
            SldzBonuSsetting SldzBonuSsettings =  sldzBonuSsettingService.getSingleEntity(BonuSsettingwrapper);
            if (ObjectUtil.isNotNull(SldzBonuSsettings)){
                //有记录则更改
                UpdateWrapper<SldzAgent> UpdatewrapperBonuSsetting = new UpdateWrapper();
                UpdatewrapperBonuSsetting.set("bonus",Bonus);
                UpdatewrapperBonuSsetting.eq("product_id",productId);
                UpdatewrapperBonuSsetting.eq("agent_random",SldzAgent.getAgentRandom());
                if(sldzBonuSsettingService. updateEntity(UpdatewrapperBonuSsetting)){
                    los+=1;
                }
            }else {
                //无记录则新增
                if ( sldzBonuSsettingService.createEntity(
                        new SldzBonuSsetting()
                                .setAgentRandom(SldzAgent.getAgentRandom())
                                .setBonus(Bonus)
                                .setProductId(productId))){
                    los+=1;
                }
            }
        Map map = new HashMap();
        map.put("msg",los+"人已更改为："+Bonus);
        return JsonResult.OK().data(map);
    }



    @ApiOperation(value = "根据唯一编码获取产品推广佣金")
    @GetMapping("/GetProductPromotionCommissionByRandom")
    public JsonResult GetProductPromotionCommissionByRandom(Long prductId,String Random) throws Exception {
        LambdaQueryWrapper<SldzBonuSsetting> SldzBonuSsettingPricewrapper = new LambdaQueryWrapper<>();
        SldzBonuSsettingPricewrapper.eq(SldzBonuSsetting::getProductId, prductId);
        SldzBonuSsettingPricewrapper.eq(SldzBonuSsetting::getAgentRandom,Random );
        return JsonResult.OK().data(sldzBonuSsettingService.getSingleEntity(SldzBonuSsettingPricewrapper));
    }

    @ApiOperation(value = "为代理设置产品价格")
    @PutMapping("/AgentProductPriceSetting")
    public JsonResult AgentProductPriceSetting(Long AgentId,
                                               Long productId,
                                               double price) throws Exception {
        //记录成功次数
        int los = 0;

        SldzAgent SldzAgent  =  sldzAgentService.getEntity(AgentId);
        LambdaQueryWrapper<SldzAgentProductPrice> SldzAgentProductPricewrapper = new LambdaQueryWrapper();
            //查询该代理商有无该产的价格
            SldzAgentProductPricewrapper.eq(SldzAgentProductPrice::getAgentRandom,SldzAgent.getAgentRandom());
            SldzAgentProductPricewrapper.eq(SldzAgentProductPrice::getProductId,productId);
            SldzAgentProductPrice SldzAgentProductPrice = sldzAgentProductPriceService.getSingleEntity(SldzAgentProductPricewrapper);
            if (ObjectUtil.isNotNull(SldzAgentProductPrice)){
                //有记录则更改
                UpdateWrapper<SldzAgentProductPrice> UpdatewrapperSldzAgentProductPrice = new UpdateWrapper();
                UpdatewrapperSldzAgentProductPrice.set("product_price",price);
                UpdatewrapperSldzAgentProductPrice.eq("product_id",productId);
                UpdatewrapperSldzAgentProductPrice.eq("agent_random",SldzAgent.getAgentRandom());
                if(sldzAgentProductPriceService. updateEntity(UpdatewrapperSldzAgentProductPrice)){
                    los+=1;
                }
            }else {
                //无记录则新增
                if ( sldzAgentProductPriceService.createEntity(
                        new SldzAgentProductPrice()
                                .setAgentRandom(SldzAgent.getAgentRandom())
                                .setProductPrice(price)
                                .setProductId(productId))){
                    los+=1;
                }
            }
        Map map = new HashMap();
        map.put("msg",los+"人已更改为："+price);
        return JsonResult.OK().data(map);
    }



    /**
     * 得到客户列表
     *
     * @param Random 随机
    //     * @param state  状态
     * @return {@link JsonResult}
     * @throws Exception 异常
     */
    @ApiOperation(value = "获取客户列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Random", value = "唯一编号", required = true, dataType = "String"),
//            @ApiImplicitParam(name = "state", value = "状态 1意向客户,2,待审核客户,3,已通过客户,4,未通过客户", required = true, dataType = "int"),
    })
    @GetMapping("/GetCustomerList")
    public JsonResult GetCustomerList(String Random) throws Exception {
        LambdaQueryWrapper<SldzUserRel> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SldzUserRel::getSupRandom, Random);
        List<SldzUserRel> sldzUserRels = sldzUserRelService.getEntityList(wrapper);
        //所有客户
        List<SldzUser> sldzUsers = new ArrayList<>();

        //1意向客户
        List<SldzUser> IntendedCustomer = new ArrayList<>();
        //2,待审核客户
        List<SldzUser> CustomerReviewed = new ArrayList<>();
        //3,已通过客户
        List<SldzUser> PassedCustomer = new ArrayList<>();
        //4,未通过客户
        List<SldzUser> FailedCustomer = new ArrayList<>();

        for (SldzUserRel rel : sldzUserRels) {
            LambdaQueryWrapper<SldzUser> UserRelwrapper = new LambdaQueryWrapper<>();
            UserRelwrapper.eq(SldzUser::getRandom, rel.getSubRandom());
            sldzUsers.add(SldzUserService.getSingleEntity(UserRelwrapper));
        }

        for (SldzUser user : sldzUsers) {
            LambdaQueryWrapper<SldzCustomerProfile> Customerwrapper = new LambdaQueryWrapper<>();
            Customerwrapper.eq(SldzCustomerProfile::getCustomerRandom, user.getRandom());
            SldzCustomerProfile SldzCustomerProfile = sldzCustomerProfileService.getSingleEntity(Customerwrapper);
            if (ObjectUtil.isNull(SldzCustomerProfile)) {
                //如果 == 空 则为意向客户
                IntendedCustomer.add(user);
            } else if (ObjectUtil.isNotNull(SldzCustomerProfile)) {
                //如果 != 空
                if (SldzCustomerProfile.getState() == 1) {
                    IntendedCustomer.add(user);
                }
                if (SldzCustomerProfile.getState() == 2) {
                    CustomerReviewed.add(user);
                }
                if (SldzCustomerProfile.getState() == 3) {
                    PassedCustomer.add(user);
                }
                if (SldzCustomerProfile.getState() == 4) {
                    FailedCustomer.add(user);
                }
            }
        }

        Map<String, Object> map = new HashMap<>();
        map.put("1",IntendedCustomer);
        map.put("2",CustomerReviewed);
        map.put("3",PassedCustomer);
        map.put("4",FailedCustomer);
        return JsonResult.OK(map);
    }


    @ApiOperation(value = "根据本级Random获取上级对象")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Random", value = "唯一编号", required = true, dataType = "String"),
    })
    @GetMapping("/GetSup")
    public JsonResult GetSup(String Random) throws Exception {
        return JsonResult.OK(getSup(Random));
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
        try{
        SldzAgentRel SldzAgentRel = sldzAgentRelService.sub_find_sup(Random);
        LambdaQueryWrapper<SldzAgent> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SldzAgent::getAgentRandom, SldzAgentRel.getSupRandom());
        return sldzAgentService.getSingleEntity(wrapper);
        }catch (Exception e){
            return null;
        }
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
        try{
            SldzAgentRel SldzAgentRel = sldzAgentRelService.sub_find_supsup(Random);
            LambdaQueryWrapper<SldzAgent> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(SldzAgent::getAgentRandom, SldzAgentRel.getSupRandom());
            return sldzAgentService.getSingleEntity(wrapper);
        }catch (Exception e){
            return null;
        }


    }



    public SldzAgent getAgent(String Random) {
        // 查询本级
        LambdaQueryWrapper<SldzAgent> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SldzAgent::getAgentRandom, Random);
        return sldzAgentService.getSingleEntity(wrapper);
    }

}
