package com.jungle.wemall.db.dao;

import com.jungle.wemall.db.pojo.WemallCoupon;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WemallCouponMapper {
    int deleteByPrimaryKey(Integer couponId);

    int insert(WemallCoupon record);

    int insertSelective(WemallCoupon record);

    WemallCoupon selectByPrimaryKey(Integer couponId);

    int updateByPrimaryKeySelective(WemallCoupon record);

    int updateByPrimaryKey(WemallCoupon record);

    /**
     * 类型为types可用优惠券
     * @return
     */
    List<WemallCoupon> listCoupon(@Param("types") String types);
}