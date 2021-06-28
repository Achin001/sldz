package com.gxc.sldz.Utils;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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





}
