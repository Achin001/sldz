package com.gxc.sldz.service.impl;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.diboot.core.vo.JsonResult;
import com.gxc.sldz.config.OssConfiguration;
import com.gxc.sldz.service.OssServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;



@Slf4j
@Service
public class OssServerImpl  implements OssServer {

    @Autowired
    OssConfiguration config;


    @Override
    public JsonResult policy() {

        String endPoint = config.getEndPoint();
        String keyId = config.getAccessKeyId();
        String keySecret = config.getAccessKeySecret();

        //阿里云文件上传客户端
        OSSClient client = new OSSClient(endPoint, keyId, keySecret);

        // 请填写您的 bucketname 。
//            String host = "https://" + ALIYUN_OSS_BUCKET_NAME + "." + ALIYUN_OSS_ENDPOINT; // host的格式为 bucketname.endpoint
        String host = "https://" + config.getBucketName() + "." + "oss-cn-shanghai.aliyuncs.com";
        /**
         * 每天生成一个目录
         */
        String format = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String dir = config.getDir()+format+"/"; // 用户上传文件时指定的前缀。
        Map<String, String> respMap = null;
        try {
            long expireTime = config.getExpire();
            long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
            Date expiration = new Date(expireEndTime);
            // PostObject请求最大可支持的文件大小为5 GB，即CONTENT_LENGTH_RANGE为5*1024*1024*1024。
            PolicyConditions policyConds = new PolicyConditions();
            policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 50*1024*1024);
            policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);

            String postPolicy = client.generatePostPolicy(expiration, policyConds);
            byte[] binaryData = postPolicy.getBytes("utf-8");
            String encodedPolicy = BinaryUtil.toBase64String(binaryData);
            String postSignature = client.calculatePostSignature(postPolicy);

            respMap = new LinkedHashMap<String, String>();
            respMap.put("accessid", keyId);
            respMap.put("policy", encodedPolicy);
            respMap.put("signature", postSignature);
            respMap.put("dir", dir);
            respMap.put("host", host);
            respMap.put("expire", String.valueOf(expireEndTime / 1000));
        } catch (Exception e) {
            log.error("签名生成失败", e);
        }

        return JsonResult.OK().data(respMap);
    }
}
