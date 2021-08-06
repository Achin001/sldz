package com.gxc.sldz.controller.API;


import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.diboot.core.vo.JsonResult;
import com.gxc.sldz.Utils.wxUtil;
import com.gxc.sldz.config.jwt.JWT;
import com.gxc.sldz.controller.BaseCustomCrudRestController;
import com.gxc.sldz.entity.*;
import com.gxc.sldz.service.*;
import com.gxc.sldz.vo.SldzAgentDetailVO;
import com.gxc.sldz.vo.SldzUserDetailVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.*;

/**
 * sldz代理api
 *
 * @author Achin
 * @date 2021-07-29 14:56:11
 */
@Api(tags = {"代理商前台接口"})
@RestController
@RequestMapping("api/sldzAgent")
@Slf4j
public class SldzAgentApi extends BaseCustomCrudRestController<SldzAgent> {


    /**
     * sldz代理服务
     */
    @Autowired
    private SldzAgentService sldzAgentService;

    /**
     * sldz用户服务
     */
    @Autowired
    private SldzUserService SldzUserService;

    /**
     * rel sldz代理服务 代理rel
     */
    @Autowired
    private SldzAgentRelService sldzAgentRelService;
    /**
     * sldz用户rel服务 客户rel
     */
    @Autowired
    private SldzUserRelService sldzUserRelService;




    /**
     * 客户消费者档案服务
     */
    @Autowired
    private SldzCustomerProfileService sldzCustomerProfileService;

    //积分记录服务
    @Autowired
    private  SldzAgentIntegralLogService sldzAgentIntegralLogService;


    /**
     * 账户绑定的微信
     *
     * @param entity 实体
     * @return {@link JsonResult}
     * @throws Exception 异常
     */
    @ApiOperation(value = "代理商账号绑定微信")
    @PostMapping("/AccountBindingWechat")
    public JsonResult AccountBindingWechat(@RequestBody SldzUser entity) throws Exception {

        //先获取openid
        JSONObject jsonObject = wxUtil.wxLogin(entity.getOpenid());
//        // 获取参数返回的
        String session_key = jsonObject.get("session_key").toString();
        String open_id = jsonObject.get("openid").toString();
        //查询该openid在用户表有无
        SldzUser getUserByOpenid = SldzUserService.getUserByOpenid(open_id);
        if (ObjectUtil.isNotNull(getUserByOpenid)) {
            return JsonResult.FAIL_OPERATION("绑定失败,该账号已经是消费者");
        }
        //更改openid 及微信资料
        LambdaQueryWrapper<SldzAgent> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SldzAgent::getId, entity.getId());
        SldzAgent sldzAgent = new SldzAgent();
        sldzAgent.setOpenid(open_id);
        sldzAgent.setNickname(entity.getNickname());
        sldzAgent.setAvatarurl(entity.getAvatarurl());
        sldzAgent.setCity(entity.getCity());
        sldzAgent.setCountry(entity.getCountry());
        sldzAgent.setGender(entity.getGender());
        sldzAgent.setProvince(entity.getProvince());
        boolean s = sldzAgentService.updateEntity(sldzAgent, wrapper);
        if (s) {
            return JsonResult.OK().data("绑定成功");
        }
        return JsonResult.FAIL_OPERATION("绑定失败,请稍后再试");
    }


    /**
     * 得到视图对象映射
     * 根据资源id查询ViewObject
     *
     * @param id ID
     * @return {@link JsonResult}
     * @throws Exception 异常
     */
    @ApiOperation(value = "根据ID获取详情数据")
    @GetMapping("/{id}")
    public JsonResult getViewObjectMapping(@PathVariable("id") Long id) throws Exception {
        return super.getViewObject(id, SldzAgentDetailVO.class);
    }


    /**
     * 获得会员资格
     *
     * @param Random 随机
     * @return {@link JsonResult}
     * @throws Exception 异常
     */
    @ApiOperation(value = "代理商获取会员列表")
    @GetMapping("/GetMembership}")
    public JsonResult GetMembership(String Random) throws Exception {
        LambdaQueryWrapper<SldzAgentRel> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SldzAgentRel::getSupRandom, Random);
        //所有一级
        List<SldzAgentRel> SldzAgentRel = sldzAgentRelService.getEntityList(wrapper);
        List<SldzAgent> SldzAgentsList = new ArrayList<>();
        for (SldzAgentRel s : SldzAgentRel) {
            LambdaQueryWrapper<SldzAgent> wrappersubs = new LambdaQueryWrapper<>();
            wrappersubs.eq(SldzAgent::getAgentRandom, s.getSubRandom());
            SldzAgent SldzAgent = sldzAgentService.getSingleEntity(wrappersubs);
            SldzAgent.setAgentPasword("HR1");
            SldzAgentsList.add(SldzAgent);
        }

        //所有二级
        List<SldzAgentRel> SldzAgentRels = sldzAgentRelService.SldzAgentRels(Random);
        for (SldzAgentRel s : SldzAgentRels) {
            LambdaQueryWrapper<SldzAgent> wrappersubs = new LambdaQueryWrapper<>();
            wrappersubs.eq(SldzAgent::getAgentRandom, s.getSubRandom());
            SldzAgent SldzAgent = sldzAgentService.getSingleEntity(wrappersubs);
            SldzAgent.setAgentPasword("HR2");
            SldzAgentsList.add(SldzAgent);
        }

        return JsonResult.OK().data(SldzAgentsList);
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



    /**
     * 得到客户列表
     *
     * @param Random 随机
     * @param userRandom  客户/用户唯一编号
     * @return {@link JsonResult}
     * @throws Exception 异常
     */
    @ApiOperation(value = "获取客户档案")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Random", value = "唯一编号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "userRandom", value = "客户/用户唯一编号", required = true, dataType = "String"),
    })
    @GetMapping("/GetCustomerProfile}")
    public JsonResult GetCustomerProfile(String Random, String userRandom) throws Exception {
        LambdaQueryWrapper<SldzCustomerProfile> Customerwrapper = new LambdaQueryWrapper<>();
        //用户唯一编号
        Customerwrapper.eq(SldzCustomerProfile::getCustomerRandom,userRandom);
        //唯一编号
        Customerwrapper.eq(SldzCustomerProfile::getAgentRandom,Random);
        SldzCustomerProfile SldzCustomerProfile = sldzCustomerProfileService.getSingleEntity(Customerwrapper);
        return JsonResult.OK(SldzCustomerProfile);
    }




    @ApiOperation(value = "补充客户档案")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Random", value = "唯一编号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "userRandom", value = "客户/用户唯一编号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "customerSkinDataJson", value = "客户皮肤资料json", required = true, dataType = "String"),
    })
    @PostMapping("/SupplementCustomerFiles")
    public JsonResult SupplementCustomerFiles(String Random, String userRandom,String customerSkinDataJson) throws Exception {
        double integral= 4800.00;
        //检查积分时候充足
        boolean CheckPointsSufficient = false;
        LambdaQueryWrapper<SldzAgent> SldzAgentwrapper = new LambdaQueryWrapper<>();
        SldzAgentwrapper.eq(SldzAgent::getAgentRandom,Random);
        SldzAgent SldzAgent =  sldzAgentService.getSingleEntity(SldzAgentwrapper);
        if (ObjectUtil.isNotNull(SldzAgent)){
            //积分是否大于 4800
            if (SldzAgent.getAgentIntegral() >= integral){
                CheckPointsSufficient =true;
            }else {

                return JsonResult.FAIL_OPERATION("积分余额不足"+SldzAgent.getAgentIntegral());
            }
        }else {
            return JsonResult.FAIL_OPERATION("你不是代理商");
        }
        //客户皮肤资料json 写入数据库
        if(CheckPointsSufficient){
            UpdateWrapper<SldzCustomerProfile> SldzCustomerProfileupdateWrapper = new UpdateWrapper<SldzCustomerProfile>();
            SldzCustomerProfileupdateWrapper.set("customer_skin_data_json", customerSkinDataJson);
            //改客户档案状态
            SldzCustomerProfileupdateWrapper.set("state", 2);
            SldzCustomerProfileupdateWrapper.eq("agent_random", Random);
            SldzCustomerProfileupdateWrapper.eq("customer_random", userRandom);
            sldzCustomerProfileService.updateEntity(SldzCustomerProfileupdateWrapper);

            //扣除积分
            sldzAgentService.ChangePoints(SldzAgent.getAgentIntegral()-integral,SldzAgent.getAgentRandom());
            //记录积分支出详细
            sldzAgentIntegralLogService.createEntity(new SldzAgentIntegralLog()
                    .setAgentRandom(SldzAgent.getAgentRandom())
                    .setIntegralDate(DateUtil.now())
                    .setIntegralEvent("解决客户脸部问题，消费:"+integral+"积分")
                    .setIntegralMoney(integral)
                    .setIntegralType(2l));

            return JsonResult.OK().data("补充客户档案成功");
        }

        return JsonResult.FAIL_OPERATION("补充客户档案失败");
    }





}


