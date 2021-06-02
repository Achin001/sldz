package com.gxc.sldz.controller.API;


import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.diboot.core.vo.JsonResult;
import com.gxc.sldz.Utils.wxUtil;
import com.gxc.sldz.config.jwt.JWT;
import com.gxc.sldz.controller.BaseCustomCrudRestController;
import com.gxc.sldz.entity.SldzAgent;
import com.gxc.sldz.entity.SldzUser;
import com.gxc.sldz.service.SldzAgentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Api(tags = {"代理商前台接口"})
@RestController
@RequestMapping("api/sldzAgent")
@Slf4j
public class SldzAgentApi extends BaseCustomCrudRestController<SldzAgent> {


    @Autowired
    private SldzAgentService sldzAgentService;


    @ApiOperation(value = "代理商账号绑定微信")
    @PostMapping("/AccountBindingWechat")
    public JsonResult AccountBindingWechat(@RequestBody  SldzUser entity) throws Exception {
        //先获取openid
        JSONObject jsonObject = wxUtil.wxLogin(entity.getOpenid());
//        // 获取参数返回的
        String session_key = jsonObject.get("session_key").toString();
        String open_id = jsonObject.get("openid").toString();
        //更改openid 及微信资料
        LambdaQueryWrapper<SldzAgent> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SldzAgent::getId, entity.getId());
        SldzAgent sldzAgent = new SldzAgent();
        sldzAgent.setOpenid(entity.getOpenid());
        sldzAgent.setNickname(entity.getNickname());
        sldzAgent.setAvatarurl(entity.getAvatarurl());
        sldzAgent.setCity(entity.getCity());
        sldzAgent.setCountry(entity.getCountry());
        sldzAgent.setGender(entity.getGender());
        sldzAgent.setProvince(entity.getProvince());
        boolean s = sldzAgentService.updateEntity(sldzAgent,wrapper);
        if (s){
            return JsonResult.OK().data("绑定成功");
        }
        return JsonResult.FAIL_OPERATION("绑定失败,请稍后再试");
        }
    }


