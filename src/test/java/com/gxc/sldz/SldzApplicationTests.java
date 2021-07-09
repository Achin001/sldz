package com.gxc.sldz;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gxc.sldz.Utils.OrderUtil;
import com.gxc.sldz.Utils.RedisUtils;
import com.gxc.sldz.Utils.wxUtil;
import com.gxc.sldz.Utils.wxconfig;
import com.gxc.sldz.entity.SldzOrder;
import com.gxc.sldz.entity.SldzUser;
import com.gxc.sldz.vo.OrderProductJsonVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

@SpringBootTest
class SldzApplicationTests {
    @Autowired
    private RedisUtils redisUtils;


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
        void contextLoadsssada() {
        String s = "{\"couponJson\":\"{\\\"couponsFullPrice\\\":599,\\\"couponsTotal\\\":100,\\\"couponsReducePrice\\\":50,\\\"couponsName\\\":\\\"美肤节7.18\\\",\\\"couponsFailureTime\\\":1626623940000,\\\"primaryKeyVal\\\":10000027,\\\"couponsAppointProductIds\\\":\\\"[{\\\\\\\"productName\\\\\\\":\\\\\\\"尔特喏特\\\\\\\",\\\\\\\"id\\\\\\\":\\\\\\\"10000013\\\\\\\"},{\\\\\\\"productName\\\\\\\":\\\\\\\"保湿喷雾液\\\\\\\",\\\\\\\"id\\\\\\\":\\\\\\\"10000006\\\\\\\"}]\\\",\\\"couponsShowOrHide\\\":2,\\\"deleted\\\":false,\\\"createTime\\\":1625725377000,\\\"couponsDetails\\\":\\\"保湿喷雾可用\\\",\\\"id\\\":10000027}\"}";

        JSONObject rowData = JSONObject.parseObject(s);
        JSONObject rowData2 = JSONObject.parseObject(rowData.getString("couponJson"));
        rowData2.getString("couponsTotal");
//        JSONArray tableData = JSONArray.parseArray(s);

        System.out.println(rowData2.getString("couponsTotal"));
    }









}

