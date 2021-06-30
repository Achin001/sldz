package com.gxc.sldz.controller.API;


import com.diboot.core.vo.JsonResult;
import com.diboot.core.vo.Pagination;
import com.gxc.sldz.controller.BaseCustomCrudRestController;
import com.gxc.sldz.dto.SldzUserDTO;
import com.gxc.sldz.entity.SldzUser;
import com.gxc.sldz.vo.SldzUserDetailVO;
import com.gxc.sldz.vo.SldzUserListVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = {"消费者/客户前台接口"})
@RestController
@RequestMapping("api/sldzUser")
@Slf4j
public class SldzUserApi extends BaseCustomCrudRestController<SldzUser> {




    /**
     * 根据资源id查询ViewObject
     * @param id ID
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "根据ID获取详情数据")
    @GetMapping("/{id}")
    public JsonResult getViewObjectMapping(@PathVariable("id") Long id) throws Exception {
        return super.getViewObject(id, SldzUserDetailVO.class);
    }


}
