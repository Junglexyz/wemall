package com.jungle.wemall.db.dao;

import com.jungle.wemall.db.pojo.WemallOrderDetail;

import java.util.List;

public interface WemallOrderDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WemallOrderDetail record);

    int insertSelective(WemallOrderDetail record);

    WemallOrderDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WemallOrderDetail record);

    int updateByPrimaryKey(WemallOrderDetail record);

    int insertByBatch(List orders);

    List<WemallOrderDetail> selectByOrderId(String orderId);

    int deleteByOrderId(String orderId);
}