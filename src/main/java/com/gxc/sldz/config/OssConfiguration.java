package com.gxc.sldz.config;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class OssConfiguration {


    @Value("${aliyun.oss.endpoint}")
    private String endPoint;
    @Value("${aliyun.oss.accessKeyId}")
    private String accessKeyId;
    @Value("${aliyun.oss.accessKeySecret}")
    private String accessKeySecret;
    @Value("${aliyun.oss.filehost}")
    private String fileHost;
    @Value("${aliyun.oss.bucketName}")
    private String bucketName;

    @Value("${aliyun.oss.policy.expire}")
    private long expire;
    @Value("${aliyun.oss.policy.maxSize}")
    private String maxSize;
    @Value("${aliyun.oss.policy.dir.prefix}")
    private String dir;

}
