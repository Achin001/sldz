package com.gxc.sldz.Utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Component
public class wxUtil {



    public static JSONObject wxLogin(String code){
        // 发送请求
        String wxResult = HttpUtil.get(wxconfig.WX_LOGIN_URL+
                "?appid="+wxconfig.WX_APPID+
                "&secret=" +wxconfig.WX_SECRET+
                "&js_code=" +code+
                "&grant_type="+wxconfig.WX_LOGIN_GRANT_TYPE);
        JSONObject jsonObject = JSONObject.parseObject(wxResult);
    return  jsonObject;
    }


    public static byte[] getminiqrQr(String url, Map<String, Object> paraMap) throws Exception {
        byte[] result = null;
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Content-Type", "application/json");

        // 设置请求的参数
        JSONObject postData = new JSONObject();
        for (Map.Entry<String, Object> entry : paraMap.entrySet()) {
            postData.put(entry.getKey(), entry.getValue());
        }
        httpPost.setEntity(new StringEntity(postData.toString(), "UTF-8"));
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpResponse response = httpClient.execute(httpPost);
        HttpEntity entity = response.getEntity();
        result = EntityUtils.toByteArray(entity);
        return result;

}
}

