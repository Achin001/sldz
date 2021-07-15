package com.gxc.sldz.controller.API;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.diboot.core.vo.JsonResult;
import com.diboot.core.vo.Pagination;
import com.gxc.sldz.controller.BaseCustomCrudRestController;
import com.gxc.sldz.dto.SldzProductDTO;
import com.gxc.sldz.entity.SldzAdmin;
import com.gxc.sldz.entity.SldzAgentProductPrice;
import com.gxc.sldz.entity.SldzProduct;
import com.gxc.sldz.service.SldzAgentProductPriceService;
import com.gxc.sldz.service.SldzProductService;
import com.gxc.sldz.vo.SldzOrderDetailVO;
import com.gxc.sldz.vo.SldzProductListVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@Api(tags = { "产品前台接口" })
@RestController
@RequestMapping("api/sldzProduct")
@Slf4j
public class SldzProductApi  extends BaseCustomCrudRestController<SldzProduct> {




    @Autowired
    SldzProductService SldzProductService;

    @Autowired
    SldzAgentProductPriceService SldzAgentProductPriceService;

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
    public JsonResult getViewObjectListMapping(SldzProduct queryDto,String Random) throws Exception {
        List<SldzProduct> SldzProducts  = SldzProductService.GetProductsByCategory(queryDto.getProductCategory());
        if (StrUtil.isNotBlank(Random)){
           for (SldzProduct s:SldzProducts){
               LambdaQueryWrapper<SldzAgentProductPrice> SldzAgentProductPricewrapper = new LambdaQueryWrapper<>();
               SldzAgentProductPricewrapper.eq(SldzAgentProductPrice::getProductId, s.getId());
               SldzAgentProductPricewrapper.eq(SldzAgentProductPrice::getAgentRandom, Random);
               SldzAgentProductPrice SldzAgentProductPrice =  SldzAgentProductPriceService.getSingleEntity(SldzAgentProductPricewrapper);
               if (ObjectUtil.isNotNull(SldzAgentProductPrice)){
                   s.setFavorablePrice(SldzAgentProductPrice.getProductPrice());
               }
           }
            return JsonResult.OK().data(SldzProducts);
        }
        return JsonResult.OK().data(SldzProducts);
    }

    /***
     * 根据资源id查询ViewObject
     * @param id ID
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "根据ID获取详情数据")
    @GetMapping("/{id}")
    public JsonResult getViewObjectMapping(@PathVariable("id") Long id) throws Exception {
        return super.getViewObject(id, SldzProductListVO.class);
    }

//
//    @ApiOperation(value = "根据唯一编码获取产品价格")
//    @GetMapping("/GetProductPriceByRandom")
//    public JsonResult GetProductPriceByRandom(Long prductId,String Random) throws Exception {
//        LambdaQueryWrapper<SldzAgentProductPrice> SldzAgentProductPricewrapper = new LambdaQueryWrapper<>();
//        SldzAgentProductPricewrapper.eq(SldzAgentProductPrice::getProductId, prductId);
//        SldzAgentProductPricewrapper.eq(SldzAgentProductPrice::getAgentRandom,Random );
//        return JsonResult.OK().data(SldzAgentProductPriceService.getSingleEntity(SldzAgentProductPricewrapper));
//    }


}
