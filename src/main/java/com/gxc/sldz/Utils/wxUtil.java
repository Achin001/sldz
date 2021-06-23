package com.gxc.sldz.Utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class wxUtil {

    @Autowired
    private RedisUtils redisUtils;

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


    //获取AccessToken
    public String getAccessToken() throws Exception {
        //去redis里面找有无AccessToken
        String AccessToken =   redisUtils.get("AccessToken");
        if (StrUtil.isBlank(AccessToken)){
            String requestUrl ="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+wxconfig.WX_LOGIN_APPID+"&secret="+wxconfig.WX_LOGIN_SECRET;
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


//    public String getQRCode(HttpServletResponse response, String random, HttpServletRequest request) {
//        try
//        {
//            String wxCodeURL = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token="+getAccessToken();
//            URL url = new URL(wxCodeURL);
//            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
//            httpURLConnection.setRequestMethod("POST");// 提交模式
//            // 发送POST请求必须设置如下两行
//            httpURLConnection.setDoOutput(true);
//            httpURLConnection.setDoInput(true);
//            // 获取URLConnection对象对应的输出流
//            PrintWriter printWriter = new PrintWriter(httpURLConnection.getOutputStream());
//            // 发送请求参数
//            JSONObject paramJson = new JSONObject();
//            paramJson.put("scene", "random="+random);
////              paramJson.put("page", "pages/index/index"); //小程序暂未发布我没有带page参数
//            printWriter.write(paramJson.toString());
//            // flush输出流的缓冲
//            printWriter.flush();
//            //开始获取数据
//            BufferedInputStream bis = new BufferedInputStream(httpURLConnection.getInputStream());
//            OutputStream os = new FileOutputStream(new File("D:/wechat/qr"+id+".png"));
//            int len;
//            byte[] arr = new byte[1024];
//            while ((len = bis.read(arr)) != -1)
//            {
//                os.write(arr, 0, len);
//                os.flush();
//            }
//            os.close();
////实现了生成二维码并保存在本地电脑的硬盘中。
//            File file = new File("D:/wechat/qr"+id+".png");
//            FileInputStream fis = new FileInputStream(file);
//            long size = file.length();
//            byte[] temp = new byte[(int) size];
//            fis.read(temp, 0, (int) size);
//            fis.close();
//            byte[] data = temp;
//            response.setContentType("image/png");
//            OutputStream out = response.getOutputStream();
//            out.write(data);
//            out.flush();
//            out.close();
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//        return null;
//    }

}
