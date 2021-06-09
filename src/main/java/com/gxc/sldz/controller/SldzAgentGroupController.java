package com.gxc.sldz.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gxc.sldz.entity.SldzAdmin;
import com.gxc.sldz.entity.SldzAgent;
import com.gxc.sldz.service.SldzAgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.diboot.core.vo.*;
import com.gxc.sldz.entity.SldzAgentGroup;
import com.gxc.sldz.dto.SldzAgentGroupDTO;
import com.gxc.sldz.vo.SldzAgentGroupListVO;
import com.gxc.sldz.vo.SldzAgentGroupDetailVO;
import com.gxc.sldz.service.SldzAgentGroupService;

import lombok.extern.slf4j.Slf4j;
import javax.validation.Valid;
import java.util.List;

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