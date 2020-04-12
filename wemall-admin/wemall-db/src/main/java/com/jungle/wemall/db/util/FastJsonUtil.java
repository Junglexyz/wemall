package com.jungle.wemall.db.util;

import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * @description: alibaba fastJson工具类
 * @author: jungle
 * @date: 2020-02-25 00:06
 */
public class FastJsonUtil {
    /**
     * 解析String返回String
     * @param body
     * @param key
     * @return
     */
    public static String getString(String body, String key){
        JSONObject  jsonObject = JSONObject.parseObject(body);
        if(jsonObject.containsKey(key)){
            return jsonObject.getString(key);
        }
        return null;
    }

    /**
     * 解析String返回Integer
     * @param body
     * @param key
     * @return
     */
    public static Integer getInteger(String body, String key){
        JSONObject  jsonObject = JSONObject.parseObject(body);
        if(jsonObject.containsKey(key)){
            return jsonObject.getInteger(key);
        }
        return null;
    }

    /**
     * 解析String返回Integer
     * @param body
     * @param key
     * @return
     */
    public static List getList(String body, String key){
        JSONObject  jsonObject = JSONObject.parseObject(body);
        if(jsonObject.containsKey(key)){
            return (List)jsonObject.get(key);
        }
        return null;
    }

    /**
     * 解析String返回Integer
     * @param body
     * @param key
     * @return
     */
    public static Map getMap(String body, String key){
        JSONObject  jsonObject = JSONObject.parseObject(body);
        if(jsonObject.containsKey(key)){
            return (Map<String, Object>)jsonObject.get(key);
        }
        return null;
    }
}
