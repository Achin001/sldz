package com.gxc.sldz.controller.API;


import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.diboot.core.util.S;
import com.diboot.core.vo.JsonResult;
import com.gxc.sldz.Utils.RedisUtils;
import com.gxc.sldz.Utils.wxUtil;
import com.gxc.sldz.Utils.wxconfig;
import com.gxc.sldz.config.jwt.JWT;
import com.gxc.sldz.entity.SldzAdmin;
import com.gxc.sldz.entity.SldzAgent;
import com.gxc.sldz.entity.SldzCustomerProfile;
import com.gxc.sldz.entity.SldzUser;
import com.gxc.sldz.service.RandomServer;
import com.gxc.sldz.service.SendSmsService;
import com.gxc.sldz.service.SldzAgentService;
import com.gxc.sldz.service.SldzUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Api(tags = {"前台登录接口"})
@RestController
@RequestMapping("api")
@Slf4j
public class SldzLoginApi {

    private static String  VerificationCode = "_VerificationCode";


    private static String  ForgetPassword = "_ForgetPassword";


    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private SldzUserService SldzUserService;

    @Autowired
    private SldzAgentService sldzAgentService;
    @Autowired
    RandomServer RandomServer;

    @Autowired
    JWT JWT;

    @Autowired
    SendSmsService SendSmsService;


    @ApiOperation(value = "代理商账号密码登录")
    @PostMapping("/AccountLogin")
    public JsonResult AccountLogin(@Valid @RequestBody SldzAgent entity) throws Exception {
        LambdaQueryWrapper<SldzAgent> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SldzAgent::getAgentPhone, entity.getAgentPhone());
        wrapper.eq(SldzAgent::getAgentPasword, SecureUtil.md5(entity.getAgentPasword()));
        SldzAgent SldzAgent =  sldzAgentService.getSingleEntity(wrapper);
        if (ObjectUtil.isNotNull(SldzAgent)){
            //如果不等于空
            Map map = new HashMap();
            //返回token
            map.put("token",JWT.generateToken(SldzAgent.getId()));
            //返回agent
            map.put("SldzAgent",SldzAgent);
            //返回识别号
            map.put("Identification",1);
            map.put("IdentificationMsg","1：代理商。2：消费者");
            return JsonResult.OK().data(map);

        }else {
            return JsonResult.FAIL_EXCEPTION("账号密码错误");

        }
    }





    @ApiOperation(value = "微信登录")
    @PostMapping("/WeChatLogin")
    public JsonResult WeChatLogin(@RequestBody SldzUser entity) throws Exception {
        JSONObject jsonObject = wxUtil.wxLogin(entity.getOpenid());
//        // 获取参数返回的
        String session_key = jsonObject.get("session_key").toString();
        String open_id = jsonObject.get("openid").toString();
        //替换openid
        entity.setOpenid(open_id);
        //查找代理商表有没有该openid
        SldzAgent SldzAgent =  sldzAgentService.getAgentByOpenid(entity.getOpenid());
        if (ObjectUtil.isNotNull(SldzAgent)){
            //如果不等于空
            Map map = new HashMap();
            //返回token
            map.put("token",JWT.generateToken(SldzAgent.getId()));
            //返回agent
            map.put("SldzAgent",SldzAgent);
            //返回识别号
            map.put("Identification",1);
            map.put("IdentificationMsg","1：代理商。2：消费者");
            return JsonResult.OK().data(map);
        }

        LambdaQueryWrapper<SldzUser> wrapperUser = new LambdaQueryWrapper<>();
        wrapperUser.eq(SldzUser::getOpenid, entity.getOpenid());
         //根据wrapper 查找对应数据
        SldzUser sldzUser =  SldzUserService.getSingleEntity(wrapperUser);
        Map map = new HashMap();
        if (ObjectUtil.isNotNull(sldzUser)){
            LambdaQueryWrapper<SldzUser> wrappere = new LambdaQueryWrapper<>();
            wrappere.eq(SldzUser::getOpenid, entity.getOpenid());
            //更新个人资料
            boolean success = SldzUserService.updateEntity(entity,wrappere);
            //返回token
            map.put("token",JWT.generateToken(sldzUser.getId()));
            //返回user
            map.put("sldzUser",sldzUser);
            //返回识别号
            map.put("Identification",2);
            map.put("IdentificationMsg","1：代理商。2：消费者");
            map.put("success",success);
            map.put("successmsg","更新");
            return JsonResult.OK().data(map);
        }else {
            entity.setRandom(RandomServer.getRandom());
            //新建个人资料
            boolean success = SldzUserService.createEntity(entity);
            //返回token
            map.put("token",JWT.generateToken(entity.getId()));
            //返回user
            map.put("sldzUser",entity);
            //返回识别号
            map.put("Identification",2);
            map.put("IdentificationMsg","1：代理商。2：消费者");
            map.put("success",success);
            map.put("successmsg","新建");
            return JsonResult.OK().data(map);
        }
    }






    @ApiOperation(value = "代理商短信验证码登录-发送验证码")
    @ApiImplicitParams({
//            @ApiImplicitParam(name = "phone", value = "账号/手机号", required = true, dataType = "String"),
    })
    @PostMapping("/VerificationCodeLoginSendCode")
    public JsonResult VerificationCodeLoginSendCode(@RequestBody String phone) throws Exception {
        String randomNumbers =  RandomUtil.randomNumbers(6);
        //5分钟 300秒
        redisUtils.set(phone+VerificationCode,randomNumbers,300);
//        正式环境打开
        return JsonResult.OK().data(SendSmsService.LoginConfirmationVerificationCode(phone,randomNumbers));
        //测试打开
//        return JsonResult.OK().data(true);
    }


    @ApiOperation(value = "代理商短信验证码登录-登录")
    @ApiImplicitParams({
//            @ApiImplicitParam(name = "agentPhone", value = "账号/手机号", required = true, dataType = "String"),
//            @ApiImplicitParam(name = "agentRandom", value = "验证码写在agentRandom", required = true, dataType = "String"),
    })
    @PostMapping("/VerificationCodeLogin")
    public JsonResult VerificationCodeLogin(@Valid @RequestBody SldzAgent entity) throws Exception {
        //拿到手机号去redis查询有无验证码
        String redisVerificationCode =  redisUtils.get(entity.getAgentPhone()+VerificationCode);

        if (StrUtil.isNotBlank(redisVerificationCode)){

            if (entity.getAgentRandom() .equals(redisVerificationCode)
                    || redisVerificationCode == entity.getAgentRandom()){
                LambdaQueryWrapper<SldzAgent> wrapper = new LambdaQueryWrapper<>();
                wrapper.eq(SldzAgent::getAgentPhone, entity.getAgentPhone());
                SldzAgent SldzAgent =  sldzAgentService.getSingleEntity(wrapper);
                if (ObjectUtil.isNotNull(SldzAgent)){
                    //如果不等于空
                    Map map = new HashMap();
                    //返回token
                    map.put("token",JWT.generateToken(SldzAgent.getId()));
                    //返回agent
                    map.put("SldzAgent",SldzAgent);
                    //返回识别号
                    map.put("Identification",1);
                    map.put("IdentificationMsg","1：代理商。2：消费者");
                    return JsonResult.OK().data(map);

                }else {
                    return JsonResult.FAIL_EXCEPTION("该账号未注册");

                }
            }else {
                return JsonResult.FAIL_EXCEPTION("验证码错误");
            }
        }else {
            return JsonResult.FAIL_EXCEPTION("未发送验证码");
        }
    }






    @ApiOperation(value = "代理商忘记密码-发送验证码")
    @ApiImplicitParams({
//            @ApiImplicitParam(name = "phone", value = "账号/手机号", required = true, dataType = "String"),
    })
    @PostMapping("/AgentForgotPasswordSendCode")
    public JsonResult AgentForgotPasswordSendCode(@RequestBody String phone) throws Exception {
        String randomNumbers =  RandomUtil.randomNumbers(6);
        //5分钟 300秒
        redisUtils.set(phone+ForgetPassword,randomNumbers,300);
//        正式环境打开
        return JsonResult.OK().data(SendSmsService.ModifyPasswordVerificationCode(phone,randomNumbers));
        //测试打开
//        return JsonResult.OK().data(true);
    }


    @ApiOperation(value = "代理商短信验证码登录-确定修改")
    @ApiImplicitParams({
//            @ApiImplicitParam(name = "agentPhone", value = "账号/手机号", required = true, dataType = "String"),
//            @ApiImplicitParam(name = "agentPasword", value = "新密码", required = true, dataType = "String"),
//            @ApiImplicitParam(name = "agentRandom", value = "验证码写在agentRandom", required = true, dataType = "String"),
    })
    @PostMapping("/AgentForgotPasswordSendCodeDetermine")
    public JsonResult AgentForgotPasswordSendCodeDetermine(@Valid @RequestBody SldzAgent entity) throws Exception {
        //拿到手机号去redis查询有无验证码
        String redisVerificationCode =  redisUtils.get(entity.getAgentPhone()+ForgetPassword);

        if (StrUtil.isNotBlank(redisVerificationCode)){

            if (entity.getAgentRandom() .equals(redisVerificationCode)
                    || redisVerificationCode == entity.getAgentRandom()){

                UpdateWrapper<SldzAgent> SldzAgentupdateWrapper = new UpdateWrapper<>();
                SldzAgentupdateWrapper.set("agent_pasword", SecureUtil.md5(entity.getAgentPasword()));
                SldzAgentupdateWrapper.eq("agent_phone", entity.getAgentPhone());
                return JsonResult.OK().data(sldzAgentService.updateEntity(SldzAgentupdateWrapper));

                }else {
                    return JsonResult.FAIL_EXCEPTION("验证码错误");

                }
            }else {
                return JsonResult.FAIL_EXCEPTION("未发送验证码");
            }
        }

}
