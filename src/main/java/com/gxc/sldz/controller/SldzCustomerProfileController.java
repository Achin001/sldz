package com.gxc.sldz.controller;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.gxc.sldz.entity.SldzAgent;
import com.gxc.sldz.entity.SldzAgentIntegralLog;
import com.gxc.sldz.entity.SldzCustomerTreatmentFile;
import com.gxc.sldz.service.SldzAgentIntegralLogService;
import com.gxc.sldz.service.SldzAgentService;
import com.gxc.sldz.service.SldzCustomerTreatmentFileService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.diboot.core.vo.*;
import com.gxc.sldz.entity.SldzCustomerProfile;
import com.gxc.sldz.dto.SldzCustomerProfileDTO;
import com.gxc.sldz.vo.SldzCustomerProfileListVO;
import com.gxc.sldz.vo.SldzCustomerProfileDetailVO;
import com.gxc.sldz.service.SldzCustomerProfileService;

import lombok.extern.slf4j.Slf4j;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* 客户档案 相关Controller
* @author Achin
* @version 1.0
* @date 2021-07-29
* Copyright © MyCompany
*/
@Api(tags = {"客户档案后台接口"})
@RestController
@RequestMapping("admin/sldzCustomerProfile")
@Slf4j
public class SldzCustomerProfileController extends BaseCustomCrudRestController<SldzCustomerProfile> {
    @Autowired
    private SldzCustomerProfileService sldzCustomerProfileService;


    /**
     * sldz代理服务
     */
    @Autowired
    private SldzAgentService sldzAgentService;


    //积分记录服务
    @Autowired
    private SldzAgentIntegralLogService sldzAgentIntegralLogService;


    //疗程档案服务
    @Autowired
    private SldzCustomerTreatmentFileService sldzCustomerTreatmentFileService;



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

        LambdaQueryWrapper<SldzCustomerTreatmentFile> SldzCustomerTreatmentFilewrapper = new LambdaQueryWrapper<>();
        SldzCustomerTreatmentFilewrapper.eq(SldzCustomerTreatmentFile::getCustomerProFile,SldzCustomerProfile.getId());
        //获取疗程档案列表
        List<SldzCustomerTreatmentFile>  SldzCustomerTreatmentFiles = sldzCustomerTreatmentFileService.getEntityList(SldzCustomerTreatmentFilewrapper);
        Map<String, Object> map = new HashMap<>();
        map.put("SldzCustomerProfile",SldzCustomerProfile);
        map.put("SldzCustomerTreatmentFiles",SldzCustomerTreatmentFiles);
        return JsonResult.OK(map);
    }



    @ApiOperation(value = "待审核-处理客户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Random", value = "唯一编号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "userRandom", value = "客户/用户唯一编号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "type", value = "处理类型（1通过，2驳回）", required = true, dataType = "String"),
    })
    @PostMapping("/handle")
    public JsonResult handle(String Random, String userRandom,int type) throws Exception {
        LambdaQueryWrapper<SldzCustomerProfile> Customerwrapper = new LambdaQueryWrapper<>();
        //用户唯一编号
        Customerwrapper.eq(SldzCustomerProfile::getCustomerRandom,userRandom);
        //唯一编号
        Customerwrapper.eq(SldzCustomerProfile::getAgentRandom,Random);
        SldzCustomerProfile SldzCustomerProfile = sldzCustomerProfileService.getSingleEntity(Customerwrapper);
        UpdateWrapper<SldzCustomerProfile> SldzCustomerProfileupdateWrapper = new UpdateWrapper<SldzCustomerProfile>();
        if(type  == 1 ){
            //1通过
            //改状态

            //改客户档案状态
            SldzCustomerProfileupdateWrapper.set("state", 3);
            SldzCustomerProfileupdateWrapper.eq("agent_random", Random);
            SldzCustomerProfileupdateWrapper.eq("customer_random", userRandom);
            sldzCustomerProfileService.updateEntity(SldzCustomerProfileupdateWrapper);
            return JsonResult.OK();
        }else if (type  == 2 ){
            //2驳回
            //改状态
            //改客户档案状态
            SldzCustomerProfileupdateWrapper.set("state", 4);
            SldzCustomerProfileupdateWrapper.eq("agent_random", Random);
            SldzCustomerProfileupdateWrapper.eq("customer_random", userRandom);
            sldzCustomerProfileService.updateEntity(SldzCustomerProfileupdateWrapper);


            LambdaQueryWrapper<SldzAgent> SldzAgentwrapper = new LambdaQueryWrapper<>();
            SldzAgentwrapper.eq(SldzAgent::getAgentRandom,Random);
            SldzAgent SldzAgent =  sldzAgentService.getSingleEntity(SldzAgentwrapper);
            //退回4800积分
            sldzAgentService.ChangePoints(SldzAgent.getAgentIntegral()+ SldzCustomerProfile.getPayPoints(),
                    SldzAgent.getAgentRandom());
            //记录积分支出详细
            sldzAgentIntegralLogService.createEntity(new SldzAgentIntegralLog()
                    .setAgentRandom(SldzAgent.getAgentRandom())
                    .setIntegralDate(DateUtil.now())
                    .setIntegralEvent("解决客户脸部问题，公司未通过,退回:"+SldzCustomerProfile.getPayPoints()+"积分.")
                    .setIntegralMoney(SldzCustomerProfile.getPayPoints())
                    .setIntegralType(1l));

            return JsonResult.OK().data("积分已退回");
        }

        return JsonResult.FAIL_OPERATION("操作失败");
    }



    @ApiOperation(value = "已通过-添加疗程")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "customerProFileId", value = "客户档案Id", required = true, dataType = "long"),
            @ApiImplicitParam(name = "logistics", value = "运单号", required = true, dataType = "String"),
    })
    @PostMapping("/AddTreatment")
    public JsonResult AddTreatment(long customerProFileId, String logistics) throws Exception {
        SldzCustomerTreatmentFile SldzCustomerTreatmentFile = new SldzCustomerTreatmentFile();
        SldzCustomerTreatmentFile.setCustomerProFile(customerProFileId);
        SldzCustomerTreatmentFile.setLogistics(logistics);
        return JsonResult.OK().data(sldzCustomerTreatmentFileService.createEntity(SldzCustomerTreatmentFile));
    }





    /***
    * 查询ViewObject的分页数据
    * <p>
    * url请求参数示例: /list?field=abc&pageIndex=1&orderBy=abc:DESC
    * </p>
    * @return
    * @throws Exception
    */
//    @ApiOperation(value = "获取列表分页数据")
//    @GetMapping("/list")
//    public JsonResult getViewObjectListMapping(SldzCustomerProfileDTO queryDto, Pagination pagination) throws Exception{
//        return super.getViewObjectList(queryDto, pagination, SldzCustomerProfileListVO.class);
//    }

    /***
    * 根据资源id查询ViewObject
    * @param id ID
    * @return
    * @throws Exception
    */
//    @ApiOperation(value = "根据ID获取详情数据")
//    @GetMapping("/{id}")
//    public JsonResult getViewObjectMapping(@PathVariable("id")Long id) throws Exception{
//        return super.getViewObject(id, SldzCustomerProfileDetailVO.class);
//    }

    /***
    * 创建资源对象
    * @param entity
    * @return JsonResult
    * @throws Exception
    */
//    @ApiOperation(value = "新建数据")
//    @PostMapping("/")
//    public JsonResult createEntityMapping(@Valid @RequestBody SldzCustomerProfile entity) throws Exception {
//        return super.createEntity(entity);
//    }

    /***
    * 根据ID更新资源对象
    * @param entity
    * @return JsonResult
    * @throws Exception
    */


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