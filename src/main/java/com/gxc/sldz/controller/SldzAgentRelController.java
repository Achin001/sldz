package com.gxc.sldz.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gxc.sldz.dto.SldzAgentDTO;
import com.gxc.sldz.entity.SldzAdmin;
import com.gxc.sldz.entity.SldzAgent;
import com.gxc.sldz.service.RandomServer;
import com.gxc.sldz.service.SendSmsService;
import com.gxc.sldz.service.SldzAgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.diboot.core.vo.*;
import com.gxc.sldz.entity.SldzAgentRel;
import com.gxc.sldz.dto.SldzAgentRelDTO;
import com.gxc.sldz.vo.SldzAgentRelListVO;
import com.gxc.sldz.vo.SldzAgentRelDetailVO;
import com.gxc.sldz.service.SldzAgentRelService;
import lombok.extern.slf4j.Slf4j;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * 代理商关系表 相关Controller
 * @author Achin
 * @version 1.0
 * @date 2021-06-02
 * Copyright © MyCompany
 */
@Api(tags = { "代理商关系管理后台接口" })
@RestController
@RequestMapping("admin/sldzAgentRel")
@Slf4j
public class SldzAgentRelController extends BaseCustomCrudRestController<SldzAgentRel> {

    @Autowired
    private SldzAgentRelService sldzAgentRelService;

    @Autowired
    private SldzAgentService sldzAgentService;

    //阿里云短信服务
    @Autowired
    private SendSmsService SendSmsService;


    /**
     * 创建资源对象
     * @param entity
     * @return JsonResult
     * @throws Exception
     */
    @ApiOperation(value = "新建数据")
    @PostMapping("/")
    public JsonResult createEntityMapping(@Valid @RequestBody SldzAgentRel entity) throws Exception {
        if(entity.getSubRandom() == entity.getSupRandom()){
            return JsonResult.FAIL_OPERATION("该代理商有上级");
        }
        // 查询该下级是否有上级
        LambdaQueryWrapper<SldzAgentRel> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SldzAgentRel::getSubRandom, entity.getSubRandom());
        SldzAgentRel SldzAgentRelp = sldzAgentRelService.getSingleEntity(wrapper);
        if (ObjectUtil.isNotNull(SldzAgentRelp)) {
            return JsonResult.FAIL_OPERATION("该代理商有上级");
        }
        //获取上级代理商的agennt
        SldzAgent SupSldzAgent = sldzAgentService.getAgentByagentRandom(entity.getSupRandom());
        //获取添加的代理商的agennt
        SldzAgent SubSldzAgent =  sldzAgentService.getAgentByagentRandom(entity.getSubRandom());

        //上级代理商的手机
        String phone = SupSldzAgent.getAgentPhone();
        //上级代理商的名称
        String name= SupSldzAgent.getAgentName();
        //添加的代理商名称
        String agentname= SubSldzAgent.getAgentName();
        //关系
        String num= "一";
        //发送短信 一级
//        SendSmsService.EnteredSuccess(phone,name,agentname,num);


        // 查询该上级是否有上级
        LambdaQueryWrapper<SldzAgentRel> supwrapper = new LambdaQueryWrapper<>();
        supwrapper.eq(SldzAgentRel::getSubRandom, SupSldzAgent.getAgentRandom());
        SldzAgentRel SldzAgentRela = sldzAgentRelService.getSingleEntity(wrapper);
        if (ObjectUtil.isNotNull(SldzAgentRela)) {
            //获取上上级代理商的agennt
            SldzAgent SupSupSldzAgent = sldzAgentService.getAgentByagentRandom(SldzAgentRela.getSupRandom());
            //上级代理商的手机
            String phone1 = SupSupSldzAgent.getAgentPhone();
            //上级代理商的名称
            String name1= SupSupSldzAgent.getAgentName();
            //添加的代理商名称
            String agentname1= SubSldzAgent.getAgentName();
            //关系
            String num1= "二";
            //发送短信 二级
//            SendSmsService.EnteredSuccess(phone1,name1,agentname1,num1);
        }

        return super.createEntity(entity);
    }

    /**
     * 根据id删除资源对象
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "根据ID删除数据")
    @PostMapping("/delet")
    public JsonResult deleteEntityMapping(@Valid @RequestBody SldzAgentRel entity) throws Exception {
        LambdaQueryWrapper<SldzAgentRel> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SldzAgentRel::getSubRandom, entity.getSubRandom());
        wrapper.eq(SldzAgentRel::getSupRandom, entity.getSupRandom());
         if(sldzAgentRelService.deleteEntities(wrapper)){
             return JsonResult.OK().data("移除成功");
         }
        return JsonResult.FAIL_OPERATION("移除失败");
    }


    @ApiOperation(value = "根据唯一编码获取一级代理商列表")
    @GetMapping("/relAgentListClassA")
    public JsonResult relAgentListClassA(SldzAgentRel queryDto) throws Exception {
        LambdaQueryWrapper<SldzAgentRel> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SldzAgentRel::getSupRandom, queryDto.getSupRandom());
        List<SldzAgentRel>  SldzAgentRel1 = sldzAgentRelService.getEntityList(wrapper);
        List<SldzAgent> SldzAgentsList = new ArrayList<>();
        for(SldzAgentRel s :SldzAgentRel1){
            LambdaQueryWrapper<SldzAgent> wrappersubs = new LambdaQueryWrapper<>();
            wrappersubs.eq(SldzAgent::getAgentRandom, s.getSubRandom());
            SldzAgent  SldzAgent  = sldzAgentService.getSingleEntity(wrappersubs);
            SldzAgentsList.add(SldzAgent);
        }

        for(SldzAgent s :SldzAgentsList){
            LambdaQueryWrapper<SldzAgentRel> swrapper = new LambdaQueryWrapper<>();
            swrapper.eq(SldzAgentRel::getSupRandom, s.getAgentRandom());
            //查出下级个数 写入密码字段
            s.setAgentPasword(sldzAgentRelService.getEntityListCount(swrapper)+"");
        }
        return JsonResult.OK().data(SldzAgentsList);
    }


    @ApiOperation(value = "根据唯一编码获取二级代理商列表")
    @GetMapping("/relAgentListClassB")
    public JsonResult relAgentListClassB(SldzAgentRel queryDto) throws Exception {
        //所有二级
        List<SldzAgentRel>  SldzAgentRel = sldzAgentRelService.SldzAgentRels(queryDto.getSupRandom());
        List<SldzAgent> SldzAgentsList = new ArrayList<>();
        for(SldzAgentRel s :SldzAgentRel){
            LambdaQueryWrapper<SldzAgent> wrappersubs = new LambdaQueryWrapper<>();
            wrappersubs.eq(SldzAgent::getAgentRandom, s.getSubRandom());
            SldzAgent  SldzAgent  = sldzAgentService.getSingleEntity(wrappersubs);
            SldzAgentsList.add(SldzAgent);
        }
        for(SldzAgent s :SldzAgentsList){
            s.setAgentPasword("");
        }
        return JsonResult.OK().data(SldzAgentsList);
    }



}
