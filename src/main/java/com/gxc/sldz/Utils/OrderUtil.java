package com.gxc.sldz.Utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gxc.sldz.entity.SldzProduct;
import com.gxc.sldz.vo.OrderProductJsonVo;
import com.gxc.sldz.vo.SldzProductListVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Component
public class OrderUtil {


    //获取orderJson productId
    public static  List<Long> getOrderJsonProductId(String s) {
        List<Long> productIds = new ArrayList<>();
        JSONObject rowData = new JSONObject();
        JSONArray tableData = JSONArray.parseArray(s);
        for (int i = 0; i < tableData.size(); i++) {
            rowData = tableData.getJSONObject(i);
            productIds.add(Long.valueOf(rowData.getString("productId")));
        }
        return productIds;
    }


    //获取优惠券里面的产品id
    public static  List<Long> GetProductIdByCoupon(String s) {
        List<Long> productIds = new ArrayList<>();
        JSONObject rowData = JSONObject.parseObject(s);
        String productIdsString = rowData.getString("couponsAppointProductIds");
        JSONArray tableData = JSONArray.parseArray(productIdsString);
        for (int i =0;i< tableData.size() ;i++){
           String dafaf = String.valueOf(tableData.get(i));
            JSONObject rowDatas = JSONObject.parseObject(dafaf);
            productIds.add(Long.valueOf(rowDatas.getString("id")));
        }
        return productIds;
    }


    //获取订单OrderProductJsonVo
    public static  List<OrderProductJsonVo> getOrderProductJsonVo(String s) {
        List<OrderProductJsonVo> orderProductJsonVos = new ArrayList<>();
        JSONArray tableData = JSONArray.parseArray(s);
        JSONObject rowData = new JSONObject();
        for (int i = 0; i < tableData.size(); i++) {
            OrderProductJsonVo OrderProductJsonVo = new OrderProductJsonVo();
            rowData = tableData.getJSONObject(i);
            JSONObject jsonObject = JSON.parseObject(rowData.getString("productJson"));
            OrderProductJsonVo.setProductPrice(Double.parseDouble(jsonObject.getString("favorablePrice")));
            OrderProductJsonVo.setCartNum(Integer.parseInt(rowData.getString("cartNum")));
            OrderProductJsonVo.setProductId(Long.parseLong(jsonObject.getString("id")));
            orderProductJsonVos.add(OrderProductJsonVo);
        }

        return orderProductJsonVos;
    }


    //获取优惠券里面的满足条件
    public static  String GetConditionsCoupon(String s) {
        JSONObject rowData = JSONObject.parseObject(s);
        String ConditionsCoupon = rowData.getString("couponsFullPrice");
        return ConditionsCoupon;
    }


    //解析首页产品
    public static  List<SldzProductListVO> AnalysisHomeProducts(String s) {
        List<SldzProductListVO> SldzProducts= new ArrayList<>();
        JSONArray tableData = JSONArray.parseArray(s);
        for(int i=0;i<tableData.size();i++) {
            //转换为对象
            SldzProductListVO SldzProductListVO = JSON.parseObject(String.valueOf(tableData.getJSONObject(i)), SldzProductListVO.class);
            SldzProducts.add(SldzProductListVO);
        }
        return SldzProducts;
    }



}
