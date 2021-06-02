package com.gxc.sldz.controller.OssController;


import com.diboot.core.vo.JsonResult;
import com.gxc.sldz.Utils.AliyunOssUtil;
import com.gxc.sldz.service.OssServer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"OSS接口"})
@RestController
@RequestMapping("admin")
@Slf4j
public class OssController {




    @Autowired
    OssServer OssServer;

    @Autowired
    AliyunOssUtil ossUtil;

    @ApiOperation(value = "oss上传签名生成")
    @GetMapping(value = "policy")
    @ResponseBody
    public JsonResult policy() {
        return OssServer.policy();
    }

    @ApiOperation(value = "删除文件")
    @DeleteMapping("OssDeleteFile")
    public JsonResult OssDeleteFile(String imageName) {
        return ossUtil.deleteObject(imageName);
    }


}
