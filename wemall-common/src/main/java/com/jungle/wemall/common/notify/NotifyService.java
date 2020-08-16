package com.jungle.wemall.common.notify;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotifyService {
    private SmsSender smsSender;
    private WxSubscribeMessageSender wxSubscribeMessageSender;
    private List<Map<String, String>> smsTemplate = new ArrayList<>();
    /**
    * 发货成功订阅消息id
    */
    private String deliverySubscribe;

    public String getCheckSuccess() {
        return checkSuccess;
    }

    public void setCheckSuccess(String checkSuccess) {
        this.checkSuccess = checkSuccess;
    }
    /**
    * 下单成功订阅消息通知id
    */
    private String checkSuccess;

    public String getDeliverySubscribe() {
        return deliverySubscribe;
    }

    public void setDeliverySubscribe(String deliverySubscribe) {
        this.deliverySubscribe = deliverySubscribe;
    }


    public SmsSender getSmsSender() {
        return smsSender;
    }
    public void setSmsSender(SmsSender smsSender) {
        this.smsSender = smsSender;
    }

    public WxSubscribeMessageSender getWxSubscribeMessageSender() {
        return wxSubscribeMessageSender;
    }

    public void setWxSubscribeMessageSender(WxSubscribeMessageSender wxSubscribeMessageSender) {
        this.wxSubscribeMessageSender = wxSubscribeMessageSender;
    }

    public void setSmsTemplate(List<Map<String, String>> smsTemplate) {
        this.smsTemplate = smsTemplate;
    }
    public List<Map<String, String>> getSmsTemplate() {
        return smsTemplate;
    }

    public SmsResult sendVerifyCode(String phone, String param){
        SmsResult result = smsSender.sendVerifyCode(phone, param);
        return result;
    }

    public String sendSubscribeMessage(String touser, Map params, String page, String subscribe){
        String result = wxSubscribeMessageSender.sendMsg(touser, subscribe, params, page);
        return result;
    }

    /**
     * 下单成功通知
     * @return
     */
    public String sendCheckSuccessMessage(Map<String, String> params){
        Map<String, TemplateData> data = new HashMap<>(8);
        data.put("character_string5", new TemplateData(params.get("orderId")));
        data.put("amount7", new TemplateData(params.get("payMoney")));
        data.put("thing2", new TemplateData(params.get("orderType")));
        data.put("thing3", new TemplateData("双安文承维生活超市online"));
        data.put("thing8", new TemplateData(params.get("tip")));
        String page = "";
        // now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"))
        String result = this.sendSubscribeMessage(params.get("openId"), data, page, this.checkSuccess);
        return result;
    }

    /**
     * 发货成功通知
     * @param touser
     * @return
     */
    public String sendDeliveryMessage(String touser, String orderId, String packupCode, String deliveryWay){
        Map<String, TemplateData> data = new HashMap<>(8);
        data.put("number10", new TemplateData(packupCode));
        data.put("character_string1", new TemplateData(orderId));
        data.put("phone_number5", new TemplateData("18789197915"));
        data.put("thing6", new TemplateData("双安镇街道双安文承维生活超市"));
        data.put("thing11", new TemplateData("1".equals(deliveryWay) ? "即将配送请注意查收" : "凭提货单号取货"));
        String page = "";
        String result = this.sendSubscribeMessage(touser, data, page,this.deliverySubscribe);
        return result;
    }

}
