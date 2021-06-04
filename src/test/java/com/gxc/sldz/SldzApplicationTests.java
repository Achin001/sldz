package com.gxc.sldz;

import com.gxc.sldz.Utils.wxconfig;
import com.gxc.sldz.entity.SldzUser;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;

@SpringBootTest
class SldzApplicationTests {

    @Test
    void contextLoads() {
        //测试
        System.out.println(getStringRandom(8));

    }
    //生成随机数字和字母,
    public String getStringRandom(int length) {
        char arr[] = new char[length];
        int i = 0;
        while (i < length) {
            char ch = (char) (int) (Math.random() * 124);
            if (ch >= 'A' && ch <= 'Z' || ch >= 'a' && ch <= 'z' || ch >= '0' && ch <= '9') {
                arr[i++] = ch;
            }
        }
        //将数组转为字符串
        return new String(arr);
    }



}
