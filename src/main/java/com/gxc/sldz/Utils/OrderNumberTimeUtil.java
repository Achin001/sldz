package com.gxc.sldz.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

//自动生成订单号
public class OrderNumberTimeUtil {


    public static String getOrderIdByTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String newDate = sdf.format(new Date());
        String result = "";
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            result += random.nextInt(10);
        }
        return newDate + result + (int) +((Math.random() * 9 + 1) * 100000);
    }

}
