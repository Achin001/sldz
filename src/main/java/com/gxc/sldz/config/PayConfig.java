package com.gxc.sldz.config;

import com.gxc.sldz.Utils.wxconfig;
import com.lly835.bestpay.config.WxPayConfig;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class PayConfig {



    @Bean
    public WxPayConfig wxPayConfig() {
        WxPayConfig wxPayConfig = new WxPayConfig();
        wxPayConfig.setAppId(wxconfig.WX_APPID);//小程序Id
        wxPayConfig.setMchId(wxconfig.mchId);//商户id
        wxPayConfig.setMchKey(wxconfig.mchKey);//密钥
        wxPayConfig.setKeyPath(wxconfig.keyPath);//证书
        wxPayConfig.setNotifyUrl(wxconfig.notifyUrl);///回调地址
        return wxPayConfig;
    }

    @Bean
    public BestPayServiceImpl bestPayService(WxPayConfig WxPayConfig) {
        BestPayServiceImpl bestPayService = new BestPayServiceImpl();
        bestPayService.setWxPayConfig(WxPayConfig);
        return bestPayService;
    }



}
