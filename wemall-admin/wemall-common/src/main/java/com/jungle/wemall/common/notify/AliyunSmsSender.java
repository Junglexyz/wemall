package com.jungle.wemall.common.notify;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class AliyunSmsSender implements SmsSender{

    private final Log logger = LogFactory.getLog(AliyunSmsSender.class);

    public IAcsClient getSender() {
        return sender;
    }

    public void setSender(DefaultAcsClient sender) {
        this.sender = sender;
    }

    private IAcsClient sender;
    @Override
    public SmsResult sendVerifyCode(String phone, String param) {
        SmsResult result = sendWithTemplate(phone, param, "立刻U", "SMS_177246215");
        return result;
    }

    @Override
    public SmsResult sendMessage(String phone, String param){
        SmsResult result = sendWithTemplate(phone, param, "立刻U", "SMS_177246215");
        return result;
    }
    @Override
    public SmsResult sendWithTemplate(String phone, String param, String signName, String templateCode) {
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", signName);
        request.putQueryParameter("TemplateCode", templateCode);
        request.putQueryParameter("TemplateParam", param);
        try {
            CommonResponse response = sender.getCommonResponse(request);
            System.out.println(response);
            SmsResult smsResult = new SmsResult();
            smsResult.setSuccessful(true);
            smsResult.setResult(response);
            return smsResult;
        } catch (ServerException e) {
            logger.error(e.getMessage(), e);
        } catch (ClientException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }
}
