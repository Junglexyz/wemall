package com.jungle.wemall.db.service;

import com.jungle.wemall.db.dao.WemallCouponAcceptMapper;
import com.jungle.wemall.db.dao.WemallCouponMapper;
import com.jungle.wemall.db.pojo.WemallCoupon;
import com.jungle.wemall.db.pojo.WemallCouponAccept;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;

/**
 * @description: 优惠券管理
 * @author: jungle
 * @date: 2020-02-28 12:26
 */
@Service
public class WemallCouponService {
    @Resource
    private WemallCouponMapper wemallCouponMapper;

    @Resource
    private WemallCouponAcceptMapper wemallCouponAcceptMapper;

    public int insertCoupon(WemallCoupon wemallCoupon){
        return wemallCouponMapper.insertSelective(wemallCoupon);
    }

    /**
     * 后台展示所有优惠券
     * @return
     */
    public List<WemallCoupon> listCoupon(String types) {
        return wemallCouponMapper.listCoupon(null);
    }

    public List<WemallCoupon> listAuthCoupon(String types) {
        return wemallCouponMapper.listCoupon(types);
    }

    /**
     * 用户获取优惠券
     * @param wemallCouponAccept
     * @return
     */
    public int acceptCoupon(WemallCouponAccept wemallCouponAccept){
        return wemallCouponAcceptMapper.insertSelective(wemallCouponAccept);
    }

    public List<WemallCouponAccept> getCouponByUser(Integer userId){
        return wemallCouponAcceptMapper.selectNoUsedByUser(userId);
    }

    /**
     * 筛选用户领取未使用的优惠券
     * @param userId
     * @return
     */
    public List<WemallCoupon> noAccept(Integer userId){
        List<WemallCouponAccept> listAccept = wemallCouponAcceptMapper.selectByUser(userId);
        // 普通红包
        String types = "2";
        List<WemallCoupon> listAll = wemallCouponMapper.listCoupon(types);
        Iterator<WemallCoupon> iterator = listAll.iterator();
        while(iterator.hasNext()){
            WemallCoupon wemallCoupon = iterator.next();
            Iterator<WemallCouponAccept> iteratorAccept = listAccept.iterator();
            while(iteratorAccept.hasNext()){
                WemallCouponAccept wemallCouponAccept = iteratorAccept.next();
                if(wemallCoupon.getCouponId().equals(wemallCouponAccept.getCouponId())){
                    iterator.remove();
                }
            }
        }
        return listAll;
    }

    public int updateCoupon(WemallCoupon wemallCoupon) {
        return wemallCouponMapper.updateByPrimaryKeySelective(wemallCoupon);
    }

    public int deleteCoupon(Integer couponId) {
        return wemallCouponMapper.deleteByPrimaryKey(couponId);
    }

}
