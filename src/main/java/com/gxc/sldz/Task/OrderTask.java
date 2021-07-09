package com.gxc.sldz.Task;



import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.gxc.sldz.entity.SldzOrder;
import com.gxc.sldz.service.SldzOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class OrderTask {



    @Autowired
    private SldzOrderService sldzOrderService;


    //每个小时执行一次
    @Scheduled(cron = "0 0/30 * * * ? ")
    public void execute() throws ParseException {
        // 15天 =1296000秒；
        long days15 = 1296000;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date now = df.parse(DateUtil.now());
        List<SldzOrder> GetOrderBeenDelivered =  sldzOrderService. GetOrderBeenDelivered();
        for (SldzOrder sldzOrder : GetOrderBeenDelivered){
            //现在时间减 发货时间  = 时间差 diff 乘 1000 转换成秒
            long diff = (now.getTime() - sldzOrder.getDeliveryTime().getTime()) * 1000;
            if(diff >= days15){
                UpdateWrapper<SldzOrder> updateWrapper = new UpdateWrapper<>();
                updateWrapper.eq("order_number", sldzOrder.getOrderNumber());
                updateWrapper.set("state", 3);
                sldzOrderService.updateEntity(updateWrapper);
            }
        }

        //订单已发货后的一个月
        //自动把订单改为已完成



    }

}
