package com.gxc.sldz.controller.API;


import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.diboot.core.vo.JsonResult;
import com.diboot.core.vo.Pagination;
import com.gxc.sldz.controller.BaseCustomCrudRestController;
import com.gxc.sldz.dto.SldzShopCartDTO;
import com.gxc.sldz.entity.SldzAdmin;
import com.gxc.sldz.entity.SldzShopCart;
import com.gxc.sldz.service.SldzShopCartService;
import com.gxc.sldz.vo.SldzShopCartListVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = {"购物车前台接口"})
@RestController
@RequestMapping("api/SldzShopCart")
@Slf4j
public class SldzShopCartApi extends BaseCustomCrudRestController<SldzShopCart> {

    @Autowired
    SldzShopCartService sldzShopCartService;


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
    public JsonResult getViewObjectListMapping(SldzShopCartDTO queryDto, Pagination pagination) throws Exception{
        return super.getViewObjectList(queryDto, pagination, SldzShopCartListVO.class);
    }

    /***
     * 根据资源id查询ViewObject
     * @param id ID
     * @return
     * @throws Exception
     */
//    @ApiOperation(value = "根据ID获取详情数据")
//    @GetMapping("/{id}")
//    public JsonResult getViewObjectMapping(@PathVariable("id")Long id) throws Exception{
//        return super.getViewObject(id, SldzShopCartDetailVO.class);
//    }

    /***
     * 创建资源对象
     * @param entity
     * @return JsonResult
     * @throws Exception
     */
    @ApiOperation(value = "新建数据")
    @PostMapping("/")
    public JsonResult createEntityMapping(@Valid @RequestBody SldzShopCart entity) throws Exception {
        LambdaQueryWrapper<SldzShopCart> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SldzShopCart::getProductId, entity.getProductId());
        wrapper.eq(SldzShopCart::getAgentRandom, entity.getAgentRandom());
        SldzShopCart SldzShopCart  = sldzShopCartService.getSingleEntity(wrapper);
        //查询该产品该代理商有无添加购物车
        if (ObjectUtil.isNotNull(SldzShopCart)){
            //有则添加数量
            SldzShopCart.setCartNum(SldzShopCart.getCartNum()+1);
            sldzShopCartService.updateEntity(SldzShopCart);
        }
        //无则新建购物车记录
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
    public JsonResult updateEntityMapping(@PathVariable("id")Long id, @Valid @RequestBody SldzShopCart entity) throws Exception {
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
