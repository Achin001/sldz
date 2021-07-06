package com.gxc.sldz;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling // 开启定时任务功能
@SpringBootApplication
public class SldzApplication {

    public static void main(String[] args) {
        SpringApplication.run(SldzApplication.class, args);
        System.out.println("diboot地址:https://localhost:83/diboot/index.html");
        System.out.println("druid地址:https://localhost:83/druid");
        System.out.println("文档地址:https://localhost:83/doc.html");

    }



}