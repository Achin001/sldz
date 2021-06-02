package com.gxc.sldz.Utils;

import com.aliyun.oss.OSSClient;
import com.diboot.core.vo.JsonResult;
import com.gxc.sldz.config.OssConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class AliyunOssUtil {


    @Autowired
    private OssConfiguration config;


    public JsonResult deleteObject(String imageName) {
        String endPoint = config.getEndPoint();
        String keyId = config.getAccessKeyId();
        String keySecret = config.getAccessKeySecret();
        String bucketName = config.getBucketName();
        String fileHost = config.getFileHost();
        //阿里云文件上传客户端
        OSSClient client = new OSSClient(endPoint, keyId, keySecret);
        try {
            // 删除Object.
            client.deleteObject(bucketName, imageName);
        } catch (Exception e) {
            e.printStackTrace();
            return  JsonResult.OK().data(false).msg("删除失败");
        } finally {
            client.shutdown();
        }
        return  JsonResult.OK().data(true).msg("删除成功");

    }

}
