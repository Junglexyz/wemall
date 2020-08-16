package com.jungle.wemall.wx.controller;

import com.jungle.wemall.common.util.FastJsonUtil;
import com.jungle.wemall.common.util.ResponseUtil;
import com.jungle.wemall.db.pojo.WemallGoods;
import com.jungle.wemall.db.service.WemallGoodsService;
import com.jungle.wemall.wx.dto.GoodsAllinone;
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
    private WemallGoodsService wemallGoodsService;

    @PostMapping("/list")
    public Object listGoods(@RequestBody String body){
        List<WemallGoods> listGoods = wemallGoodsService.listGoodsByCategoryId(body);
        if(null != listGoods){
            return ResponseUtil.okList(listGoods);
        }
        return ResponseUtil.fail();
    }

    @PostMapping("/search")
    public Object search(@RequestBody String body){
        String content = FastJsonUtil.getString(body, "content");
        List<WemallGoods> listGoods = wemallGoodsService.search(content);
        if(null != listGoods){
            return ResponseUtil.okList(listGoods);
        }
        return ResponseUtil.fail();
    }
    @PostMapping("/single")
    public Object singleGoods(){
        WemallGoods goods = wemallGoodsService.singleGoods();
        if(goods != null){
            return ResponseUtil.ok(goods);
        }
        return ResponseUtil.fail();
    }

    @PostMapping("/update")
    public Object updateGoods(@RequestBody GoodsAllinone goods){
        int result = wemallGoodsService.updatePrice(goods.getGoods(), goods.getSpecifications());
        if(result > 0){
            return ResponseUtil.ok();
        }
        return ResponseUtil.fail();
    }
}
