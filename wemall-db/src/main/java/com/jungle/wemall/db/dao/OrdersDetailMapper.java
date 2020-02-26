package com.jungle.wemall.db.dao;

import com.jungle.wemall.db.pojo.OrdersDetail;

import java.util.List;

public interface OrdersDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrdersDetail record);

    int insertSelective(OrdersDetail record);

    int insertByBatch(List<OrdersDetail> list);

    OrdersDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrdersDetail record);

    int updateByPrimaryKey(OrdersDetail record);
}