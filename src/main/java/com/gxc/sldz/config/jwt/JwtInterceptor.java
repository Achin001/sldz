package com.gxc.sldz.config.jwt;


import cn.hutool.core.util.StrUtil;
import com.gxc.sldz.Utils.RedisUtils;
import com.gxc.sldz.Utils.ResponseCode;
import com.gxc.sldz.handler.ServiceException;
import com.gxc.sldz.service.ApiIdempotent;
import com.gxc.sldz.service.TokenService;
import io.jsonwebtoken.Claims;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

@Slf4j
@Component
public class JwtInterceptor extends HandlerInterceptorAdapter {

    @Resource
    private JWT jwt;

    @Autowired
    private TokenService tokenService;

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



//        //从请求头中获取token2
//        String token2=request.getHeader("token2");
//        if (StringUtils.isBlank(token2)){
//            //如果请求头token为空就从参数中获取
//            token=request.getParameter("token2");
//            //如果都为空抛出参数异常的错误
//            if (StringUtils.isBlank(token)){
//                response.setContentType("text/html;charset=UTF-8");
//                response.setCharacterEncoding("UTF-8");
//                response.setStatus(201);
//                response.getWriter().print("<font size=6 color=red>请勿重复提交!</font>");
//                return false;
//            }
//        }
//        //如果redis中不包含该token，说明token已经被删除了，抛出请求重复异常
//        if (!redisUtil.exists(token2)){
//            response.setContentType("text/html;charset=UTF-8");
//            response.setCharacterEncoding("UTF-8");
//            response.setStatus(201);
//            response.getWriter().print("<font size=6 color=red>请勿重复提交!</font>");
//            return false;
//        }
//        //删除token2
//        Boolean del=redisUtil.delete(token2);
//        //如果删除不成功（已经被其他请求删除），抛出请求重复异常
//        if (!del){
//            response.setContentType("text/html;charset=UTF-8");
//            response.setCharacterEncoding("UTF-8");
//            response.setStatus(201);
//            response.getWriter().print("<font size=6 color=red>请勿重复提交!</font>");
//            return false;
//        }

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
    };


}
