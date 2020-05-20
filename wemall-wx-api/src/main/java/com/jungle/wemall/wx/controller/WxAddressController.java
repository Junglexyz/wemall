package com.jungle.wemall.wx.controller;

import com.jungle.wemall.common.util.ResponseUtil;
import com.jungle.wemall.db.pojo.WemallUserAddress;
import com.jungle.wemall.db.service.WemallAddressService;
import com.jungle.wemall.wx.annotation.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description: 用户地址增删改查
 * @author: jungle
 * @date: 2020-02-24 16:45
 */
@RestController
@RequestMapping("/wx/address")
public class WxAddressController {
    @Autowired
    private WemallAddressService wemallAddressService;

    @PostMapping("/create")
    public Object createAddress(@RequestBody WemallUserAddress userAddress, @LoginUser Integer userId){
        if(userId == null){
            return ResponseUtil.unlogin();
        }
        int result = wemallAddressService.insertAddress(userAddress);
        return result == 1 ? ResponseUtil.ok() : ResponseUtil.updatedDataFailed();
    }

    @PostMapping("/list")
    public Object listAddress(@RequestBody String body, @LoginUser Integer userId){
        if(userId == null){
            return ResponseUtil.unlogin();
        }
        List<WemallUserAddress> list = wemallAddressService.selectAddressByUserId(body);
        if(null != list){
            return ResponseUtil.okList(list);
        }
        return ResponseUtil.fail();
    }

    @PostMapping("/update")
    public Object updateAddress(@RequestBody WemallUserAddress userAddress, @LoginUser Integer userId){
        if(userId == null){
            return ResponseUtil.unlogin();
        }
        int result = wemallAddressService.updateAddress(userAddress);
        return result == 1 ? ResponseUtil.ok() : ResponseUtil.updatedDataFailed();
    }
}
