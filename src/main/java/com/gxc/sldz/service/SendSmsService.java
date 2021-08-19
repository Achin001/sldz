package com.gxc.sldz.service;

public interface SendSmsService {

    /**
     * 新加入客户通知提醒
     *
     * @param phone     接受者目标手机号码
     * @param name      接受者的名称
     * @param agentname 他的下级名称
     * @param num       他的下级的名称
     * @return boolean
     */
    boolean EnteredSuccess(String phone,
                           String name,
                           String agentname,
                           String num);



    /**
     * 登录确认验证码
     *
     * @param phone     接受者目标手机号码
     * @param code      验证码
     * @return boolean
     */
    boolean LoginConfirmationVerificationCode(String phone,String code);


    /**
     * 修改密码验证码
     *
     * @param phone     接受者目标手机号码
     * @param code      验证码
     * @return boolean
     */
    boolean ModifyPasswordVerificationCode(String phone,String code);

}
