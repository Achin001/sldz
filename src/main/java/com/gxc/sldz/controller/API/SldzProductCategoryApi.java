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

@Api(tags = {"产品类别前台接口"})
@RestController
@RequestMapping("api/sldzProductCategory")
@Slf4j
public class SldzProductCategoryApi extends BaseCustomCrudRestController<SldzProductCategory> {

    @ApiOperation(value = "获取列表分页数据")
    @GetMapping("/list")
    public JsonResult getViewObjectListMapping(SldzProductCategoryDTO queryDto, Pagination pagination) throws Exception{
        return super.getViewObjectList(queryDto, pagination, SldzProductCategoryListVO.class);
    }


}
