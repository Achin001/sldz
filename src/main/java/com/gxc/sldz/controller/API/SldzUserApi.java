package com.gxc.sldz.controller.API;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.diboot.core.vo.JsonResult;
import com.diboot.core.vo.Pagination;
import com.gxc.sldz.controller.BaseCustomCrudRestController;
import com.gxc.sldz.dto.SldzUserDTO;
import com.gxc.sldz.entity.SldzAgent;
import com.gxc.sldz.entity.SldzAgentRel;
import com.gxc.sldz.entity.SldzUser;
import com.gxc.sldz.entity.SldzUserRel;
import com.gxc.sldz.service.SldzUserRelService;
import com.gxc.sldz.service.SldzUserService;
import com.gxc.sldz.vo.SldzAgentDetailVO;
import com.gxc.sldz.vo.SldzUserDetailVO;
import com.gxc.sldz.vo.SldzUserListVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Api(tags = {"客户前台接口"})
@RestController
@RequestMapping("api/sldzUserl")
@Slf4j
public class SldzUserApi extends BaseCustomCrudRestController<SldzUser> {


    @Autowired
    private SldzUserRelService sldzUserRelService;

    @Autowired
    private SldzUserService sldzUserService;

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

    /**
     * 获得会员资格
     *
     * @param Random 随机
     * @return {@link JsonResult}
     * @throws Exception 异常
     */
    @ApiOperation(value = "客户获取会员列表")
    @GetMapping("/GetMembership}")
    public JsonResult GetMembership(String Random) throws Exception {

        LambdaQueryWrapper<SldzUserRel> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SldzUserRel::getSupRandom, Random);
        //所有一级
        List<SldzUserRel> SldzAgentRel = sldzUserRelService.getEntityList(wrapper);
        List<SldzUser> SldzAgentsList = new ArrayList<>();
        for (SldzUserRel s : SldzAgentRel) {
            LambdaQueryWrapper<SldzUser> wrappersubs = new LambdaQueryWrapper<>();
            wrappersubs.eq(SldzUser::getRandom, s.getSubRandom());
            SldzUser SldzUser = sldzUserService.getSingleEntity(wrappersubs);
            SldzAgentsList.add(SldzUser);
        }
        return JsonResult.OK().data(SldzAgentsList);
    }


}
