package com.gxc.sldz.controller;

import cn.hutool.crypto.SecureUtil;
import com.gxc.sldz.service.SldzAgentRelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.diboot.core.vo.*;
import com.gxc.sldz.entity.SldzAgent;
import com.gxc.sldz.dto.SldzAgentDTO;
import com.gxc.sldz.vo.SldzAgentListVO;
import com.gxc.sldz.vo.SldzAgentDetailVO;
import com.gxc.sldz.service.SldzAgentService;
import lombok.extern.slf4j.Slf4j;
import javax.validation.Valid;
import java.util.List;

/**
 * 代理商 相关Controller
 * @author Achin
 * @version 1.0
 * @date 2021-05-31
 * Copyright © MyCompany
 */
@Api(tags = { "代理商后台接口" })
@RestController
@RequestMapping("admin/sldzAgent")
@Slf4j
public class SldzAgentController extends BaseCustomCrudRestController<SldzAgent> {

    @Autowired
    private SldzAgentService sldzAgentService;

    @Autowired
    private SldzAgentRelService sldzAgentRelService;

    /**
     * 查询ViewObject的分页数据
     * <p>
     * url请求参数示例: /list?field=abc&pageIndex=1&orderBy=abc:DESC
     * </p>
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "获取列表分页数据")
    @GetMapping("/list")
    public JsonResult getViewObjectListMapping(SldzAgentDTO queryDto, Pagination pagination) throws Exception {
        return super.getViewObjectList(queryDto, pagination, SldzAgentListVO.class);
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

    /**
     * 创建资源对象
     * @param entity
     * @return JsonResult
     * @throws Exception
     */
    @ApiOperation(value = "新建代理商数据")
    @PostMapping("/")
    public JsonResult createEntityMapping(@Valid @RequestBody SldzAgent entity) throws Exception {
        entity.setAgentPasword(SecureUtil.md5(entity.getAgentPhone()));
        return super.createEntity(entity);
    }

    /**
     * 根据ID更新资源对象
     * @param entity
     * @return JsonResult
     * @throws Exception
     */
    @ApiOperation(value = "根据ID更新代理商数据")
    @PutMapping("/{id}")
    public JsonResult updateEntityMapping(@PathVariable("id") Long id, @Valid @RequestBody SldzAgent entity) throws Exception {
        return super.updateEntity(id, entity);
    }
    // /**
    // * 根据id删除资源对象
    // * @param id
    // * @return
    // * @throws Exception
    // */
    // @ApiOperation(value = "根据ID删除数据")
    // @DeleteMapping("/{id}")
    // public JsonResult deleteEntityMapping(@PathVariable("id") Long id) throws Exception {
    // return super.deleteEntity(id);
    // }


}
