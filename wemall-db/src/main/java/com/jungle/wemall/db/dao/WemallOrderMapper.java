package com.jungle.wemall.db.dao;

import com.jungle.wemall.db.pojo.WemallOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface WemallOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WemallOrder record);

    int insertSelective(WemallOrder record);

    WemallOrder selectByPrimaryKey(Integer id);

    WemallOrder selectByOrderId(String orderId);

    int updateByPrimaryKeySelective(WemallOrder record);

    int updateByPrimaryKey(WemallOrder record);

    int insertByMap(@Param("order") Map<String, Object> order);

    /**
     * 修改订单状态
     * @param order
     * @return
     */
    int updateByMap(@Param("order") Map<String, Object> order);
    /**
     * 获取订单及其详情
     * @return
     */
    List<WemallOrder> listOrders(@Param("order") Map<String, Object> order);

    Integer count(@Param("order") Map<String, Object> order);

    Double sumOrderSales(@Param("order") Map<String, Object> order);

    List<WemallOrder> selectByPage(@Param("order") Map<String, Object> order);
}