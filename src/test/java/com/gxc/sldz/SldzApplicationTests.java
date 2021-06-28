package com.gxc.sldz;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gxc.sldz.Utils.OrderUtil;
import com.gxc.sldz.Utils.RedisUtils;
import com.gxc.sldz.Utils.wxUtil;
import com.gxc.sldz.Utils.wxconfig;
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
            if (redisUtils.set("sas","ulaj",100)){
                System.out.println(redisUtils.get("sas"));
            }
        }catch (Exception e)
        {
        }
    }





    @Test
    void contextLoadssa() {
        List<OrderProductJsonVo> orderProductJsonVos = new ArrayList<>();
        String z = "[{\"id\":\"10000107\",\"createTime\":\"2021-06-25 13:05:16\",\"agentRandom\":\"YtENKCuJ\",\"productId\":\"10000013\",\"productJson\":{\"id\":\"10000013\",\"createTime\":\"2021-06-04 19:54:51\",\"productCategory\":\"10000005\",\"productImgs\":[{\"url\":\"http://wx1.shiguangyimei.com/imgs/2021-06-04/保湿修复喷雾.jpg\",\"uid\":1622778844458,\"status\":\"success\"}],\"productName\":\"尔特喏特\",\"productPrice\":236,\"productDetails\":\"<p>二人的头423443&nbsp;</p>\",\"productStock\":\"152\",\"sldzProductCategoryCategoryName\":\"真香\"},\"cartNum\":\"1\",\"checked\":true},{\"id\":\"10000099\",\"createTime\":\"2021-06-21 18:18:08\",\"agentRandom\":\"YtENKCuJ\",\"productId\":\"10000006\",\"productJson\":{\"id\":\"10000006\",\"createTime\":\"2021-06-04 19:48:07\",\"productCategory\":\"10000005\",\"productImgs\":[{\"url\":\"http://wx1.shiguangyimei.com/imgs/2021-06-04/保湿修复喷雾.jpg\",\"uid\":1622778441869,\"status\":\"success\"}],\"productName\":\"保湿喷雾液\",\"productPrice\":189,\"productVideo\":\"http://wx1.shiguangyimei.com/imgs/2021-06-04/vedio2.mp4\",\"productDetails\":\" <p>真好，是真的好！</p>\",\"productStock\":\"200\",\"sldzProductCategoryCategoryName\":\"真香\"},\"cartNum\":\"10\",\"checked\":true},{\"id\":\"10000098\",\"createTime\":\"2021-06-21 18:18:06\",\"agentRandom\":\"YtENKCuJ\",\"productId\":\"10000009\",\"productJson\":{\"id\":\"10000009\",\"createTime\":\"2021-06-04 19:53:39\",\"productCategory\":\"10000005\",\"productImgs\":[{\"url\":\"http://wx1.shiguangyimei.com/imgs/2021-06-04/保湿修复喷雾.jpg\",\"uid\":1622778800125,\"status\":\"success\"}],\"productName\":\"玻尿酸\",\"productPrice\":156,\"productDetails\":\"<p>问题热天热特特</p>\",\"productStock\":\"656\",\"sldzProductCategoryCategoryName\":\"真香\"},\"cartNum\":\"14\",\"checked\":true}]";
        JSONArray tableData = JSONArray.parseArray(z);
        JSONObject rowData = new JSONObject();
        String rowDatas  = null;

        for (int i = 0; i < tableData.size(); i++) {
            OrderProductJsonVo OrderProductJsonVo = new OrderProductJsonVo();
            rowData = tableData.getJSONObject(i);
            JSONObject jsonObject = JSON.parseObject(rowData.getString("productJson"));
            OrderProductJsonVo.setProductPrice(Double.parseDouble(jsonObject.getString("productPrice")));
            OrderProductJsonVo.setCartNum(Integer.parseInt(rowData.getString("cartNum")));
            OrderProductJsonVo.setProductId(Long.parseLong(jsonObject.getString("id")));
            orderProductJsonVos.add(OrderProductJsonVo);
        }
        System.out.println(orderProductJsonVos);
    }









}
