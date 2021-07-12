package com.gxc.sldz.controller.API;


import com.diboot.core.vo.JsonResult;
import com.diboot.core.vo.Pagination;
import com.gxc.sldz.controller.BaseCustomCrudRestController;
import com.gxc.sldz.dto.SldzPunchClockDTO;
import com.gxc.sldz.entity.SldzPunchClock;
import com.gxc.sldz.service.SldzPunchClockService;
import com.gxc.sldz.vo.SldzPunchClockListVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = {"签到前台接口"})
@RestController
@RequestMapping("api/sldzPunchClock")
@Slf4j
public class SldzPunchClockApi extends BaseCustomCrudRestController<SldzPunchClock> {


    @Autowired
    private SldzPunchClockService sldzPunchClockService;


    @ApiOperation(value = "获取列表分页数据")
    @GetMapping("/list")
    public JsonResult getViewObjectListMapping(SldzPunchClockDTO queryDto, Pagination pagination) throws Exception{
        return super.getViewObjectList(queryDto, pagination, SldzPunchClockListVO.class);
    }


    /***
     * 创建资源对象
     * @param entity
     * @return JsonResult
     * @throws Exception
     */
    @ApiOperation(value = "新建数据")
    @PostMapping("/")
    public JsonResult createEntityMapping(@Valid @RequestBody SldzPunchClock entity) throws Exception {

        return super.createEntity(entity);
    }




}
