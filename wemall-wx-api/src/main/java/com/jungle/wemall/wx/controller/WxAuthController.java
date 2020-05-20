package com.jungle.wemall.wx.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.jungle.wemall.common.notify.NotifyService;
import com.jungle.wemall.common.notify.SmsResult;
import com.jungle.wemall.common.service.CaptchaCodeManager;
import com.jungle.wemall.common.util.CharUtil;
import com.jungle.wemall.common.util.ResponseUtil;
import com.jungle.wemall.db.pojo.WemallCoupon;
import com.jungle.wemall.db.pojo.WemallCouponAccept;
import com.jungle.wemall.db.pojo.WemallUser;
import com.jungle.wemall.db.service.WemallCouponService;
import com.jungle.wemall.db.service.WemallUserService;
import com.jungle.wemall.db.util.FastJsonUtil;
import com.jungle.wemall.wx.service.UserTokenManager;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @Autowired
    private WemallUserService wemallUserService;
    @Autowired
    private WxMaService wxMaService;
    @Autowired
    private WemallCouponService wemallCouponService;
    /**
     * 获取验证码
     * @param body
     * @return
     */
    @PostMapping("/getVerify")
    public Object getVerify(@RequestBody String body){
        String mobile = FastJsonUtil.getString(body, "mobile");
        System.out.println(mobile);
        String code = CharUtil.getRandomNum(6);
        System.out.println(code);
        String param = "{\"code\":"+code+"}";
        boolean successful = CaptchaCodeManager.addToCache(mobile, code);
        SmsResult result = null;
        if (successful) {
            result = notifyService.sendVerifyCode(mobile, param);
        }
        return result.isSuccessful() ? ResponseUtil.ok() : ResponseUtil.fail();
    }

    /**
     * 手机号登录
     * @param body
     * @return
     */
    @PostMapping("/loginByCaptcha")
    @Transactional
    public Object loginByCaptcha(@RequestBody String body){
        String mobile = FastJsonUtil.getString(body, "mobile");
        String captcha = FastJsonUtil.getString(body, "captcha");
        String openId = FastJsonUtil.getString(body, "openid");
        String code = CaptchaCodeManager.getCachedCaptcha(mobile);
        if(code == null){
            return ResponseUtil.fail(1001, "验证码已过期");
        }
        if(!captcha.equals(code)){
            return ResponseUtil.fail(1002, "验证码输入错误！");
        }
        if(captcha.equals(code)){
            CaptchaCodeManager.removeCaptcha(mobile);
            //将电话号码转为十六进制作为id
            String hexId = Long.toHexString(Long.parseLong(mobile));
            WemallUser user = wemallUserService.findUserByMobile(mobile);
            if(user != null){
                // 老用户登录
                // 更新openId
                user.setOpenId(openId);
                wemallUserService.update(user);
                String token = UserTokenManager.generateToken(user.getUserId());
                user.setToken(token);
                return ResponseUtil.ok(user);
            }else{
                // 新用户注册
                WemallUser userInfo = new WemallUser();
                userInfo.setUsername(hexId);
                userInfo.setMobile(mobile);
                userInfo.setOpenId(openId);
                // 插入成功返回userId
                int insertResult = wemallUserService.insert(userInfo);
                System.out.println("userId: "+userInfo.getUserId());
                // 注册送满减券
                // 获取满减优惠券
                List<WemallCoupon> wemallCoupons = wemallCouponService.listCoupon("1");
                for(WemallCoupon coupon: wemallCoupons){
                    WemallCouponAccept couponAccept = new WemallCouponAccept();
                    couponAccept.setUserId(userInfo.getUserId());
                    couponAccept.setCouponId(coupon.getCouponId());
                    couponAccept.setMobile(mobile);
                    couponAccept.setTitle(coupon.getTitle());
                    couponAccept.setTypes(coupon.getTypes());
                    couponAccept.setTargetMoney(coupon.getTargetMoney());
                    couponAccept.setDiscountMoney(coupon.getDiscountMoney());
                    couponAccept.setStatus("1");
                    couponAccept.setEndTime(coupon.getEndTime());
                    wemallCouponService.acceptCoupon(couponAccept);
                }
                if(insertResult > 0){
                    String token = UserTokenManager.generateToken(userInfo.getUserId());
                    userInfo.setToken(token);
                    // result返回自增id
                    userInfo.setUserId(userInfo.getUserId());
                    return ResponseUtil.ok(userInfo);
                }
            }
        }
        return ResponseUtil.fail();
    }

    /**
     * 获取微信小程序用户openid
     * @param body
     * @return
     */
    @PostMapping("/openid")
    public Object getOpenId(@RequestBody String body){
        String code = FastJsonUtil.getString(body,"code");
        WxMaJscode2SessionResult sessionInfo = null;
        try {
            sessionInfo = wxMaService.getUserService().getSessionInfo(code);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        if(sessionInfo != null){
            return ResponseUtil.ok(sessionInfo.getOpenid());
        }
        return ResponseUtil.fail();
    }
}
