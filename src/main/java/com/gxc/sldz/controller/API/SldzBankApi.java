package com.gxc.sldz.controller.API;


import com.diboot.core.vo.JsonResult;
import com.diboot.core.vo.Pagination;
import com.gxc.sldz.controller.BaseCustomCrudRestController;
import com.gxc.sldz.dto.SldzBankDTO;
import com.gxc.sldz.entity.SldzBank;
import com.gxc.sldz.service.SldzBankService;
import com.gxc.sldz.vo.SldzBankDetailVO;
import com.gxc.sldz.vo.SldzBankListVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = {"银行卡前台接口"})
@RestController
@RequestMapping("api/sldzBank")
@Slf4j
public class SldzBankApi extends BaseCustomCrudRestController<SldzBank>{
    @Autowired
    private SldzBankService sldzBankService;

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
    public JsonResult getViewObjectListMapping(SldzBankDTO queryDto, Pagination pagination) throws Exception{
        return super.getViewObjectList(queryDto, pagination, SldzBankListVO.class);
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
        return super.getViewObject(id, SldzBankDetailVO.class);
    }

    /***
     * 创建资源对象
     * @param entity
     * @return JsonResult
     * @throws Exception
     */
    @ApiOperation(value = "新建数据")
    @PostMapping("/")
    public JsonResult createEntityMapping(@Valid @RequestBody SldzBank entity) throws Exception {
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
    public JsonResult updateEntityMapping(@PathVariable("id")Long id, @Valid @RequestBody SldzBank entity) throws Exception {
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
