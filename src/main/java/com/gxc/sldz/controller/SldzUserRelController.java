package com.gxc.sldz.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gxc.sldz.entity.SldzAgent;
import com.gxc.sldz.entity.SldzAgentRel;
import com.gxc.sldz.entity.SldzUser;
import com.gxc.sldz.service.SldzUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.diboot.core.vo.*;
import com.gxc.sldz.entity.SldzUserRel;
import com.gxc.sldz.dto.SldzUserRelDTO;
import com.gxc.sldz.vo.SldzUserRelListVO;
import com.gxc.sldz.vo.SldzUserRelDetailVO;
import com.gxc.sldz.service.SldzUserRelService;

import lombok.extern.slf4j.Slf4j;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
* 用户绑定 相关Controller
* @author Achin
* @version 1.0
* @date 2021-07-15
* Copyright © MyCompany
*/
@Api(tags = {"用户绑定后台接口"})
@RestController
@RequestMapping("admin/sldzUserRel")
@Slf4j
public class SldzUserRelController extends BaseCustomCrudRestController<SldzUserRel> {


    @Autowired
    private SldzUserRelService sldzUserRelService;


    @Autowired
    private SldzUserService SldzUserService;


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
    public JsonResult getViewObjectListMapping(SldzUserRelDTO queryDto, Pagination pagination) throws Exception{
        return super.getViewObjectList(queryDto, pagination, SldzUserRelListVO.class);
    }

    /***
    * 根据资源id查询ViewObject
    * @param id ID
    * @return
    * @throws Exception
    */
    @ApiOperation(value = "根据ID获取详情数据")
    @GetMapping("/{id}")
    public JsonResult getViewObjectMapping(@PathVariable("id")Long id) throws Exception{
        return super.getViewObject(id, SldzUserRelDetailVO.class);
    }

    /***
    * 创建资源对象
    * @param entity
    * @return JsonResult
    * @throws Exception
    */
    @ApiOperation(value = "新建数据")
    @PostMapping("/")
    public JsonResult createEntityMapping(@Valid @RequestBody SldzUserRel entity) throws Exception {
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
    public JsonResult updateEntityMapping(@PathVariable("id")Long id, @Valid @RequestBody SldzUserRel entity) throws Exception {
        return super.updateEntity(id, entity);
    }

    /***
    * 根据id删除资源对象
    * @param id
    * @return
    * @throws Exception
    */
    @ApiOperation(value = "根据ID删除数据")
    @DeleteMapping("/{id}")
    public JsonResult deleteEntityMapping(@PathVariable("id")Long id) throws Exception {
        return super.deleteEntity(id);
    }



    @ApiOperation(value = "根据唯一编码获取一级客户列表")
    @GetMapping("/relUserListClassA")
    public JsonResult relUserListClassA(SldzUserRel queryDto) throws Exception {
        LambdaQueryWrapper<SldzUserRel> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SldzUserRel::getSupRandom, queryDto.getSupRandom());
        List<SldzUserRel>  SldzUserRel1 = sldzUserRelService.getEntityList(wrapper);
        List<SldzUser> SldzUserList = new ArrayList<>();
        for(SldzUserRel s :SldzUserRel1){
            LambdaQueryWrapper<SldzUser> wrappersubs = new LambdaQueryWrapper<>();
            wrappersubs.eq(SldzUser::getRandom, s.getSubRandom());
            SldzUser  SldzUser  = SldzUserService.getSingleEntity(wrappersubs);
            SldzUserList.add(SldzUser);
        }

        for(SldzUser s :SldzUserList){
            LambdaQueryWrapper<SldzUserRel> swrapper = new LambdaQueryWrapper<>();
            swrapper.eq(SldzUserRel::getSupRandom, s.getRandom());
            //查出下级个数 写入密码字段
            s.setCity(sldzUserRelService.getEntityListCount(swrapper)+"");
        }
        return JsonResult.OK().data(SldzUserList);
    }


} 