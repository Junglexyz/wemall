package com.jungle.wemall.admin.controller;

import com.jungle.wemall.admin.util.ResponseUtil;
import com.jungle.wemall.db.pojo.Orders;
import com.jungle.wemall.db.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description: 订单处理
 * @author: jungle
 * @date: 2020-02-25 23:15
 */
@RestController
@RequestMapping("/admin/order")
public class OrderController {
    @Autowired
    private OrdersService ordersService;

    @PostMapping("/list")
    public Object listOrders(){
        List<Orders> list = ordersService.listOrders();
        return ResponseUtil.ok(list);
    }
}
