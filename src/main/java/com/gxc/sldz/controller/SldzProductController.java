package com.gxc.sldz.controller;

import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.diboot.core.vo.*;
import com.gxc.sldz.entity.SldzProduct;
import com.gxc.sldz.dto.SldzProductDTO;
import com.gxc.sldz.vo.SldzProductListVO;
import com.gxc.sldz.vo.SldzProductDetailVO;
import com.gxc.sldz.service.SldzProductService;
import lombok.extern.slf4j.Slf4j;
import javax.validation.Valid;
import java.util.List;

/**
 * 产品 相关Controller
 * @author Achin
 * @version 1.0
 * @date 2021-05-20
 * Copyright © MyCompany
 */
@Api(tags = { "产品管理后台接口" })
@RestController
@RequestMapping("admin/sldzProduct")
@Slf4j
public class SldzProductController extends BaseCustomCrudRestController<SldzProduct> {

    @Autowired
    private SldzProductService sldzProductService;



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
    public JsonResult getViewObjectListMapping(SldzProductDTO queryDto, Pagination pagination) throws Exception {

        return super.getViewObjectList(queryDto, pagination, SldzProductListVO.class);
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
        return super.getViewObject(id, SldzProductDetailVO.class);
    }

    /**
     * 创建资源对象
     * @param entity
     * @return JsonResult
     * @throws Exception
     */
    @ApiOperation(value = "新建数据")
    @PostMapping("/")
    public JsonResult createEntityMapping(@Valid @RequestBody SldzProduct entity) throws Exception {
        return super.createEntity(entity);
    }

    /**
     * 根据ID更新资源对象
     * @param entity
     * @return JsonResult
     * @throws Exception
     */
    @ApiOperation(value = "根据ID更新数据")
    @PutMapping("/{id}")
    public JsonResult updateEntityMapping(@PathVariable("id") Long id, @Valid @RequestBody SldzProduct entity) throws Exception {
        return super.updateEntity(id, entity);
    }

    /**
     * 根据id删除资源对象
     * @param id
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "根据ID删除数据")
    @DeleteMapping("/{id}")
    public JsonResult deleteEntityMapping(@PathVariable("id") Long id) throws Exception {
        return super.deleteEntity(id);
    }
}
