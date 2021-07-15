package com.gxc.sldz.controller.API;


import com.diboot.core.vo.JsonResult;
import com.diboot.core.vo.Pagination;
import com.gxc.sldz.controller.BaseCustomCrudRestController;
import com.gxc.sldz.dto.SldzWithdrawalDTO;
import com.gxc.sldz.entity.SldzWithdrawal;
import com.gxc.sldz.service.SldzWithdrawalService;
import com.gxc.sldz.vo.SldzWithdrawalListVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = {"提现前台接口"})
@RestController
@RequestMapping("api/sldzWithdrawal")
@Slf4j
public class SldzWithdrawalApi extends BaseCustomCrudRestController<SldzWithdrawal> {


    @Autowired
    private SldzWithdrawalService sldzWithdrawalService;

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
    public JsonResult getViewObjectListMapping(SldzWithdrawalDTO queryDto, Pagination pagination) throws Exception{
        return super.getViewObjectList(queryDto, pagination, SldzWithdrawalListVO.class);
    }

        /***
    * 创建资源对象
    * @param entity
    * @return JsonResult
    * @throws Exception
    */
    @ApiOperation(value = "新建数据")
    @PostMapping("/")
    public JsonResult createEntityMapping(@Valid @RequestBody SldzWithdrawal entity) throws Exception {
        return super.createEntity(entity);
    }

}
