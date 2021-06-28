package com.gxc.sldz.vo;

import lombok.Data;

@Data
public class OrderProductJsonVo {

    //产品id
    private  long productId;
    //产品价格
    private  double productPrice;
    //加购数量
    private  int cartNum;


}
