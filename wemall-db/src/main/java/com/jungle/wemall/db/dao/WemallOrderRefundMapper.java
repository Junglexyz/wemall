package com.jungle.wemall.db.dao;

import com.jungle.wemall.db.pojo.WemallOrderRefund;

public interface WemallOrderRefundMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WemallOrderRefund record);

    int insertSelective(WemallOrderRefund record);

    WemallOrderRefund selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WemallOrderRefund record);

    int updateByPrimaryKey(WemallOrderRefund record);
}