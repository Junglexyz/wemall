package com.jungle.wemall.wx.controller;

import com.jungle.wemall.common.util.ResponseUtil;
import com.jungle.wemall.db.pojo.WemallAd;
import com.jungle.wemall.db.service.WemallAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description: 广告
 * @author: jungle
 * @date: 2020-02-29 09:55
 */
@RestController
@RequestMapping("/wx/ad")
public class WxAdController {
    @Autowired
    private WemallAdService wemallAdService;

    @GetMapping("/list")
    public Object listAd(){
        List<WemallAd> list = wemallAdService.listAd();
        if(list != null){
            return ResponseUtil.okList(list);
        }
        return  ResponseUtil.fail();
    }
}
