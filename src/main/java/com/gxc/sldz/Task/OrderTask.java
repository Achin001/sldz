package com.gxc.sldz.Task;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gxc.sldz.entity.SldzOrder;
import com.gxc.sldz.service.SldzOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderTask {



    @Autowired
    private SldzOrderService sldzOrderService;


    //每个小时执行一次
    @Scheduled(cron = "0 0 0/1 * * ? ")
    public void execute() {
//        QueryWrapper<SldzOrder> wrapper = new QueryWrapper();
//        wrapper.eq("state",2);
//        wrapper.eq("logistics_number",);
//        log.info("u2");
        //订单已发货后的一个月
        //自动把订单改为已完成



    }

}
