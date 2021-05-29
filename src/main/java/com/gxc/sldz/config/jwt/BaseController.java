package com.gxc.sldz.config.jwt;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class BaseController {

    protected int getId(){
        HttpServletRequest request = getHttpServletRequest();
        return Integer.parseInt(request.getAttribute(JwtInterceptor.USER_KEY).toString());
    }
    public static HttpServletRequest getHttpServletRequest(){
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }


}
