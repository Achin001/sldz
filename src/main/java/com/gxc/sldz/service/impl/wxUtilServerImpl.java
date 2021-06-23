package com.gxc.sldz.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gxc.sldz.Utils.RedisUtils;
import com.gxc.sldz.Utils.wxconfig;
import com.gxc.sldz.service.wxUtilServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


@Service
public class wxUtilServerImpl implements wxUtilServer {


    @Autowired
    private RedisUtils redisUtils;

    @Override
    public String getAccessToken() throws Exception {
        //去redis里面找有无AccessToken
        String AccessToken =   redisUtils.get("AccessToken");
        if (StrUtil.isBlank(AccessToken)){
            String requestUrl ="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+ wxconfig.WX_LOGIN_APPID+"&secret="+wxconfig.WX_LOGIN_SECRET;
            URL url = new URL(requestUrl);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            // 设置通用的请求属性
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Connection", "Keep-Alive");
            connection.setUseCaches(false);
            connection.setDoOutput(true);
            connection.setDoInput(true);
            // 得到请求的输出流对象
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            out.writeBytes("");
            out.flush();
            out.close();
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            BufferedReader in = null;
            if (requestUrl.contains("nlp"))
                in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "GBK"));
            else
                in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String result = "";
            String getLine;
            while ((getLine = in.readLine()) != null) {
                result += getLine;
            }
            in.close();
            JSONObject jsonObject = JSON.parseObject(result);
            AccessToken = jsonObject.getString("access_token");
            redisUtils.set("AccessToken",AccessToken,7200);
            return AccessToken;
        }
        return AccessToken;
    }



}
