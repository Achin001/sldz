package com.gxc.sldz.config.jwt;


import cn.hutool.core.util.StrUtil;
import com.gxc.sldz.Utils.RedisUtils;
import io.jsonwebtoken.Claims;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class JwtInterceptor extends HandlerInterceptorAdapter {

    @Resource
    private JWT jwt;


    @Autowired
    private RedisUtils redisUtil;


    public static final String USER_KEY = "userId";


    @SneakyThrows
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        String servletPath = request.getServletPath();
//        log.info("ServletPath: " + servletPath);
        // 不需要验证,直接放行
        boolean isNotCheck = isNotCheck(servletPath);
        if (isNotCheck) {
            return true;
        }

        // 需要验证 等于空401
        String token = getToken(request);
        if (StrUtil.isBlank(token)) {
            response.setContentType("text/html;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(401);
            response.getWriter().print("<font size=6 color=red>登陆信息失效，请重新登录!</font>");
            return false;
        }
        // 获取签名信息
        Claims claims = jwt.getClaimByToken(token);
        // log.info("TOKEN: " + claims);
        // 判断签名是否存在或过期
        boolean b = claims == null || claims.isEmpty() || jwt.isTokenExpired(claims.getExpiration());
        if (b) {
            response.setContentType("text/html;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(401);
            response.getWriter().print("<font size=6 color=red>登陆信息失效，请重新登录!</font>");
            return false;
        }
        request.setAttribute(USER_KEY, claims.getSubject());




        return true;
    }

    /**
     * 根据URL判断当前请求是否不需要校验, true:需要校验
     */
    public boolean isNotCheck(String servletPath) {
// 若 请求接口 以 / 结尾, 则去掉 /
        servletPath = servletPath.endsWith("/")
                ? servletPath.substring(0, servletPath.lastIndexOf("/"))
                : servletPath;
//        log.info("servletPath = " + servletPath);
        for (String path : NOT_CHECK_URL) {
//            log.info("path = " + path);
// path 以 /** 结尾, servletPath 以 path 前缀开头
            if (path.endsWith("/**")) {
                String pathStart = path.substring(0, path.lastIndexOf("/") + 1);
//                log.info("pathStart = " + pathStart);
                if (servletPath.startsWith(pathStart)) {
                    return true;
                }
                String pathStart2 = path.substring(0, path.lastIndexOf("/"));
//                log.info("pathStart2 = " + pathStart2);
                if (servletPath.equals(pathStart2)) {
                    return true;
                }
            }
// servletPath == path
            if (servletPath.equals(path)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取请求Token
     */
    private String getToken(HttpServletRequest request) {
        String token = request.getHeader(jwt.getHeader());
        if (StrUtil.isBlank(token)) {
            token = request.getParameter(jwt.getHeader());
        }
        return token;
    }

    /**
     * 不用拦截的页面路径(也可存入数据库中), 不要以 / 结尾
     */
    private static final String[] NOT_CHECK_URL = {
            "/admin/login",
            "/api/AccountLogin",//账号密码登录
            "/api/WeChatLogin",//微信登录
            "/api/sldzOrder/notify", //微信支付回调
            "/api/sldzProduct/**",//产品列表
            "/api/sldzProductCategory/**",//产品分类列表
            "/api/Home/**",//首页
            "/api/sldzProductReviews/list",//产品评论
            "/api/sldzBanner/**",//轮播图

            "/api/VerificationCodeLoginSendCode",//代理商短信验证码登录-发送验证码
            "/api/VerificationCodeLogin",//代理商短信验证码登录-登录
            "/api/AgentForgotPasswordSendCode",//代理商忘记密码-发送验证码
            "/api/AgentForgotPasswordSendCodeDetermine",//代理商忘记密码-确定修改


            "/common/**",//公共服务接口

    };


}
