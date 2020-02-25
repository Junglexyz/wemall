package com.jungle.wemall.db.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jungle.wemall.db.dao.OrdersDetailMapper;
import com.jungle.wemall.db.dao.OrdersMapper;
import com.jungle.wemall.db.pojo.Orders;
import com.jungle.wemall.db.pojo.OrdersDetail;
import com.jungle.wemall.db.util.FastJsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @description: 订单
 * @author: jungle
 * @date: 2020-02-25 00:38
 */
@Service
public class OrdersService {
    @Resource
    private OrdersDetailMapper ordersDetailMapper;
    @Resource
    private OrdersMapper ordersMapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public int submitOrder(String body){
        long orderId = stringRedisTemplate.opsForValue().increment("orderId", 1);
        // 订单详情列表
        JSONObject jsonObject = JSONObject.parseObject(body);
        List orders = new ArrayList<>();
        if(jsonObject.containsKey("orders")){
            JSONArray listOrders = jsonObject.getJSONArray("orders");
            for(int i = 0; i < listOrders.size(); i ++){
                listOrders.getJSONObject(i).put("orderId", orderId);
            }
            orders = (List)listOrders;
        }
        // 订单汇总
        Map order = FastJsonUtil.getMap(body, "order");
        order.put("orderId", orderId);
        int resultOfInsertOrders = ordersDetailMapper.insertByBatch(orders);
        int resultOfInsertOrder = ordersMapper.insertByMap(order);
        int result = (resultOfInsertOrder > 0 && resultOfInsertOrders > 0) ? 1 : 0;
        return result;
    }

    public List<Orders> listOrders(){
        return ordersMapper.listOrders();
    }
}
