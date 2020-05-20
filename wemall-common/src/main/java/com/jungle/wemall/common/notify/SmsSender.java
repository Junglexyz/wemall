package com.jungle.wemall.common.notify;

public interface SmsSender {
    /**
     * 发送验证码
     * @return
     */
    SmsResult sendVerifyCode(String phone, String param);
    /**
     * 发送通知
     * @return
     */
    SmsResult sendMessage(String phone, String param);
    /**
     * 调用阿里云短信api发送信息
     * @return
     */
    SmsResult sendWithTemplate(String phone, String code);


}
