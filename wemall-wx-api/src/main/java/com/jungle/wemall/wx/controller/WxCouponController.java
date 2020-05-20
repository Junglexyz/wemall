package com.jungle.wemall.wx.controller;

import com.jungle.wemall.common.util.ResponseUtil;
import com.jungle.wemall.db.pojo.WemallCoupon;
import com.jungle.wemall.db.pojo.WemallCouponAccept;
import com.jungle.wemall.db.service.WemallCouponService;
import com.jungle.wemall.db.util.FastJsonUtil;
import com.jungle.wemall.wx.annotation.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description: 优惠券
 * @author: jungle
 * @date: 2020-02-27 15:38
 */
@RestController
@RequestMapping("/wx/coupon")
public class WxCouponController {
    @Autowired
    private WemallCouponService wemallCouponService;

    @PostMapping("/list")
    public Object listCoupon(@LoginUser Integer userId) {
        if(userId == null){
            return ResponseUtil.unlogin();
        }
        List<WemallCoupon> listCoupon = wemallCouponService.noAccept(userId);
        if(listCoupon != null){
            return ResponseUtil.okList(listCoupon);
        }else{
            return ResponseUtil.fail(100001,"暂无可用优惠券");
        }
    }

    @PostMapping("/accept")
    public Object acceptCoupon(@RequestBody WemallCouponAccept wemallCouponAccept, @LoginUser Integer userId)  {
        if(userId == null){
            return ResponseUtil.unlogin();
        }
        int result = wemallCouponService.acceptCoupon(wemallCouponAccept);
        if (result > 0){
            return ResponseUtil.ok();
        }
        return ResponseUtil.fail();
    }

    @PostMapping("/getCouponByUser")
    public Object getCouponByUser(@LoginUser Integer userId)  {
        if(userId == null){
            return ResponseUtil.unlogin();
        }
        List<WemallCouponAccept> listCoupon = wemallCouponService.getCouponByUser(userId);
        if(listCoupon != null){
            return ResponseUtil.okList(listCoupon);
        }
        return ResponseUtil.fail();
    }
}
