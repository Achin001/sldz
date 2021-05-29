package com.gxc.sldz.config.jwt;

import org.springframework.beans.factory.annotation.Autowired;
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
                //放行
                .excludePathPatterns(
                        "/admin/login",
                        "/api/**"
                );


    }
}