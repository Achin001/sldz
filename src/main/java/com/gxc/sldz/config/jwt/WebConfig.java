package com.gxc.sldz.config.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {


    @Autowired
    private JwtInterceptor JwtInterceptor;
    @Autowired
    private CorsInterceptor corsInterceptor;


    /**
     * APP接口拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
// 跨域拦截器需放在最上面
        registry.addInterceptor(corsInterceptor).addPathPatterns("/**");
// 校验token的拦截器
        registry.addInterceptor(JwtInterceptor)
                //拦截
                .addPathPatterns("/admin/**")
                .addPathPatterns("/api/**")
                //放行
                .excludePathPatterns(
                        "/admin/login",
                        "/api/AccountLogin",//账号密码登录
                        "/api/WeChatLogin",//微信登录
                        "/api/sldzOrder/notify", //微信支付回调
                        "/api/sldzProduct/**",//产品列表
                        "/api/sldzProductCategory/**"//产品分类列表
                );


    }



}