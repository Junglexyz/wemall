package com.jungle.wemall.common.notify.config;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.jungle.wemall.common.notify.AliyunSmsSender;
import com.jungle.wemall.common.notify.NotifyService;
import com.jungle.wemall.common.notify.SmsSender;
import com.jungle.wemall.common.notify.WxSubscribeMessageSender;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(NotifyProperties.class)
public class NotifyAutoConfiguration {

    private final NotifyProperties properties;
//    private Object WxSubscribeMessageSender;

    public NotifyAutoConfiguration(NotifyProperties properties) {
        this.properties = properties;
    }

    @Bean
    public NotifyService notifyService() {
        NotifyService notifyService = new NotifyService();

        /**
         * 阿里云短信服务
         */
        NotifyProperties.AliyunSms aliyunSmsConfig = properties.getAliyunsms();
        if (aliyunSmsConfig.isEnable()) {
            notifyService.setSmsSender(aliyunSmsSender());
//            notifyService.setSmsTemplate(aliyunSmsConfig.getTemplate());
        }

        NotifyProperties.Wx wxConfig = properties.getWx();
        if (wxConfig.isEnable()) {
            notifyService.setWxSubscribeMessageSender(wxSubscribeMessageSender());
            notifyService.setDeliverySubscribe(wxConfig.getDeliverySubscribeId());
            notifyService.setCheckSuccess(wxConfig.getCheckoutSuccess());
        }
        return notifyService;
    }


    /*@Bean
    public WxTemplateSender wxTemplateSender() {
        WxTemplateSender wxTemplateSender = new WxTemplateSender();
        return wxTemplateSender;
    }*/

    @Bean
    public WxSubscribeMessageSender wxSubscribeMessageSender() {
        WxSubscribeMessageSender wxSubscribeMessageSender = new WxSubscribeMessageSender();
        return wxSubscribeMessageSender;
    }

    /**
     * 阿里云短信服务
     * @return
     */
    @Bean
    public SmsSender aliyunSmsSender() {
        NotifyProperties.AliyunSms aliyunSmsConfig = properties.getAliyunsms();
        AliyunSmsSender smsSender = new AliyunSmsSender();
        DefaultProfile profile = DefaultProfile.getProfile(aliyunSmsConfig.getLocation(), aliyunSmsConfig.getAccessKeyId(), aliyunSmsConfig.getSecret());
        smsSender.setSender(new DefaultAcsClient(profile));
        smsSender.setSignName(aliyunSmsConfig.getSignName());
        smsSender.setTemplateId(aliyunSmsConfig.getTemplateId());
        return smsSender;
    }
}
