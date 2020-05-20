package com.jungle.wemall.wx.service;


import com.jungle.wemall.wx.util.JwtHelper;

/**
 * @description 维护用户token
 * @author Jungle
 */
public class UserTokenManager {
	public static String generateToken(Integer id) {
        JwtHelper jwtHelper = new JwtHelper();
        return jwtHelper.createToken(id);
    }
    public static Integer getUserId(String token) {
    	JwtHelper jwtHelper = new JwtHelper();
    	Integer userId = jwtHelper.verifyTokenAndGetUserId(token);
    	if(userId == null || userId == 0){
    		return null;
    	}
        return userId;
    }
}
