package com.jungle.wemall.wx.controller;

import com.jungle.wemall.common.util.FastJsonUtil;
import com.jungle.wemall.common.util.ResponseUtil;
import com.jungle.wemall.db.pojo.WemallAward;
import com.jungle.wemall.db.pojo.WemallCouponAccept;
import com.jungle.wemall.db.pojo.WemallPrize;
import com.jungle.wemall.db.service.WemallAwardService;
import com.jungle.wemall.db.service.WemallCouponService;
import com.jungle.wemall.wx.annotation.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 抽奖
 * @author: jungle
 * @date: 2020-06-03 23:48
 */
@RestController
@RequestMapping("/wx/award")
public class WxAwardController {
    @Autowired
    private WemallAwardService awardService;
    @Autowired
    private WemallCouponService couponService;
    /**
     * 查询是否有满足的抽奖活动
     * @return
     */
    @GetMapping("/get")
    public Object findAward(){
        WemallAward award = awardService.select();
        if(award != null){
            if(award.getDeleted()){
                return ResponseUtil.fail(1001, "活动已结束");
            }
            return ResponseUtil.ok(award);
        }else{
            return ResponseUtil.fail(-1,"暂时没有活动");
        }
    }

    /**
     * 生成奖品
     * @return
     */
    @PostMapping("/generate")
    public Object generate(@RequestBody String body, @LoginUser Integer userId){
        if(userId == null){
            return ResponseUtil.unlogin();
        }
        Integer awardId = FastJsonUtil.getInteger(body, "awardId");
        String userTel = FastJsonUtil.getString(body, "userTel");
        // 获取奖品
        WemallPrize prize = awardService.getPrize(awardId);
        // 更新奖品状态
        prize.setUserId(userId);
        prize.setUserTel(userTel);
        int result = awardService.updatePrize(prize);
        if(result > 0){
            int reduce = awardService.reduceAward(awardId);
            // TODO 加入优惠券
            WemallCouponAccept couponAccept = new WemallCouponAccept();
            couponAccept.setUserId(userId);
            couponAccept.setCouponId(prize.getCouponId());
            couponService.acceptCoupon(couponAccept);
            return ResponseUtil.ok(prize);
        }
        return ResponseUtil.fail();
    }
}
