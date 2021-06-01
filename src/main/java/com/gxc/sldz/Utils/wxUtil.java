package com.gxc.sldz.Utils;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;

public class wxUtil {

    public static JSONObject wxLogin(String code){
        // 发送请求
        String wxResult = HttpUtil.get(wxconfig.WX_LOGIN_URL+
                "?appid="+wxconfig.WX_LOGIN_APPID+
                "&secret=" +wxconfig.WX_LOGIN_SECRET+
                "&js_code=" +code+
                "&grant_type="+wxconfig.WX_LOGIN_GRANT_TYPE);
        JSONObject jsonObject = JSONObject.parseObject(wxResult);
    return  jsonObject;
    }
}
