package com.jungle.wemall.admin.controller;

import com.jungle.wemall.common.util.ResponseUtil;
import com.jungle.wemall.db.pojo.WemallCoupon;
import com.jungle.wemall.db.service.WemallCouponService;
import com.jungle.wemall.db.util.FastJsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description: 优惠券管理
 * @author: jungle
 * @date: 2020-02-27 12:07
 */
@RestController
@RequestMapping("/admin/coupon")
public class AdminCouponController {
    @Autowired
    private WemallCouponService wemallCouponService;

    @PostMapping("/create")
    public Object createCoupon(@RequestBody WemallCoupon wemallCoupon)  {
        int result = wemallCouponService.insertCoupon(wemallCoupon);
        if(result > 0){
            return ResponseUtil.ok();
        }
        return ResponseUtil.fail();
    }

    @PostMapping("/update")
    public Object updateCoupon(@RequestBody WemallCoupon wemallCoupon)  {
        int result = wemallCouponService.updateCoupon(wemallCoupon);
        if(result > 0){
            return ResponseUtil.ok();
        }
        return ResponseUtil.fail();
    }

    @PostMapping("/delete")
    public Object deleteCoupon(@RequestBody String body)  {
        Integer couponId = FastJsonUtil.getInteger(body, "couponId");
        int result = wemallCouponService.deleteCoupon(couponId);
        if(result > 0){
            return ResponseUtil.ok();
        }
        return ResponseUtil.fail();
    }

    @PostMapping("/list")
    public Object listCoupon()  {
        List<WemallCoupon> listCoupon = wemallCouponService.listCoupon(null);
        if(listCoupon != null){
            return ResponseUtil.okList(listCoupon);
        }
        return ResponseUtil.fail();
    }
}
