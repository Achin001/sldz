package com.gxc.sldz.Utils;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DateUtils {


    //获取过去12个月的月份
    public static List<String> getThisyear() throws ParseException {
        List<String> list = new ArrayList<String>();
        //获取过去12个月的月份
        LocalDate today = LocalDate.now();
        for(long i = 0L;i <= 11L; i++){
            LocalDate localDate = today.minusMonths(i);
            String month = localDate.toString().substring(0,7);
            list.add(month);
        }
        return list;
    }
}