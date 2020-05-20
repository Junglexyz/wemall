package com.jungle.wemall.common.print;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @description: 打印小票服务
 * @author: jungle
 * @date: 2020-03-21 18:53
 */
@Service
public class PrintService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    private PrintI printI;
    public PrintI getPrintI() {
        return printI;
    }

    public void setPrintI(PrintI printI) {
        this.printI = printI;
    }

    public String getAccessToken(){
        String token = stringRedisTemplate.opsForValue().get("printerToken");
        if(token == null){
            token = printI.getAccessToken();
            // 易联云token接口每日最多可调用 这里将获取到的token保存在redis中 24小时刷新一次
            stringRedisTemplate.opsForValue().set("printerToken",token,24, TimeUnit.HOURS);
        }
        return token;
    }

    /**
     * 打印小票
     * @param content
     * @return
     */
    public String print(String content){
        String accessToken = getAccessToken();
        return printI.print(accessToken, content);
    }

}
