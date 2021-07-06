package com.gxc.sldz.Utils;


public interface wxconfig {

    // 请求的网址
    public static final String WX_LOGIN_URL = "https://api.weixin.qq.com/sns/jscode2session";
    // 固定参数
    public static final String WX_LOGIN_GRANT_TYPE = "authorization_code";
    // 你的appid
    public static final String WX_APPID = "wx5dd4c5f8a1d5a62a";
    // 你的密匙
    public static final String WX_SECRET = "e7e8ed87a3047491e15e73ce2bcea747";




    //微信支付的商户id
    public static final String mchId = "1564268321";
    //密匙(在微信支付平台自行设置,要求32位.建议使用随机密码)
    public static final String mchKey = "o8mdiqk14nz5aq2jfwi1oafaw02bcbhu";
    //退款密匙(应该这么叫吧.需要去微信支付平台下载.指定密匙的绝对地址)
    public static final String keyPath = "/www/certificate/cert/apiclient_cert.p12";
//    public static final String keyPath = "/Users/test/workspace/apiclient_cert.p12";
    //微信支付完成的通知地址 需要关闭token验证
    public static final String notifyUrl = "https://miniapp.siliandingzhi.com:83/api/sldzOrder/notify";


}
