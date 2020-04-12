package com.jungle.wemall.wx.controller;

import com.jungle.wemall.db.pojo.Goods;
import com.jungle.wemall.db.service.GoodsService;
import com.jungle.wemall.wx.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description: 小程序商品controller
 * @author: jungle
 * @date: 2020-02-24 12:41
 */
@RestController
@RequestMapping("/wx/goods")
public class WxGoodsController {
    @Autowired
    private GoodsService goodsService;

    @PostMapping("/list")
    public Object listGoods(@RequestBody String body){
        List<Goods> listGoods = goodsService.listGoodsByCategoryId(body);
        return ResponseUtil.ok(listGoods);
    }
}
