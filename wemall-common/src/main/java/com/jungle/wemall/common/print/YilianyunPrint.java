package com.jungle.wemall.common.print;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.client.RestTemplate;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @description: 易联云小票打印
 * @author: jungle
 * @date: 2020-03-20 19:38
 */
public class YilianyunPrint implements PrintI{
    private String clientId;
    private String clientSecret;
    private String machineCode;
    private RestTemplate restTemplate = new RestTemplate();

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getMachineCode() {
        return machineCode;
    }

    public void setMachineCode(String machineCode) {
        this.machineCode = machineCode;
    }

    /**
     * 自有应用服务
     */

    @Override
    public String getAccessToken() {
        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
        Map hashMap = new HashMap();
        hashMap.put("client_id", this.clientId);
        hashMap.put("grant_type", "client_credentials");
        hashMap.put("sign", getSin(timestamp));
        hashMap.put("scope", "all");
        hashMap.put("timestamp", timestamp);
        hashMap.put("id", getuuid());
        String result = restTemplate.postForObject(ApiConst.MAIN_HOST_URL + ApiConst.GET_TOKEN, hashMap, String.class);
        JSONObject jsonResult = JSONObject.parseObject(result);
        JSONObject body = JSONObject.parseObject(jsonResult.getString("body"));
        String token = body.getString("access_token");
        String refreshToken = body.getString("refresh_token");
        return token;
    }

    /**
     * 添加终端授权 开放应用服务模式不需要此接口 ,自有应用服务模式所需参数
     */
    @Override
    public String addPrinter(String access_token) {
        String timestamp = String.valueOf(LocalDateTime.now());
        Map hashMap = new HashMap();
        hashMap.put("client_id", this.clientId);
        hashMap.put("machine_code", this.machineCode);
        hashMap.put("msign", getSin(timestamp));
        hashMap.put("access_token", access_token);
        hashMap.put("sign", getSin(timestamp));
        hashMap.put("id", getuuid());
        hashMap.put("timestamp", timestamp);
        String result = restTemplate.postForObject(ApiConst.MAIN_HOST_URL + ApiConst.API_ADD_PRINTER, hashMap, String.class);
        return result;
    }

    /**
     * 打印
     *
     * @param access_token 授权的token 必要参数
     * @param content      打印内容(需要urlencode)
     * @return
     */
    @Override
    public String print(String access_token, String content) {
        String timestamp = String.valueOf(LocalDateTime.now());
        Map hashMap = new HashMap();
        hashMap.put("client_id", this.clientId);
        hashMap.put("access_token", access_token);
        hashMap.put("machine_code", this.machineCode);
        hashMap.put("content", content);
        hashMap.put("origin_id", "2");
        hashMap.put("sign", getSin(timestamp));
        hashMap.put("id", getuuid());
        hashMap.put("timestamp", timestamp);
        String result = restTemplate.postForObject(ApiConst.MAIN_HOST_URL + ApiConst.API_PRINT, hashMap, String.class);
        return result;
    }

    private String getuuid() {
        return UUID.randomUUID().toString();
    }

    public String getSin(String timestamp) {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.clientId);
            stringBuilder.append(timestamp);
            stringBuilder.append(this.clientSecret);
            return getMd5(stringBuilder.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * @param str
     * @return
     * @Description: 32位小写MD5
     */
    public String getMd5(String str) {
        String reStr = "";
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(str.getBytes());
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : bytes) {
                int bt = b & 0xff;
                if (bt < 16) {
                    stringBuffer.append(0);
                }
                stringBuffer.append(Integer.toHexString(bt));
            }
            reStr = stringBuffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return reStr;
    }
}
