package com.gxc.sldz.controller.API;


import com.gxc.sldz.Utils.wxUtil;
import com.gxc.sldz.service.wxUtilServer;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

@Api(tags = {"获取小程序二维码接口"})
@RestController
@RequestMapping("api/getUnlimited")
@Slf4j
public class getUnlimited {

    @Autowired
    wxUtilServer wxUtilServer;

    @GetMapping(value = "/")
    public void downloadQrCode(@RequestParam(value = "random") String random,
                               HttpServletResponse response) throws Exception {

//获取AccessToken
        String accessToken = wxUtilServer.getAccessToken();
        byte[] qrCodeBytes = null;
        Map<String, Object> paraMap = new HashMap();
        String url="https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token="+accessToken;
        //二维码携带参数 不超过32位 参数类型必须是字符串
        paraMap.put("scene", random);  //存入的参数
        paraMap.put("width", 280);  //尺寸 px最小280
        paraMap.put("is_hyaline", true);//为 true 时，生成透明底色的小程序


        qrCodeBytes=wxUtil.getminiqrQr(url,paraMap);
        response.setContentType("image/jpg");
        // 写入response的输出流中
        OutputStream stream = response.getOutputStream();
        stream.write(qrCodeBytes);
        stream.flush();
        stream.close();
    }


}
