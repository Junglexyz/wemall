package com.jungle.wemall.admin.controller;

import com.jungle.wemall.admin.util.ResponseUtil;
import com.jungle.wemall.db.pojo.Goods;
import com.jungle.wemall.db.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/admin/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @PostMapping("/list")
    public Object list(@RequestBody String body){
        Map<String, Object> goods = goodsService.listGoods(body);
        return ResponseUtil.ok(goods);
    }

    @PostMapping("/create")
    public Object createGoods(@RequestBody Goods goods){
        int result = goodsService.createGoods(goods);
        System.out.println(result);
        return result == 1 ? ResponseUtil.ok() : ResponseUtil.fail();
    }

    @PostMapping("/update")
    public Object updateGoods(@RequestBody Goods goods){
        int result = goodsService.updateGoods(goods);
        return result == 1 ? ResponseUtil.ok() : ResponseUtil.updatedDataFailed();
    }

    @PostMapping("/delete")
    public Object deleteCategory(@RequestBody String body){
        int result = goodsService.deleteGoods(body);
        return result > 0 ? ResponseUtil.ok() : ResponseUtil.fail(10001,"删除失败！");
    }
}
