package com.gxc.sldz.controller.API;


import com.diboot.core.vo.JsonResult;
import com.diboot.core.vo.Pagination;
import com.gxc.sldz.controller.BaseCustomCrudRestController;
import com.gxc.sldz.dto.SldzUserDTO;
import com.gxc.sldz.entity.SldzUser;
import com.gxc.sldz.vo.SldzUserListVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"消费者/客户前台接口"})
@RestController
@RequestMapping("api/sldzUser")
@Slf4j
public class SldzUserApi extends BaseCustomCrudRestController<SldzUser> {


}
