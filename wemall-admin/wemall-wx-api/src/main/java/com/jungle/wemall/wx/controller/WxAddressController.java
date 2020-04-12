package com.jungle.wemall.wx.controller;

import com.jungle.wemall.db.pojo.UserAddress;
import com.jungle.wemall.db.service.AddressService;
import com.jungle.wemall.wx.util.ResponseUtil;
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
    private AddressService addressService;

    @PostMapping("/create")
    public Object createAddress(@RequestBody UserAddress userAddress){
        System.out.println(userAddress);
        int result = addressService.insertAddress(userAddress);
        return result == 1 ? ResponseUtil.ok() : ResponseUtil.updatedDataFailed();
    }

    @PostMapping("/list")
    public Object listAddress(@RequestBody String body){
        System.out.println(body);
        List<UserAddress> list = addressService.selectAddressByUserId(body);
        return ResponseUtil.ok(list);
    }

    @PostMapping("/update")
    public Object updateAddress(@RequestBody UserAddress userAddress){
        int result = addressService.updateAddress(userAddress);
        return result == 1 ? ResponseUtil.ok() : ResponseUtil.updatedDataFailed();
    }
}
