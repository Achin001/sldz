package com.gxc.sldz.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.diboot.core.vo.JsonResult;
import com.diboot.core.vo.Pagination;
import com.gxc.sldz.entity.SldzAgentProductPrice;
import com.gxc.sldz.entity.SldzProduct;
import com.gxc.sldz.mapper.SldzProductMapper;
import com.gxc.sldz.service.SldzAgentProductPriceService;
import com.gxc.sldz.service.SldzProductService;
import com.gxc.sldz.vo.SldzProductListVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* 产品相关Service实现
* @author Achin
* @version 1.0
* @date 2021-05-20
 * Copyright © MyCompany
*/
@Service
@Slf4j
public class SldzProductServiceImpl extends BaseCustomServiceImpl<SldzProductMapper, SldzProduct> implements SldzProductService {



    @Autowired
    SldzProductMapper SldzProductMapper;

    @Autowired
    SldzAgentProductPriceService SldzAgentProductPriceService;

    @Override
    public boolean productStockById(long stock, long id) {
        return SldzProductMapper.productStockById(stock,id);
    }

    @Override
    public boolean productStockByIdloa(long stock, long id) {
        return SldzProductMapper.productStockByIdloa(stock,id);
    }

    @Override
    public List<SldzProductListVO> GetProductsByCategory(long productCategory) {
        return SldzProductMapper.GetProductsByCategory(productCategory);
    }

    @Override
    public SldzProductListVO GetProductsById(long id) {
        return SldzProductMapper.GetProductsById(id);
    }

    @Override
    public JsonResult GetProductsByKeywords(String keywords,String Random) {
        List<SldzProductListVO> GetProductsByKeywords = SldzProductMapper.GetProductsByKeywords(keywords);
        if (StrUtil.isNotBlank(Random)){
            for (SldzProductListVO s:GetProductsByKeywords){
                LambdaQueryWrapper<SldzAgentProductPrice> SldzAgentProductPricewrapper = new LambdaQueryWrapper<>();
                SldzAgentProductPricewrapper.eq(SldzAgentProductPrice::getProductId, s.getId());
                SldzAgentProductPricewrapper.eq(SldzAgentProductPrice::getAgentRandom, Random);
                SldzAgentProductPrice SldzAgentProductPrice =  SldzAgentProductPriceService.getSingleEntity(SldzAgentProductPricewrapper);
                if (ObjectUtil.isNotNull(SldzAgentProductPrice)){
                    s.setFavorablePrice(SldzAgentProductPrice.getProductPrice());
                }else {
                    s.setFavorablePrice(s.getProductPrice());
                }
            }
            return JsonResult.OK().data(GetProductsByKeywords);
        }else {
            for (SldzProductListVO s:GetProductsByKeywords){
                s.setFavorablePrice(s.getProductPrice());
            }
        }
        return JsonResult.OK().data(GetProductsByKeywords);
    }


}
