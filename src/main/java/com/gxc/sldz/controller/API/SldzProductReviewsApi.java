package com.gxc.sldz.controller.API;


import com.diboot.core.vo.JsonResult;
import com.diboot.core.vo.Pagination;
import com.gxc.sldz.controller.BaseCustomCrudRestController;
import com.gxc.sldz.dto.SldzProductReviewsDTO;
import com.gxc.sldz.entity.SldzProductReviews;
import com.gxc.sldz.service.SldzProductReviewsService;
import com.gxc.sldz.vo.SldzProductReviewsListVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = {"产品评论前台接口"})
@RestController
@RequestMapping("api/sldzProductReviews")
@Slf4j
public class SldzProductReviewsApi extends BaseCustomCrudRestController<SldzProductReviews> {
    @Autowired
    private SldzProductReviewsService sldzProductReviewsService;



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
    public JsonResult getViewObjectListMapping(SldzProductReviewsDTO queryDto, Pagination pagination) throws Exception{
        return super.getViewObjectList(queryDto, pagination, SldzProductReviewsListVO.class);
    }



    /***
     * 创建资源对象
     * @param entity
     * @return JsonResult
     * @throws Exception
     */
    @ApiOperation(value = "新建数据")
    @PostMapping("/")
    public JsonResult createEntityMapping(@Valid @RequestBody SldzProductReviews entity) throws Exception {
        return super.createEntity(entity);
    }



}
