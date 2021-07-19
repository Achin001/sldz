package com.gxc.sldz.mapper;

import com.diboot.core.mapper.BaseCrudMapper;
import com.gxc.sldz.entity.SldzProduct;
import com.gxc.sldz.vo.SldzProductListVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
* 产品Mapper
* @author Achin
* @version 1.0
* @date 2021-05-20
 * Copyright © MyCompany
*/
@Mapper
public interface SldzProductMapper extends BaseCrudMapper<SldzProduct> {


    //根据产品id改产品库存
    @Update("UPDATE sldz_product SET product_stock = #{stock}  WHERE id = #{id}")
    boolean productStockById (@Param("stock") long stock,@Param("id") long id);

    //根据产品id减改产品库存
    @Update("UPDATE sldz_product SET product_stock = product_stock - #{stock}  WHERE id = #{id}")
    boolean productStockByIdloa (@Param("stock") long stock,@Param("id") long id);


    //根据分类ID获取产品
    @Select("SELECT * FROM sldz_product WHERE  product_category = #{productCategory}")
    List<SldzProductListVO> GetProductsByCategory (@Param("productCategory") long productCategory);


    //根据ID获取产品
    @Select("SELECT * FROM sldz_product WHERE  id = #{id}")
    SldzProductListVO GetProductsById (@Param("id") long id);


}

