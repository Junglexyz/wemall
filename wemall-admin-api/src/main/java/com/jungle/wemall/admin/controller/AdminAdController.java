package com.jungle.wemall.admin.controller;

import com.jungle.wemall.common.util.ResponseUtil;
import com.jungle.wemall.db.pojo.WemallAd;
import com.jungle.wemall.db.service.WemallAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description: 广告
 * @author: jungle
 * @date: 2020-03-08 15:40
 */
@RestController
@RequestMapping("/admin/ad")
public class AdminAdController {
    @Autowired
    private WemallAdService wemallAdService;

    @PostMapping("/list")
    public Object listAd(){
        List<WemallAd> list = wemallAdService.listAd();
        if(list != null){
            return ResponseUtil.okList(list);
        }
        return  ResponseUtil.fail();
    }

    @PostMapping("/create")
    public Object createCategory(@RequestBody WemallAd wemallAd){
        int result = wemallAdService.createAd(wemallAd);
        return result == 1 ? ResponseUtil.ok() : ResponseUtil.updatedDataFailed();
    }

    @PostMapping("/update")
    public Object updateCategory(@RequestBody WemallAd wemallAd){
        int result = wemallAdService.updateAd(wemallAd);
        return result == 1 ? ResponseUtil.ok() : ResponseUtil.updatedDataFailed();
    }

    @PostMapping("/delete")
    public Object deleteCategory(@RequestBody String body){
        int result = wemallAdService.deleteAd(body);
        return result > 0 ? ResponseUtil.ok() : ResponseUtil.fail(10001,"删除失败");
    }
}
