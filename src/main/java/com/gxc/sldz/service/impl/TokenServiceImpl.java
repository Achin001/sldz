package com.gxc.sldz.service.impl;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.text.StrBuilder;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.extension.api.R;
import com.diboot.core.util.S;
import com.gxc.sldz.Utils.Constant;
import com.gxc.sldz.Utils.RedisUtils;
import com.gxc.sldz.Utils.Response;
import com.gxc.sldz.Utils.ResponseCode;
import com.gxc.sldz.handler.ServiceException;
import com.gxc.sldz.service.TokenService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private RedisUtils redisUtil;

    @Override
    public String createToken() {
        //生成uuid当作token
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        //将生成的token存入redis中
        redisUtil.set(token, token, 60);
        return token;

    }

}





