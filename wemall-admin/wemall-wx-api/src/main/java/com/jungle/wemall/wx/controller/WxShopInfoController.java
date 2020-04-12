package com.jungle.wemall.wx.controller;

import com.jungle.wemall.db.pojo.Orders;
import com.jungle.wemall.db.pojo.ShopInfo;
import com.jungle.wemall.db.service.OrdersService;
import com.jungle.wemall.db.service.ShopInfoService;
import com.jungle.wemall.wx.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description: 小程序订单处理
 * @author: jungle
 * @date: 2020-02-24 21:57
 */
@RestController
@RequestMapping("/wx/shopInfo")
public class WxShopInfoController {
    @Autowired
    private ShopInfoService shopInfoService;

    @PostMapping("/info")
    public Object listOrders(){
        ShopInfo shopInfo = shopInfoService.getShopInfo();
        return ResponseUtil.ok(shopInfo);
    }
}
