package com.gxc.sldz.controller.API;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.diboot.core.vo.JsonResult;
import com.gxc.sldz.controller.BaseCustomCrudRestController;
import com.gxc.sldz.entity.SldzAdmin;
import com.gxc.sldz.entity.SldzUserRel;
import com.gxc.sldz.service.SldzUserRelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(tags = {"用户绑定前台接口"})
@RestController
@RequestMapping("api/sldzUserRel")
@Slf4j
public class SldzUserRelApi extends BaseCustomCrudRestController<SldzUserRel> {



    @Autowired
    private SldzUserRelService sldzUserRelService;


    /***
     * 创建资源对象
     * @param entity
     * @return JsonResult
     * @throws Exception
     */
    @ApiOperation(value = "新建数据")
    @PostMapping("/")
    public JsonResult createEntityMapping(@Valid @RequestBody SldzUserRel entity) throws Exception {
        //查询当前下级有无绑定
        LambdaQueryWrapper<SldzUserRel> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SldzUserRel::getSubRandom, entity.getSubRandom());
        int SubCount =   sldzUserRelService.getEntityListCount(wrapper);
        if (SubCount<=0){
            return super.createEntity(entity);
        }
        //有则不新建

        return JsonResult.OK();
    }


}
