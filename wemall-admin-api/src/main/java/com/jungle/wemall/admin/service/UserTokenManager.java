package com.jungle.wemall.admin.service;

import com.jungle.wemall.admin.util.JwtHelper;

/**
 * @author : jungle
 * 维护用户token
 */
public class UserTokenManager {
	public static String generateToken(String id) {
        JwtHelper jwtHelper = new JwtHelper();
        return jwtHelper.createToken(id);
    }
    public static String getUserId(String token) {
    	JwtHelper jwtHelper = new JwtHelper();
    	String userId = jwtHelper.verifyTokenAndGetUserId(token);
    	if(userId == null || userId.length() == 0){
    		return null;
    	}
        return userId;
    }
}
