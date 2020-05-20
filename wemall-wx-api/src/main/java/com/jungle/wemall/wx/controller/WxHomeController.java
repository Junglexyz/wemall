package com.jungle.wemall.wx.controller;

import com.jungle.wemall.common.util.ResponseUtil;
import com.jungle.wemall.db.pojo.WemallAd;
import com.jungle.wemall.db.pojo.WemallCategory;
import com.jungle.wemall.db.pojo.WemallGoods;
import com.jungle.wemall.db.service.WemallAdService;
import com.jungle.wemall.db.service.WemallCategoryService;
import com.jungle.wemall.db.service.WemallGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 微信小程序首页
 * @author: jungle
 * @date: 2020-02-29 12:29
 */
@RestController
@RequestMapping("/wx/home")
public class WxHomeController {
    @Autowired
    private WemallAdService wemallAdService;
    @Autowired
    private WemallGoodsService wemallGoodsService;
    @Autowired
    private WemallCategoryService wemallCategoryService;
    @PostMapping("/index")
    public Object index(){
        List<WemallAd> listAd = wemallAdService.listAd();
        List<WemallGoods> listSpecialGooods = wemallGoodsService.selectSpecial();
        List<WemallCategory> listWemallCategory = wemallCategoryService.getCategoryByPid();
        Map<String, Object> data = new HashMap<>();
        data.put("listAd", listAd);
        data.put("listSpecialGoods", listSpecialGooods);
        data.put("listCategory", listWemallCategory);
        return ResponseUtil.ok(data);
    }
}
