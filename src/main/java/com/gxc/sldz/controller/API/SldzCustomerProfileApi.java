package com.gxc.sldz.controller.API;


import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.diboot.core.util.S;
import com.diboot.core.vo.JsonResult;
import com.gxc.sldz.controller.BaseCustomCrudRestController;
import com.gxc.sldz.entity.SldzAgent;
import com.gxc.sldz.entity.SldzCustomerProfile;
import com.gxc.sldz.entity.SldzUserRel;
import com.gxc.sldz.service.SldzAgentService;
import com.gxc.sldz.service.SldzCustomerProfileService;
import com.gxc.sldz.service.SldzUserRelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = {"客户档案前台接口"})
@RestController
@RequestMapping("api/sldzCustomerProfile")
@Slf4j
public class SldzCustomerProfileApi  extends BaseCustomCrudRestController<SldzCustomerProfile> {


    @Autowired
    private SldzCustomerProfileService sldzCustomerProfileService;

    @Autowired
    private SldzUserRelService sldzUserRelService;

    /**
     * sldz代理服务
     */
    @Autowired
    private SldzAgentService sldzAgentService;



    /***
     * 创建资源对象
     * @param entity
     * @return JsonResult
     * @throws Exception
     */
    @ApiOperation(value = "新建档案")
    @PostMapping("/createFile")
    public JsonResult createEntityMapping(@Valid @RequestBody SldzCustomerProfile entity) throws Exception {

        LambdaQueryWrapper<SldzUserRel> Customerwrapper = new LambdaQueryWrapper<>();
        Customerwrapper.eq(SldzUserRel::getSubRandom,entity.getCustomerRandom());
        SldzUserRel  SldzUserRel =  sldzUserRelService.getSingleEntity(Customerwrapper);
        if (ObjectUtil.isNotNull(SldzUserRel)){
            LambdaQueryWrapper<SldzAgent> SldzAgentwrapper = new LambdaQueryWrapper<>();
            //上级编码
            SldzAgentwrapper.eq(SldzAgent::getAgentRandom,SldzUserRel.getSupRandom());
            //判断上级是不是代理商
            SldzAgent  SldzAgent =  sldzAgentService.getSingleEntity(SldzAgentwrapper);
            if (ObjectUtil.isNotNull(SldzAgent)){
                entity.setAgentRandom(SldzAgent.getAgentRandom());
                entity.setState(1);
                return super.createEntity(entity);
            }else {
                return   JsonResult.FAIL_OPERATION("您的上级不是代理商，档案添加失败");
            }
        }
        return   JsonResult.FAIL_OPERATION("您的上级不是代理商，档案添加失败");
    }



    @ApiOperation(value = "查看档案")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Random", value = "客户/用户唯一编号", required = true, dataType = "String"),
    })
    @GetMapping("/ViewFile")
    public JsonResult ViewFile(String Random) throws Exception {

        LambdaQueryWrapper<SldzCustomerProfile> Customerwrapper = new LambdaQueryWrapper<>();
        //用户唯一编号
        Customerwrapper.eq(SldzCustomerProfile::getCustomerRandom,Random);
        SldzCustomerProfile SldzCustomerProfile =  sldzCustomerProfileService.getSingleEntity(Customerwrapper);
        return JsonResult.OK().data(SldzCustomerProfile);
    }



}
