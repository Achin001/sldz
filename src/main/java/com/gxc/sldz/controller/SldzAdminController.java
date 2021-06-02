package com.gxc.sldz.controller;

import cn.hutool.crypto.SecureUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.diboot.core.vo.*;
import com.gxc.sldz.entity.SldzAdmin;
import com.gxc.sldz.dto.SldzAdminDTO;
import com.gxc.sldz.vo.SldzAdminListVO;
import com.gxc.sldz.vo.SldzAdminDetailVO;
import com.gxc.sldz.service.SldzAdminService;

import lombok.extern.slf4j.Slf4j;
import javax.validation.Valid;
import java.util.List;

/**
* 管理员 相关Controller
* @author Achin
* @version 1.0
* @date 2021-06-01
* Copyright © MyCompany
*/
@Api(tags = {"管理员后台接口"})
@RestController
@RequestMapping("admin/sldzAdmin")
@Slf4j
public class SldzAdminController extends BaseCustomCrudRestController<SldzAdmin> {
    @Autowired
    private SldzAdminService sldzAdminService;

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
    public JsonResult getViewObjectListMapping(SldzAdminDTO queryDto, Pagination pagination) throws Exception{
        return super.getViewObjectList(queryDto, pagination, SldzAdminListVO.class);
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
        return super.getViewObject(id, SldzAdminDetailVO.class);
    }

    /***
    * 创建资源对象
    * @param entity
    * @return JsonResult
    * @throws Exception
    */
    @ApiOperation(value = "新建数据")
    @PostMapping("/")
    public JsonResult createEntityMapping(@Valid @RequestBody SldzAdmin entity) throws Exception {
        entity.setPassword(SecureUtil.md5(entity.getPassword()));
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
    public JsonResult updateEntityMapping(@PathVariable("id")Long id, @Valid @RequestBody SldzAdmin entity) throws Exception {
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

} 