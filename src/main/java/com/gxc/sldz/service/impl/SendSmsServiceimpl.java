package com.gxc.sldz.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.gxc.sldz.service.SendSmsService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Mmap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.aliyun.tea.*;
import com.aliyun.dysmsapi20170525.*;
import com.aliyun.dysmsapi20170525.models.*;
import com.aliyun.teaopenapi.*;
import com.aliyun.teaopenapi.models.*;

import java.util.HashMap;
import java.util.Map;


/**
 * 发送sms serviceimpl
 *
 * @author Achin
 * @date 2021-08-18 15:29:49
 */
@Slf4j
@Service
public class SendSmsServiceimpl implements SendSmsService {


    /**
     * 访问密钥id
     */// 这里采用 注入的方式传递参数
    @Value("${aliyun.sms.accessKeyID}")
    private String accessKeyID;
    /**
     * 访问密钥的秘密
     */
    @Value("${aliyun.sms.accessKeySecret}")
    private String accessKeySecret;


    /**
     * 使用AK&SK初始化账号Client
     * @param accessKeyId
     * @param accessKeySecret
     * @return Client
     * @throws Exception
     */
    public static com.aliyun.dysmsapi20170525.Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
        Config config = new Config()
                // 您的AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 您的AccessKey Secret
                .setAccessKeySecret(accessKeySecret);
        // 访问的域名
        config.endpoint = "dysmsapi.aliyuncs.com";
        return new com.aliyun.dysmsapi20170525.Client(config);
    }
    /**
     * 成功进入
     *
     * @param phone     接受者目标手机号码
     * @param name      接受者的名称
     * @param agentname 他的下级名称
     * @param num       他的下级的名称
     * @return boolean
     */
    @SneakyThrows
    @Override
    public boolean EnteredSuccess(String phone, String name, String agentname, String num) {

        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        params.put("agentname", agentname);
        params.put("num", num);
        com.aliyun.dysmsapi20170525.Client client = SendSmsServiceimpl.createClient(accessKeyID, accessKeySecret);
        SendSmsRequest sendSmsRequest = new SendSmsRequest()
                .setPhoneNumbers(phone)
                .setSignName("私脸定治")
                .setTemplateCode("SMS_222470389")
                .setTemplateParam(JSON.toJSONString(params));
        // 复制代码运行请自行打印 API 的返回值
        SendSmsResponse sendSmsResponse =  client.sendSms(sendSmsRequest);
        if (sendSmsResponse.body.getCode() == "ok"){
            log.info("短信发送成功，接受者："+phone);
            return true;
        }
        return false;
    }

    @SneakyThrows
    @Override
    public boolean LoginConfirmationVerificationCode(String phone, String code) {
        Map<String, Object> params = new HashMap<>();
        params.put("code", code);

        com.aliyun.dysmsapi20170525.Client client = SendSmsServiceimpl.createClient(accessKeyID, accessKeySecret);
        SendSmsRequest sendSmsRequest = new SendSmsRequest()
                .setPhoneNumbers(phone)
                .setSignName("私脸定治")
                .setTemplateCode("SMS_190965006")
                .setTemplateParam(JSON.toJSONString(params));
        // 复制代码运行请自行打印 API 的返回值
        SendSmsResponse sendSmsResponse =  client.sendSms(sendSmsRequest);
        if (sendSmsResponse.body.getCode() == "ok"){
            log.info("短信发送成功，接受者："+phone);
            return true;
        }
        return false;
    }


    @SneakyThrows
    @Override
    public boolean ModifyPasswordVerificationCode(String phone, String code) {
        Map<String, Object> params = new HashMap<>();
        params.put("code", code);

        com.aliyun.dysmsapi20170525.Client client = SendSmsServiceimpl.createClient(accessKeyID, accessKeySecret);
        SendSmsRequest sendSmsRequest = new SendSmsRequest()
                .setPhoneNumbers(phone)
                .setSignName("私脸定治")
                .setTemplateCode("SMS_190965003")
                .setTemplateParam(JSON.toJSONString(params));
        // 复制代码运行请自行打印 API 的返回值
        SendSmsResponse sendSmsResponse =  client.sendSms(sendSmsRequest);
        if (sendSmsResponse.body.getCode() == "ok"){
            log.info("短信发送成功，接受者："+phone);
            return true;
        }
        return false;
    }


}
