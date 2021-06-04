package com.gxc.sldz.Utils;

public class StringRandomUtil {

    //生成随机数字和字母,
    public static String getStringRandom() {
        int size = 8;
        char arr[] = new char[size];
        int i = 0;
        while (i < size) {
            char ch = (char) (int) (Math.random() * 124);
            if (ch >= 'A' && ch <= 'Z' || ch >= 'a' && ch <= 'z' || ch >= '0' && ch <= '9') {
                arr[i++] = ch;
            }
        }
        //将数组转为字符串
        return new String(arr);
    }
}
