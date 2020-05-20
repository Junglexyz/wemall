package com.jungle.wemall.common.notify;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaTemplateMessage;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @description: 微信小程序订阅消息发送
 * @author: jungle
 * @date: 2020-03-07 14:16
 */
public class WxSubscribeMessageSender {
    private final String ACCESS_TOLEN_URL = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=";
    private RestTemplate restTemplate = new RestTemplate();
    @Autowired
    private WxMaService wxMaService;
    public String getAccessToken() {
        String accessToken = null;
        try {
            accessToken = wxMaService.getAccessToken();
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    private String accessToken;
    public String sendMsg(String touser, String subscribeId, Map<String, TemplateData> parms, String page) {


        WxMssVo wxMssVo = new WxMssVo();
        wxMssVo.setTouser(touser);
        wxMssVo.setPage(page);
        wxMssVo.setTemplate_id(subscribeId);
        wxMssVo.setData(parms);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(ACCESS_TOLEN_URL+getAccessToken(), wxMssVo, String.class);

        return responseEntity.getBody();
    }
}
