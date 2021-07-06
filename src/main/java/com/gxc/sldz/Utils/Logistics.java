package com.gxc.sldz.Utils;




import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;


@Slf4j
public class Logistics {
    public static String main(String no) {
            //快递编号不为空
            String host = "https://goexpress.market.alicloudapi.com";// 【1】请求地址 支持http 和 https 及 WEBSOCKET
            String path = "/goexpress";// 【2】后缀
            String appcode = "423665bfc7d44af0893273cf7b9db25c"; // 【3】开通服务后 买家中心-查看AppCode
//        String no = "SF1094821888143"; // 【4】请求参数，详见文档描述
            String type = ""; //  【4】请求参数，不知道可不填 95%能自动识别
            String urlSend = host + path + "?no=" + no + "&type=" + type; // 【5】拼接请求链接
            try {
                URL url = new URL(urlSend);
                HttpURLConnection httpURLCon = (HttpURLConnection) url.openConnection();
                httpURLCon.setRequestProperty("Authorization", "APPCODE " + appcode);// 格式Authorization:APPCODE
                // (中间是英文空格)
                int httpCode = httpURLCon.getResponseCode();
                if (httpCode == 200) {
                    log.info("正常请求计费(其他均不计费)");
                    log.info("获取返回的json:");
                    String json = read(httpURLCon.getInputStream());
                    log.info(json);
                    return json;
                } else {
                    Map<String, List<String>> map = httpURLCon.getHeaderFields();
                    String error = map.get("X-Ca-Error-Message").get(0);
                    if (httpCode == 400 && error.equals("Invalid AppCode `not exists`")) {
                        log.info("AppCode错误 ");
                        return "AppCode错误";
                    } else if (httpCode == 400 && error.equals("Invalid Url")) {
                        log.info("请求的 Method、Path 或者环境错误");
                        return "请求的 Method、Path 或者环境错误";
                    } else if (httpCode == 400 && error.equals("Invalid Param Location")) {
                        log.info("参数错误");
                        return "参数错误";
                    } else if (httpCode == 403 && error.equals("Unauthorized")) {
                        log.info("服务未被授权（或URL和Path不正确）");
                        return "服务未被授权（或URL和Path不正确）";
                    } else if (httpCode == 403 && error.equals("Quota Exhausted")) {
                        log.info("套餐包次数用完 ");
                        return "套餐包次数用完";
                    } else {
                        log.info("参数名错误 或 其他错误");
                        log.info(error);
                        return error;
                    }
                }

            } catch (MalformedURLException e) {
                log.info("URL格式错误");
                return "URL格式错误";
            } catch (UnknownHostException e) {
                log.info("URL地址错误");
                return "URL地址错误";
            } catch (Exception e) {
                // 打开注释查看详细报错异常信息
                 e.printStackTrace();
                return String.valueOf(e);
            }
    }

    /*
     * 读取返回结果
     */
    private static String read(InputStream is) throws IOException {
        StringBuffer sb = new StringBuffer();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = null;
        while ((line = br.readLine()) != null) {
            line = new String(line.getBytes(), "utf-8");
            sb.append(line);
        }
        br.close();
        return sb.toString();
    }
}
