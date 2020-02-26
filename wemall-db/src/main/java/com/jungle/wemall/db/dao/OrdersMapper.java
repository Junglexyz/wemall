package com.jungle.wemall.db.dao;

import com.jungle.wemall.db.pojo.Orders;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OrdersMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Orders record);

    int insertSelective(Orders record);

    Orders selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Orders record);

    int updateByPrimaryKey(Orders record);

    int insertByMap(@Param("order")Map<String, Object> order);

    /**
     * 获取订单及其详情
     * @return
     */
    List<Orders> listOrders();
}