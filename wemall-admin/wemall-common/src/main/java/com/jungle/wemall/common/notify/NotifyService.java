package com.jungle.wemall.common.notify;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NotifyService {
    public SmsSender getSmsSender() {
        return smsSender;
    }

    public void setSmsSender(SmsSender smsSender) {
        this.smsSender = smsSender;
    }

    private SmsSender smsSender;

    public List<Map<String, String>> getSmsTemplate() {
        return smsTemplate;
    }

    public void setSmsTemplate(List<Map<String, String>> smsTemplate) {
        this.smsTemplate = smsTemplate;
    }

    private List<Map<String, String>> smsTemplate = new ArrayList<>();

    public SmsResult sendVerifyCode(String phone, String param){
        SmsResult result = smsSender.sendVerifyCode(phone, param);
        return result;
    }

}
