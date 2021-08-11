package com.gxc.sldz.vo;

import lombok.Data;

import java.util.Random;

//订单奖励应得Vo
@Data
public class OrderRewardDueVo {


    //上级Random
    private String Random;
    //上级姓名
    private String name;

    //关系
    private String Relationship;

    //产品id
    private long productId;
    //产品价格
    private double productPrice;
    //加购数量
    private int cartNum;


    //应得奖励金
    private double DueReward;

}
