package com.gxc.sldz.service.impl;

import com.diboot.core.util.BeanUtils;
import com.gxc.sldz.entity.SldzBonuSsetting;
import com.gxc.sldz.mapper.SldzBonuSsettingMapper;
import com.gxc.sldz.service.SldzBonuSsettingService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
* 奖励金设置相关Service实现
* @author Achin
* @version 1.0
* @date 2021-06-09
 * Copyright © MyCompany
*/
@Service
@Slf4j
public class SldzBonuSsettingServiceImpl extends BaseCustomServiceImpl<SldzBonuSsettingMapper, SldzBonuSsetting> implements SldzBonuSsettingService {



//    @Override
//    public void checkToken(HttpServletRequest request) {
//        String token2 = request.getHeader("token2");
//        // header中不存在token
//        if (StringUtils.isBlank(token2)) {
//            token2 = request.getParameter(token2);
//            // parameter中也不存在token
//            if (StringUtils.isBlank(token2)) {
//                //报错
////                throw new RRException(ResponseCode.ILLEGAL_ARGUMENT.getMsg(),ResponseCode.ILLEGAL_ARGUMENT.getCode());
//            }
//        }
//
//        //如果redis 里面不存在token 就报错
////        if (!redisUtil.exists(token)) {
////            throw new RRException(ResponseCode.REPETITIVE_OPERATION.getMsg(),ResponseCode.REPETITIVE_OPERATION.getCode());
////        }
//
//        //删除失败页报错
////        Boolean del = redisUtil.del(token);
////        if (!del) {
////            throw new RRException(ResponseCode.REPETITIVE_OPERATION.getMsg(),ResponseCode.REPETITIVE_OPERATION.getCode());
////        }
//
//    }
//

}
