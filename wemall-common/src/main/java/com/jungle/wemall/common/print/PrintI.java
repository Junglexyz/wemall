package com.jungle.wemall.common.print;

import java.util.Map;

/**
 * @description: 打印接口
 * @author: jungle
 * @date: 2020-03-21 20:43
 */
public interface PrintI {
    String getAccessToken();
    String addPrinter(String access_token);
    String print(String access_token, String content);
}
