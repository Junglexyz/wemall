package com.jungle.wemall.wx.controller;

import com.jungle.wemall.common.notify.NotifyService;
import com.jungle.wemall.common.notify.SmsResult;
import com.jungle.wemall.common.service.CaptchaCodeManager;
import com.jungle.wemall.common.util.CharUtil;
import com.jungle.wemall.db.util.FastJsonUtil;
import com.jungle.wemall.wx.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 小程序端权限验证
 * @author: jungle
 * @date: 2020-02-25 10:09
 */
@RestController
@RequestMapping("/wx/auth")
public class WxAuthController {
    @Autowired
    private NotifyService notifyService;

    /**
     * 获取验证码
     * @param body
     * @return
     */
    @PostMapping("/getVerify")
    public Object getVerify(@RequestBody String body){
        String mobile = FastJsonUtil.getString(body, "mobile");
        String code = CharUtil.getRandomNum(6);
        String param = "{\"code\":"+code+"}";
        boolean successful = CaptchaCodeManager.addToCache(mobile, code);
        SmsResult result = null;
        if (successful) {
            result = notifyService.sendVerifyCode(mobile, param);
        }
        return result.isSuccessful() ? ResponseUtil.ok() : ResponseUtil.fail();
    }

    @PostMapping("/loginByCaptcha")
    public Object loginByCaptcha(@RequestBody String body){
        String mobile = FastJsonUtil.getString(body, "mobile");
        String captcha = FastJsonUtil.getString(body, "captcha");
        String code = CaptchaCodeManager.getCachedCaptcha(mobile);
        System.out.println(captcha +" "+ code);
        if(null == code){
            return ResponseUtil.fail(1001, "验证码已过期");
        }
        System.out.println(captcha != code);
        System.out.println(captcha.equals(code));
        System.out.println(!captcha.equals(code));
        System.out.println(!(captcha.equals(code)));
        if(!captcha.equals(code)){
            return ResponseUtil.fail(1002, "验证码输入错误！");
        }
        if(captcha.equals(code)){
            CaptchaCodeManager.removeCaptcha(mobile);
            return ResponseUtil.ok();
        }
        return ResponseUtil.fail();
    }
}
