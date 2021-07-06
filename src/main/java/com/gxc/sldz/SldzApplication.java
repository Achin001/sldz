package com.gxc.sldz;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SldzApplication {

    public static void main(String[] args) {
        SpringApplication.run(SldzApplication.class, args);
        System.out.println("diboot地址:https://localhost:83/diboot/index.html");
        System.out.println("druid地址:https://localhost:83/druid");
        System.out.println("文档地址:https://localhost:83/doc.html");

    }



}