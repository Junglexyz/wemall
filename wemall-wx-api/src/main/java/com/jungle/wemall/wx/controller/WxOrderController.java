package com.jungle.wemall.wx.controller;

import com.alibaba.fastjson.JSONObject;
import com.jungle.wemall.db.pojo.Orders;
import com.jungle.wemall.db.service.OrdersService;
import com.jungle.wemall.wx.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * @description: 小程序订单处理
 * @author: jungle
 * @date: 2020-02-24 21:57
 */
@RestController
@RequestMapping("/wx/order")
public class WxOrderController {
    @Autowired
    private OrdersService ordersService;
    @PostMapping("/submit")
    public Object submitOrder(@RequestBody String body){
        System.out.println(body);
        int result = ordersService.submitOrder(body);
        return ResponseUtil.ok();
    }
}
