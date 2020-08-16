package com.jungle.wemall.db.dao;

import com.jungle.wemall.db.pojo.WemallOrderDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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

    List<Map<String, Object>> sumOrder(@Param("order") Map<String, Object> order);
}