package com.gxc.sldz.service;

import com.diboot.core.vo.JsonResult;
import com.diboot.core.vo.Pagination;
import com.gxc.sldz.entity.SldzProduct;
import com.gxc.sldz.vo.SldzProductListVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* 产品相关Service
* @author Achin
* @version 1.0
* @date 2021-05-20
 * Copyright © MyCompany
*/
public interface SldzProductService extends BaseCustomService<SldzProduct> {

    boolean productStockById (@Param("stock") long stock, @Param("id") long id);

    boolean productStockByIdloa (@Param("stock") long stock,@Param("id") long id);


    List<SldzProductListVO> GetProductsByCategory (@Param("productCategory") long productCategory);


    SldzProductListVO GetProductsById (@Param("id") long id);

    JsonResult GetProductsByKeywords (@Param("keywords") String keywords,String Random);


}