package com.jungle.wemall.db.dao;

import com.alibaba.fastjson.JSONObject;
import com.jungle.wemall.db.pojo.WemallCouponAccept;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface WemallCouponAcceptMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WemallCouponAccept record);

    int insertSelective(WemallCouponAccept record);

    WemallCouponAccept selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WemallCouponAccept record);

    int updateByPrimaryKey(WemallCouponAccept record);

    /**
     * 已使用过的优惠券
     * @param userId
     * @return
     */
    List<WemallCouponAccept> selectByUser(Integer userId);

    void removeUsedCoupon(@Param("data")HashMap data);

    /**
     * 未使用的优惠券
     * @param userId
     * @return
     */
    List<WemallCouponAccept> selectNoUsedByUser(Integer userId);
}