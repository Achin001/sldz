package com.gxc.sldz.mapper;

import com.diboot.core.mapper.BaseCrudMapper;
import com.gxc.sldz.entity.SldzShopCart;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
* 购物车Mapper
* @author Achin
* @version 1.0
* @date 2021-06-18
 * Copyright © MyCompany
*/
@Mapper
public interface SldzShopCartMapper extends BaseCrudMapper<SldzShopCart> {


    @Update("UPDATE sldz_shop_cart SET cart_num = cart_num + 1 WHERE agent_random = #{Random} and id = #{id}")
    boolean cartNumPlus(@Param("Random") String Random,
                        @Param("id") long id);

    @Update("UPDATE sldz_shop_cart SET cart_num = cart_num - 1 WHERE agent_random = #{Random} and id = #{id}")
    boolean cartNumReduce(@Param("Random") String Random,
                        @Param("id") long id);
}

