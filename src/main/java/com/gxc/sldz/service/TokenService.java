package com.gxc.sldz.service;

import com.baomidou.mybatisplus.extension.api.R;
import com.gxc.sldz.Utils.Response;
import com.gxc.sldz.handler.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface TokenService {



    public String createToken();


}
