package com.jungle.wemall.common.notify;

import java.util.Map;

/**
 * @description:
 * @author: jungle
 * @date: 2020-03-07 12:13
 */
public class WxMssVo {
    /**
     * 用户openid
     */
    private String touser;
    /**
     * 订阅消息模版id
     */
    private String template_id;
    /**
     * 默认跳到小程序首页
     */
    private String page = "pages/index/index";
    private Map<String, TemplateData> data;

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public Map<String, TemplateData> getData() {
        return data;
    }

    public void setData(Map<String, TemplateData> data) {
        this.data = data;
    }
}

