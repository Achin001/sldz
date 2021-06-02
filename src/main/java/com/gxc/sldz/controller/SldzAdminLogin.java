package com.gxc.sldz.controller;


import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.diboot.core.vo.JsonResult;
import com.diboot.core.vo.Pagination;
import com.gxc.sldz.config.jwt.JWT;
import com.gxc.sldz.dto.SldzAdminDTO;
import com.gxc.sldz.entity.SldzAdmin;
import com.gxc.sldz.entity.SldzAgent;
import com.gxc.sldz.service.SldzAdminService;
import com.gxc.sldz.vo.SldzAdminListVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Api(tags = {"管理员后台登录接口"})
@RestController
@RequestMapping("admin")
@Slf4j
public class SldzAdminLogin  extends BaseCustomCrudRestController<SldzAdmin>{


    @Autowired
    private SldzAdminService sldzAdminService;

    @Autowired
    private JWT JWT;



    @ApiOperation(value = "登录")
    @PostMapping("login")
    public JsonResult login( @RequestBody SldzAdmin entity) throws Exception{
        LambdaQueryWrapper<SldzAdmin> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SldzAdmin::getPhone, entity.getPhone());
        wrapper.eq(SldzAdmin::getPassword, SecureUtil.md5(entity.getPassword()));
        SldzAdmin SldzAdmin =  sldzAdminService.getSingleEntity(wrapper);
        if (ObjectUtil.isNotNull(SldzAdmin)){
            //如果不等于空
            Map map = new HashMap();
            //返回token
            map.put("token", JWT.generateToken(SldzAdmin.getId()));
            map.put("SldzAdmin", SldzAdmin);
            return JsonResult.OK().data(map);
        }
        return JsonResult.FAIL_OPERATION("账号密码错误");
    }


}
