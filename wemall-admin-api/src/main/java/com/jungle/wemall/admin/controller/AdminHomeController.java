package com.jungle.wemall.admin.controller;

import com.aliyun.oss.common.parser.ResponseParseException;
import com.jungle.wemall.common.util.FastJsonUtil;
import com.jungle.wemall.common.util.ResponseUtil;
import com.jungle.wemall.db.pojo.WemallShopInfo;
import com.jungle.wemall.db.pojo.WemallUser;
import com.jungle.wemall.db.service.WemallGoodsService;
import com.jungle.wemall.db.service.WemallOrderService;
import com.jungle.wemall.db.service.WemallShopInfoService;
import com.jungle.wemall.db.service.WemallUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 后台管理首页信息汇总
 * @author: jungle
 * @date: 2020-03-03 08:44
 */
@RestController
@RequestMapping("/admin/home")
public class AdminHomeController {
    @Autowired
    private WemallUserService wemallUserService;
    @Autowired
    private WemallGoodsService wemallGoodsService;
    @Autowired
    private WemallOrderService wemallOrderService;
    @Autowired
    private WemallShopInfoService wemallShopInfoService;
    @PostMapping("/info")
    public Object info(@RequestBody String body){
        Map<String, Object> order = new HashMap<>();
        order.put("createTime", FastJsonUtil.getString(body,"createTime"));
        order.put("endTime", FastJsonUtil.getString(body,"endTime"));
        Integer totalUser = wemallUserService.countUser();
        Integer totalGoods = wemallGoodsService.countGoods();
        Integer totalOrder = wemallOrderService.countOrderAll(order);
        Double totalSales = wemallOrderService.totalSales(order);
        Map<String, Object> data = new HashMap<>();
        data.put("userTotal", totalUser);
        data.put("goodsTotal", totalGoods);
        data.put("orderTotal", totalOrder);
        data.put("salesTotal", totalSales);
        return ResponseUtil.ok(data);
    }

    @PostMapping("/todayInfo")
    public Object todayInfo(@RequestBody String body){
        Map<String, Object> order = new HashMap<>(4);
        order.put("createTime", FastJsonUtil.getString(body,"createTime"));
        order.put("endTime", FastJsonUtil.getString(body,"endTime"));
        Integer totalOrder = wemallOrderService.countOrderAll(order);
        Double totalSales = wemallOrderService.totalSales(order);
        if(totalSales == null){
            totalSales = 0d;
        }
        Map<String, Object> result = new HashMap<>(4);
        result.put("totalOrder", totalOrder);
        result.put("totalSales", totalSales);
        return ResponseUtil.ok(result);
    }

    @PostMapping("/shopInfo")
    public Object shopInfo(){
        WemallShopInfo shopInfo = wemallShopInfoService.getShopInfo();
        if(shopInfo != null){
            return ResponseUtil.ok(shopInfo);
        }
        return ResponseUtil.fail();
    }

    @PostMapping("/updateShopInfo")
    public Object updateShopInfo(@RequestBody WemallShopInfo wemallShopInfo){
        int shopInfo = wemallShopInfoService.updateShopInfo(wemallShopInfo);
        if(shopInfo > 0){
            return ResponseUtil.ok(shopInfo);
        }
        return ResponseUtil.fail();
    }
}
