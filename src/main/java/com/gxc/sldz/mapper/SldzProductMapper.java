package com.gxc.sldz.mapper;

import com.diboot.core.mapper.BaseCrudMapper;
import com.gxc.sldz.entity.SldzProduct;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

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



}

