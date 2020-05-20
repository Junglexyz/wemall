package com.jungle.wemall.common.notify.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "wemall.notify")
public class NotifyProperties {
    private Wx wx;
    private AliyunSms aliyunsms;

    public AliyunSms getAliyunsms(){
        return  aliyunsms;
    }
    public void setAliyunsms(AliyunSms aliyunsms){
        this.aliyunsms = aliyunsms;
    }

    public Wx getWx() {
        return wx;
    }

    public void setWx(Wx wx) {
        this.wx = wx;
    }

    /**
     * 阿里云短信服务
     */
    public static class AliyunSms {
        private boolean enable;
        private String accessKeyId;
        private String secret;
        private String location;

        public String getTemplateId() {
            return templateId;
        }

        public void setTemplateId(String templateId) {
            this.templateId = templateId;
        }

        public String getSignName() {
            return signName;
        }

        public void setSignName(String signName) {
            this.signName = signName;
        }

        //        private List<Map<String, String>> template = new ArrayList<>();
        private String templateId;
        private String signName;

        public boolean isEnable() {
            return enable;
        }

        public void setEnable(boolean enable) {
            this.enable = enable;
        }

        public String getAccessKeyId() {
            return accessKeyId;
        }

        public void setAccessKeyId(String accessKeyId) {
            this.accessKeyId = accessKeyId;
        }

        public String getSecret() {
            return secret;
        }

        public void setSecret(String secret) {
            this.secret = secret;
        }

        public String getLocation(){
            return location;
        }
        public void setLocation(String location){
            this.location = location;
        }

//        public List<Map<String, String>> getTemplate() {
//            return template;
//        }
//
//        public void setTemplate(List<Map<String, String>> template) {
//            this.template = template;
//        }
    }

    public static class Wx {
        private boolean enable;
        private List<Map<String, String>> template = new ArrayList<>();
        private String deliverySubscribeId;
        private String checkoutSuccess;

        public String getCheckoutSuccess() {
            return checkoutSuccess;
        }

        public void setCheckoutSuccess(String checkoutSuccess) {
            this.checkoutSuccess = checkoutSuccess;
        }

        public String getDeliverySubscribeId() {
            return deliverySubscribeId;
        }

        public void setDeliverySubscribeId(String deliverySubscribeId) {
            this.deliverySubscribeId = deliverySubscribeId;
        }

        public boolean isEnable() {
            return enable;
        }

        public void setEnable(boolean enable) {
            this.enable = enable;
        }

        public List<Map<String, String>> getTemplate() {
            return template;
        }

        public void setTemplate(List<Map<String, String>> template) {
            this.template = template;
        }
    }

}
