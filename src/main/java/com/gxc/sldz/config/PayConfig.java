package com.gxc.sldz.config;

import com.gxc.sldz.Utils.wxconfig;
import com.lly835.bestpay.config.WxPayConfig;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class PayConfig {



    @Bean
    public WxPayConfig wxPayConfig() {
        WxPayConfig wxPayConfig = new WxPayConfig();
        wxPayConfig.setMiniAppId(wxconfig.WX_LOGIN_APPID);
        wxPayConfig.setMchId(wxconfig.mchId);
        wxPayConfig.setMchKey(wxconfig.mchKey);
        wxPayConfig.setKeyPath(wxconfig.keyPath);
        wxPayConfig.setNotifyUrl(wxconfig.notifyUrl);
        return wxPayConfig;
    }

    @Bean
    public BestPayServiceImpl bestPayService(WxPayConfig WxPayConfig) {
        BestPayServiceImpl bestPayService = new BestPayServiceImpl();
        bestPayService.setWxPayConfig(WxPayConfig);
        return bestPayService;
    }



}
