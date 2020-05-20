package com.jungle.wemall.wx.controller;

import com.jungle.wemall.common.util.ResponseUtil;
import com.jungle.wemall.db.pojo.WemallShopInfo;
import com.jungle.wemall.db.service.WemallShopInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @description: 小程序订单处理
 * @author: jungle
 * @date: 2020-02-24 21:57
 */
@RestController
@RequestMapping("/wx/shopInfo")
public class WxShopInfoController {
    @Autowired
    private WemallShopInfoService wemallShopInfoService;

    @PostMapping("/info")
    public Object listOrders(){
        WemallShopInfo shopInfo = wemallShopInfoService.getShopInfo();
        if(null != shopInfo){
            return ResponseUtil.ok(shopInfo);
        }
        return ResponseUtil.fail();
    }
}
