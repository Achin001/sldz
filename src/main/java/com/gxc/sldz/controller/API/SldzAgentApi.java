package com.gxc.sldz.controller.API;


import com.gxc.sldz.controller.BaseCustomCrudRestController;
import com.gxc.sldz.entity.SldzAgent;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"代理商前台接口"})
@RestController
@RequestMapping("api/sldzAgent")
@Slf4j
public class SldzAgentApi extends BaseCustomCrudRestController<SldzAgent> {



}
