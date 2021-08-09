package com.gxc.sldz.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.gxc.sldz.entity.*;
import com.gxc.sldz.service.SldzAgentProductPriceService;
import com.gxc.sldz.service.SldzBonuSsettingService;
import com.gxc.sldz.service.SldzUserRelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.diboot.core.vo.*;
import com.gxc.sldz.dto.SldzUserDTO;
import com.gxc.sldz.vo.SldzUserListVO;
import com.gxc.sldz.vo.SldzUserDetailVO;
import com.gxc.sldz.service.SldzUserService;
import lombok.extern.slf4j.Slf4j;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户 相关Controller
 * @author Achin
 * @version 1.0
 * @date 2021-05-31
 * Copyright © MyCompany
 */
@Api(tags = { "客户后台接口" })
@RestController
@RequestMapping("admin/sldzUser")
@Slf4j
public class SldzUserController extends BaseCustomCrudRestController<SldzUser> {

    @Autowired
    private SldzUserService sldzUserService;


    @Autowired
    private SldzUserRelService sldzUserRelService;

    @Autowired
    private SldzAgentProductPriceService sldzAgentProductPriceService;


    @Autowired
    private SldzBonuSsettingService sldzBonuSsettingService;



    /**
     * 查询ViewObject的分页数据
     * <p>
     * url请求参数示例: /list?field=abc&pageIndex=1&orderBy=abc:DESC
     * </p>
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "获取列表分页数据")
    @GetMapping("/list")
    public JsonResult getViewObjectListMapping(SldzUserDTO queryDto, Pagination pagination) throws Exception {
        return super.getViewObjectList(queryDto, pagination, SldzUserListVO.class);
    }

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
     * 创建资源对象
     * @param entity
     * @return JsonResult
     * @throws Exception
     */
    @ApiOperation(value = "新建数据")
    @PostMapping("/")
    public JsonResult createEntityMapping(@Valid @RequestBody SldzUser entity) throws Exception {
        return super.createEntity(entity);
    }

    /**
     * 根据ID更新资源对象
     * @param entity
     * @return JsonResult
     * @throws Exception
     */
    @ApiOperation(value = "根据ID更新数据")
    @PutMapping("/{id}")
    public JsonResult updateEntityMapping(@PathVariable("id") Long id, @Valid @RequestBody SldzUser entity) throws Exception {
        return super.updateEntity(id, entity);
    }

    /**
     * 根据id删除资源对象
     * @param id
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "根据ID删除数据")
    @DeleteMapping("/{id}")
    public JsonResult deleteEntityMapping(@PathVariable("id") Long id) throws Exception {
        return super.deleteEntity(id);
    }


    /**
     * 查询ViewObject的分页数据
     * <p>
     * url请求参数示例: /list?field=abc&pageIndex=1&orderBy=abc:DESC
     * </p>
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "获取客户推广列表数据")
    @GetMapping("/GetMembership")
    public JsonResult GetMembership(String Random) throws Exception {
        LambdaQueryWrapper<SldzUserRel> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SldzUserRel::getSupRandom, Random);
        //所有一级
        List<SldzUserRel> SldzAgentRel = sldzUserRelService.getEntityList(wrapper);
        List<SldzUser> SldzUserList = new ArrayList<>();
        for (SldzUserRel s : SldzAgentRel) {
            LambdaQueryWrapper<SldzUser> wrappersubs = new LambdaQueryWrapper<>();
            wrappersubs.eq(SldzUser::getRandom, s.getSubRandom());
            SldzUser SldzUser = sldzUserService.getSingleEntity(wrappersubs);
            SldzUserList.add(SldzUser);
        }
        return JsonResult.OK().data(SldzUserList);
    }



    /**
     * 查询ViewObject的分页数据
     * <p>
     * url请求参数示例: /list?field=abc&pageIndex=1&orderBy=abc:DESC
     * </p>
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "批量设置产品价格")
    @PostMapping("/SetProductPriceBatchByUser")
    public JsonResult SetProductPriceBatchByUser(Long productId,
                                                 double price) throws Exception {
        //记录成功次数
        int los = 0;
        //获取所有用户/客户
        List<SldzUser> UserAll = sldzUserService.UserAll();

        for (SldzUser s :UserAll){
            LambdaQueryWrapper<SldzAgentProductPrice> SldzAgentProductPricewrapper = new LambdaQueryWrapper();
            //查询该代理商有无该产的价格
            SldzAgentProductPricewrapper.eq(SldzAgentProductPrice::getAgentRandom,s.getRandom());
            SldzAgentProductPricewrapper.eq(SldzAgentProductPrice::getProductId,productId);
            SldzAgentProductPrice SldzAgentProductPrice = sldzAgentProductPriceService.getSingleEntity(SldzAgentProductPricewrapper);
            if (ObjectUtil.isNotNull(SldzAgentProductPrice)){
                //有记录则更改
                UpdateWrapper<SldzAgentProductPrice> UpdatewrapperSldzAgentProductPrice = new UpdateWrapper();
                UpdatewrapperSldzAgentProductPrice.set("product_price",price);
                UpdatewrapperSldzAgentProductPrice.eq("product_id",productId);
                UpdatewrapperSldzAgentProductPrice.eq("agent_random",s.getRandom());
                if(sldzAgentProductPriceService. updateEntity(UpdatewrapperSldzAgentProductPrice)){
                    los+=1;
                }
            }else {
                //无记录则新增
                if ( sldzAgentProductPriceService.createEntity(
                        new SldzAgentProductPrice()
                                .setAgentRandom(s.getRandom())
                                .setProductPrice(price)
                                .setProductId(productId))){
                    los+=1;
                }
            }
        }
        Map map = new HashMap();
        map.put("msg",los+"人已更改为："+price);
        return JsonResult.OK(map);
    }



    @ApiOperation(value = "批量设置推广奖励")
    @PostMapping("/SetPromotionRewardsBatchByUser")
    public JsonResult SetPromotionRewardsBatchByUser(Long productId,
                                                 double Bonus) throws Exception {
        //记录成功次数
        int los = 0;
        //获取所有用户/客户
        List<SldzUser> UserAll = sldzUserService.UserAll();

        for (SldzUser s :UserAll){
            LambdaQueryWrapper<SldzBonuSsetting> BonuSsettingwrapper = new LambdaQueryWrapper();
            //查询该代理商有无奖励金
            BonuSsettingwrapper.eq(SldzBonuSsetting::getAgentRandom,s.getRandom());
            BonuSsettingwrapper.eq(SldzBonuSsetting::getProductId,productId);
            SldzBonuSsetting SldzBonuSsettings =  sldzBonuSsettingService.getSingleEntity(BonuSsettingwrapper);
            if (ObjectUtil.isNotNull(SldzBonuSsettings)){
                //有记录则更改
                UpdateWrapper<SldzAgent> UpdatewrapperBonuSsetting = new UpdateWrapper();
                UpdatewrapperBonuSsetting.set("bonus",Bonus);
                UpdatewrapperBonuSsetting.eq("product_id",productId);
                UpdatewrapperBonuSsetting.eq("agent_random",s.getRandom());
                if(sldzBonuSsettingService. updateEntity(UpdatewrapperBonuSsetting)){
                    los+=1;
                }
            }else {
                //无记录则新增
                if ( sldzBonuSsettingService.createEntity(
                        new SldzBonuSsetting()
                                .setAgentRandom(s.getRandom())
                                .setBonus(Bonus)
                                .setProductId(productId))){
                    los+=1;
                }
            }
        }
        Map map = new HashMap();
        map.put("msg",los+"人已更改为："+Bonus);
        return JsonResult.OK().data(map);
    }



    @ApiOperation(value = "为客户/用户设置推广佣金")
    @PutMapping("/AgentBonusSetting")
    public JsonResult AgentBonusSetting(Long UserId,
                                        Long productId,
                                        double Bonus) throws Exception {
        //记录成功次数
        int los = 0;
        SldzUser SldzUser =  sldzUserService.getEntity(UserId);
        LambdaQueryWrapper<SldzBonuSsetting> BonuSsettingwrapper = new LambdaQueryWrapper();
        //查询该代理商有无奖励金
        BonuSsettingwrapper.eq(SldzBonuSsetting::getAgentRandom,SldzUser.getRandom());
        BonuSsettingwrapper.eq(SldzBonuSsetting::getProductId,productId);
        SldzBonuSsetting SldzBonuSsettings =  sldzBonuSsettingService.getSingleEntity(BonuSsettingwrapper);
        if (ObjectUtil.isNotNull(SldzBonuSsettings)){
            //有记录则更改
            UpdateWrapper<SldzAgent> UpdatewrapperBonuSsetting = new UpdateWrapper();
            UpdatewrapperBonuSsetting.set("bonus",Bonus);
            UpdatewrapperBonuSsetting.eq("product_id",productId);
            UpdatewrapperBonuSsetting.eq("agent_random",SldzUser.getRandom());
            if(sldzBonuSsettingService. updateEntity(UpdatewrapperBonuSsetting)){
                los+=1;
            }
        }else {
            //无记录则新增
            if ( sldzBonuSsettingService.createEntity(
                    new SldzBonuSsetting()
                            .setAgentRandom(SldzUser.getRandom())
                            .setBonus(Bonus)
                            .setProductId(productId))){
                los+=1;
            }
        }
        Map map = new HashMap();
        map.put("msg",los+"人已更改为："+Bonus);
        return JsonResult.OK().data(map);
    }



    @ApiOperation(value = "为客户/用户设置产品价格")
    @PutMapping("/AgentProductPriceSetting")
    public JsonResult AgentProductPriceSetting(Long UserId,
                                               Long productId,
                                               double price) throws Exception {
        //记录成功次数
        int los = 0;

        SldzUser SldzUser  =  sldzUserService.getEntity(UserId);
        LambdaQueryWrapper<SldzAgentProductPrice> SldzAgentProductPricewrapper = new LambdaQueryWrapper();
        //查询该代理商有无该产的价格
        SldzAgentProductPricewrapper.eq(SldzAgentProductPrice::getAgentRandom,SldzUser.getRandom());
        SldzAgentProductPricewrapper.eq(SldzAgentProductPrice::getProductId,productId);
        SldzAgentProductPrice SldzAgentProductPrice = sldzAgentProductPriceService.getSingleEntity(SldzAgentProductPricewrapper);
        if (ObjectUtil.isNotNull(SldzAgentProductPrice)){
            //有记录则更改
            UpdateWrapper<SldzAgentProductPrice> UpdatewrapperSldzAgentProductPrice = new UpdateWrapper();
            UpdatewrapperSldzAgentProductPrice.set("product_price",price);
            UpdatewrapperSldzAgentProductPrice.eq("product_id",productId);
            UpdatewrapperSldzAgentProductPrice.eq("agent_random",SldzUser.getRandom());
            if(sldzAgentProductPriceService. updateEntity(UpdatewrapperSldzAgentProductPrice)){
                los+=1;
            }
        }else {
            //无记录则新增
            if ( sldzAgentProductPriceService.createEntity(
                    new SldzAgentProductPrice()
                            .setAgentRandom(SldzUser.getRandom())
                            .setProductPrice(price)
                            .setProductId(productId))){
                los+=1;
            }
        }
        Map map = new HashMap();
        map.put("msg",los+"人已更改为："+price);
        return JsonResult.OK().data(map);
    }


}
