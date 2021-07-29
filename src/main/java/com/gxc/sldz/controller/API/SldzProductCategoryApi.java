package com.gxc.sldz.controller.API;


import com.diboot.core.vo.JsonResult;
import com.diboot.core.vo.Pagination;
import com.gxc.sldz.controller.BaseCustomCrudRestController;
import com.gxc.sldz.dto.SldzProductCategoryDTO;
import com.gxc.sldz.entity.SldzProductCategory;
import com.gxc.sldz.vo.SldzProductCategoryListVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * sldz api产品类别
 *
 * @author Achin
 * @date 2021-07-28 16:08:56
 */
@Api(tags = {"产品类别前台接口"})
@RestController
@RequestMapping("api/sldzProductCategory")
@Slf4j
public class SldzProductCategoryApi extends BaseCustomCrudRestController<SldzProductCategory> {

    /**
     * 得到映射视图对象列表
     *
     * @param queryDto   查询dto
     * @param pagination 分页
     * @return {@link JsonResult}
     * @throws Exception 异常
     */
    @ApiOperation(value = "获取列表分页数据")
    @GetMapping("/list")
    public JsonResult getViewObjectListMapping(SldzProductCategoryDTO queryDto, Pagination pagination) throws Exception{
        return super.getViewObjectList(queryDto, pagination, SldzProductCategoryListVO.class);
    }





}
