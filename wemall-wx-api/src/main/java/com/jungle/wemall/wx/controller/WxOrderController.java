package com.jungle.wemall.wx.controller;

import com.jungle.wemall.common.util.ResponseUtil;
import com.jungle.wemall.db.service.WemallOrderService;
import com.jungle.wemall.db.util.FastJsonUtil;
import com.jungle.wemall.wx.annotation.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @description: 小程序订单处理
 * @author: jungle
 * @date: 2020-02-24 21:57
 */
@RestController
@RequestMapping("/wx/order")
public class WxOrderController {
    @Autowired
    private WemallOrderService wemallOrderService;
    @PostMapping("/submit")
    public Object submitOrder(@RequestBody String body, @LoginUser Integer userId){
        if(userId == null){
            return ResponseUtil.unlogin();
        }
        Object result = wemallOrderService.submitOrder(body);
        if(result != null){
            return ResponseUtil.ok(result);
        }
        return ResponseUtil.fail();
    }

    /**
     * 微信小程序预支付
     * @param body
     * @param request
     * @param userId
     * @return
     */
    @PostMapping("/prepay")
    public Object prepay(@RequestBody String body,HttpServletRequest request, @LoginUser Integer userId){
        if(userId == null){
            return ResponseUtil.unlogin();
        }

        Map order = FastJsonUtil.getMap(body, "order");
        Object prepay = wemallOrderService.prepay(order, request);
        if(prepay != null){
            return ResponseUtil.ok(prepay);
        }
        return ResponseUtil.fail();
    }

    @PostMapping("/list")
    public Object listOrder(@RequestBody String body, @LoginUser Integer userId){
        if(userId == null){
            return ResponseUtil.unlogin();
        }
        Map order = FastJsonUtil.getMap(body, "order");
        Map<String,Object> list = wemallOrderService.listOrder(order);
        if(list != null){
            return ResponseUtil.ok(list);
        }
        return ResponseUtil.fail();
    }

    @PostMapping("/delete")
    public Object deleteOrder(@RequestBody String body, @LoginUser Integer userId){
        if(userId == null){
            return ResponseUtil.unlogin();
        }
        int result = wemallOrderService.deleteOrderByOrderId(body);
        if(result > 0){
            return ResponseUtil.ok();
        }
        return ResponseUtil.fail();
    }

    /**
     * 微信小程序支付成功回调接口
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = { "/pay-notify" }, method = { RequestMethod.POST }, produces="text/xml")
    public String notify(HttpServletRequest request, HttpServletResponse response){
        //  produces="text/xml" 返回前端content-type类型
        String result = wemallOrderService.payNotify(request, response);
        System.out.println(result);
        if(request != null){
            return result;
        }
        return String.valueOf(ResponseUtil.fail());
    }
}
