package com.gxc.sldz;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gxc.sldz.Utils.OrderUtil;
import com.gxc.sldz.Utils.RedisUtils;
import com.gxc.sldz.entity.SldzOrder;
import com.gxc.sldz.service.SldzOrderService;
import com.gxc.sldz.vo.OrderProductJsonVo;
import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.KeyPair;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

@SpringBootTest
class   SldzApplicationTests {
    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private SldzOrderService sldzOrderService;

    @Autowired
    private StringEncryptor encryptor;


    @Test
    void contextLoawasads() {
//        System.out.println(encryptor.encrypt("3D3336TD"));

//        KeyPair pair = SecureUtil.generateKeyPair("RSA");
//        System.out.println(Base64.getEncoder().encodeToString(pair.getPublic().getEncoded()));
//        System.out.println(Base64.getEncoder().encodeToString(pair.getPrivate().getEncoded()));

    }

    @Test
    void contextLoads() {
        //本月要发的积分
        double oal = 480;
        //本月所有天
//        int days = getDaysOfMonth(DateUtil.date());
        int days = 31;
        //总积分
        double ko =0;

        for (double i = 1; i <= days; i++) {
            ko+=i;
            if (i==days){
                ko+=oal-ko;
            }
            System.out.println("当前应得积分"+i+"，第"+i+"天获得总"+ko+"积分");
        }


        //测试
//        System.out.println(getStringRandom(8));

    }
//    //生成随机数字和字母,
//    public String getStringRandom(int length) {
//        char arr[] = new char[length];
//        int i = 0;
//        while (i < length) {
//            char ch = (char) (int) (Math.random() * 124);
//            if (ch >= 'A' && ch <= 'Z' || ch >= 'a' && ch <= 'z' || ch >= '0' && ch <= '9') {
//                arr[i++] = ch;
//            }
//        }
//        //将数组转为字符串
//        return new String(arr);
//    }



    public static int getDaysOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    @Test
    void contextLoadsa() {

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try
        {
            Date d1 = df.parse("2004-01-01 00:00:00");
            Date d2 = df.parse("2004-01-01 00:01:00");
            //这样得到的差值是毫秒级别
            long diff = d2.getTime() - d1.getTime();
            System.out.println(diff/1000);
            System.out.println(d1.getTime());
            System.out.println(d2.getTime());
//            if (redisUtils.set("sas","ulaj",100)){
//                System.out.println(redisUtils.get("sas"));
//            }
        }catch (Exception e)
        {
        }
    }





    @Test
    void contextLoadssa() {
        List<OrderProductJsonVo>   OrderProductJsonVo =
                OrderUtil.getOrderProductJsonVo("[{\"id\":\"10000125\",\"createTime\":\"2021-06-30 19:03:25\",\"agentRandom\":\"KXHS1lDU\",\"productId\":\"10000006\",\"productJson\":{\"id\":\"10000006\",\"createTime\":\"2021-06-04 11:48:07\",\"productCategory\":\"10000005\",\"productImgs\":[{\"url\":\"http://wx1.shiguangyimei.com/imgs/2021-06-04/保湿修复喷雾.jpg\",\"uid\":1622778441869,\"status\":\"success\"}],\"productName\":\"保湿喷雾液\",\"productPrice\":189,\"productVideo\":\"http://wx1.shiguangyimei.com/imgs/2021-06-04/vedio2.mp4\",\"productDetails\":\" <p>真好，是真的好！</p>\",\"productStock\":\"1000000\",\"sldzProductCategoryCategoryName\":\"梵莲娜\"},\"cartNum\":\"1\",\"checked\":true}]");
        for (OrderProductJsonVo asfssa :OrderProductJsonVo) {
            System.out.println(asfssa);
        }

    }

    @Test
    void contextLoads22sa() throws Exception {
        String sake = "1000333";
        String sake2 = "1000332";
        String sake3 = "1000334";
        SldzOrder s = GetOrderObjectByOrderNumbers("20210709102002476441964");

        if (StrUtil.isBlank(s.getProductsIdReviewed())){
            s.setProductsIdReviewed(sake);
            System.out.println(s.getProductsIdReviewed());
        }else {
            System.out.println( s.getProductsIdReviewed()+","+sake2+","+sake2);
        }


    }




    @Test
        void contextLoadsssada() {
        String s = "{\"couponJson\":\"{\\\"couponsFullPrice\\\":599,\\\"couponsTotal\\\":100,\\\"couponsReducePrice\\\":50,\\\"couponsName\\\":\\\"美肤节7.18\\\",\\\"couponsFailureTime\\\":1626623940000,\\\"primaryKeyVal\\\":10000027,\\\"couponsAppointProductIds\\\":\\\"[{\\\\\\\"productName\\\\\\\":\\\\\\\"尔特喏特\\\\\\\",\\\\\\\"id\\\\\\\":\\\\\\\"10000013\\\\\\\"},{\\\\\\\"productName\\\\\\\":\\\\\\\"保湿喷雾液\\\\\\\",\\\\\\\"id\\\\\\\":\\\\\\\"10000006\\\\\\\"}]\\\",\\\"couponsShowOrHide\\\":2,\\\"deleted\\\":false,\\\"createTime\\\":1625725377000,\\\"couponsDetails\\\":\\\"保湿喷雾可用\\\",\\\"id\\\":10000027}\"}";

        JSONObject rowData = JSONObject.parseObject(s);
        JSONObject rowData2 = JSONObject.parseObject(rowData.getString("couponJson"));
        rowData2.getString("couponsTotal");
//        JSONArray tableData = JSONArray.parseArray(s);

        System.out.println(rowData2.getString("couponsTotal"));
    }



    //根据订单号获取已完成订单对象
    public SldzOrder GetOrderObjectByOrderNumbers(String orderNumber) throws Exception {
        LambdaQueryWrapper<SldzOrder> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SldzOrder::getOrderNumber, orderNumber);
        wrapper.eq(SldzOrder::getState, 3);
        SldzOrder s = sldzOrderService.getSingleEntity(wrapper);
        return s;
    }






}

