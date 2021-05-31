package com.gxc.sldz.controller.API;


import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.diboot.core.vo.JsonResult;
import com.gxc.sldz.WeChat.Util.WechatUtil;
import com.gxc.sldz.config.jwt.JWT;
import com.gxc.sldz.entity.SldzAgent;
import com.gxc.sldz.entity.SldzUser;
import com.gxc.sldz.service.SldzAgentService;
import com.gxc.sldz.service.SldzUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.OBJ_ADAPTER;
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


    @Autowired
    private SldzUserService SldzUserService;

    @Autowired
    private SldzAgentService sldzAgentService;

    @Autowired
    JWT JWT;

    @ApiOperation(value = "代理商账号密码登录")
    @PostMapping("/AccountLogin")
    public JsonResult AccountLogin(@Valid @RequestBody SldzAgent entity) throws Exception {
        LambdaQueryWrapper<SldzAgent> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SldzAgent::getAgentPhone, entity.getAgentPhone());
        wrapper.eq(SldzAgent::getAgentPasword, entity.getAgentPasword());
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
            return JsonResult.OK().data("账号密码错误");

        }
    }


//@RequestParam(value = "code", required = true) String code,
//    @RequestParam(value = "SldzUsers", required = true) SldzUser SldzUsers
    @ApiOperation(value = "微信登录")
    @PostMapping("/WeChatLogin")
    public JsonResult WeChatLogin(@RequestBody JSONObject jsonObject) throws Exception {
        // 1.接收小程序发送的code
        // 2.开发者服务器 登录凭证校验接口 appi + appsecret + code
        JSONObject SessionKeyOpenId = WechatUtil.getSessionKeyOrOpenId(jsonObject.getString("code"));
        // 3.接收微信接口服务 获取返回的参数
        String openid = SessionKeyOpenId.getString("openid");
        String sessionKey = SessionKeyOpenId.getString("session_key");
        // 4.根据返回的User实体类，判断用户是否是新用户，是的话，将用户信息存到数据库；不是的话，更新最新登录时间
//        LambdaQueryWrapper<SldzUser> wrapper = new LambdaQueryWrapper<>();
//        wrapper.eq(SldzUser::getOpenid, openid);
//        SldzUser sldzUser =  SldzUserService.getSingleEntity(wrapper);
//        boolean success = SldzUserService.createOrUpdateEntity(sldzUser);
//        Map map = new HashMap();
//        //返回token
//        map.put("token",JWT.generateToken(sldzUser.getId()));
//        //返回agent
//        map.put("sldzUser",sldzUser);
//        //返回识别号
//        map.put("Identification",2);
//        map.put("IdentificationMsg","1：代理商。2：消费者");
//        map.put("success",success);
        return JsonResult.OK();
    }
}
