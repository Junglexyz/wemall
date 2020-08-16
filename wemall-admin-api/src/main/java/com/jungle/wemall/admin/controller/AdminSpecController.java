package com.jungle.wemall.admin.controller;

import com.jungle.wemall.common.util.FastJsonUtil;
import com.jungle.wemall.common.util.ResponseUtil;
import com.jungle.wemall.db.pojo.WemallAd;
import com.jungle.wemall.db.pojo.WemallGoodsSpecification;
import com.jungle.wemall.db.service.WemallAdService;
import com.jungle.wemall.db.service.WemallGoodsSpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description: 商品规格
 * @author: jungle
 * @date: 2020-05-27 15:15
 */
@RestController
@RequestMapping("/admin/spec")
public class AdminSpecController {
    @Autowired
    private WemallGoodsSpecificationService goodsSpecificationService;

    @PostMapping("/create")
    public Object createSpec(@RequestBody WemallGoodsSpecification goodsSpecification){
        int result = goodsSpecificationService.createSpec(goodsSpecification);
        return result == 1 ? ResponseUtil.ok() : ResponseUtil.updatedDataFailed();
    }

    /*@PostMapping("/update")
    public Object updateCategory(@RequestBody WemallAd wemallAd){
        int result = wemallAdService.updateAd(wemallAd);
        return result == 1 ? ResponseUtil.ok() : ResponseUtil.updatedDataFailed();
    }*/

    @PostMapping("/delete")
    public Object deleteSpec(@RequestBody String body){
        Integer id = FastJsonUtil.getInteger(body, "specId");
        int result = goodsSpecificationService.deleteSpec(id);
        return result > 0 ? ResponseUtil.ok() : ResponseUtil.fail(10001,"删除失败");
    }

    @PostMapping("/update")
    public Object updateSpec(@RequestBody WemallGoodsSpecification goodsSpecification){
        int result = goodsSpecificationService.update(goodsSpecification);
        return result == 1 ? ResponseUtil.ok() : ResponseUtil.updatedDataFailed();
    }
}
