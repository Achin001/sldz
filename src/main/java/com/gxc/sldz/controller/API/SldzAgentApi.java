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
import com.gxc.sldz.entity.SldzAgentRel;
import com.gxc.sldz.entity.SldzUser;
import com.gxc.sldz.service.SldzAgentRelService;
import com.gxc.sldz.service.SldzAgentService;
import com.gxc.sldz.service.SldzUserService;
import com.gxc.sldz.vo.SldzAgentDetailVO;
import com.gxc.sldz.vo.SldzUserDetailVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = {"代理商前台接口"})
@RestController
@RequestMapping("api/sldzAgent")
@Slf4j
public class SldzAgentApi extends BaseCustomCrudRestController<SldzAgent> {


    @Autowired
    private SldzAgentService sldzAgentService;

    @Autowired
    private SldzUserService SldzUserService;

    @Autowired
    private SldzAgentRelService sldzAgentRelService;


    @ApiOperation(value = "代理商账号绑定微信")
    @PostMapping("/AccountBindingWechat")
    public JsonResult AccountBindingWechat(@RequestBody  SldzUser entity) throws Exception {

        //先获取openid
        JSONObject jsonObject = wxUtil.wxLogin(entity.getOpenid());
//        // 获取参数返回的
        String session_key = jsonObject.get("session_key").toString();
        String open_id = jsonObject.get("openid").toString();
        entity.setOpenid(open_id);
        //查询该openid在用户表有无
        SldzUser getUserByOpenid =  SldzUserService.getUserByOpenid(entity.getOpenid());
        if (ObjectUtil.isNotNull(getUserByOpenid)){
            return JsonResult.FAIL_OPERATION("绑定失败,该账号已经是消费者");
        }
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




    /**
     * 根据资源id查询ViewObject
     * @param id ID
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "根据ID获取详情数据")
    @GetMapping("/{id}")
    public JsonResult getViewObjectMapping(@PathVariable("id") Long id) throws Exception {
        return super.getViewObject(id, SldzAgentDetailVO.class);
    }





    @ApiOperation(value = "获取会员列表")
    @GetMapping("/GetMembership}")
    public JsonResult  GetMembership(String Random) throws Exception {
        LambdaQueryWrapper<SldzAgentRel> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SldzAgentRel::getSupRandom, Random);
        //所有一级
        List<SldzAgentRel>  SldzAgentRel = sldzAgentRelService.getEntityList(wrapper);
        List<SldzAgent> SldzAgentsList = new ArrayList<>();
        for(SldzAgentRel s :SldzAgentRel){
            LambdaQueryWrapper<SldzAgent> wrappersubs = new LambdaQueryWrapper<>();
            wrappersubs.eq(SldzAgent::getAgentRandom, s.getSubRandom());
            SldzAgent  SldzAgent  = sldzAgentService.getSingleEntity(wrappersubs);
            SldzAgent.setAgentPasword("HR1");
            SldzAgentsList.add(SldzAgent);
        }

        //所有二级
        List<SldzAgentRel>  SldzAgentRels = sldzAgentRelService.SldzAgentRels(Random);
        for(SldzAgentRel s :SldzAgentRels){
            LambdaQueryWrapper<SldzAgent> wrappersubs = new LambdaQueryWrapper<>();
            wrappersubs.eq(SldzAgent::getAgentRandom, s.getSubRandom());
            SldzAgent  SldzAgent  = sldzAgentService.getSingleEntity(wrappersubs);
            SldzAgent.setAgentPasword("HR2");
            SldzAgentsList.add(SldzAgent);
        }

        return JsonResult.OK().data(SldzAgentsList);
    }





}


