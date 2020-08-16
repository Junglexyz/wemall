package com.jungle.wemall.admin.controller;

import com.jungle.wemall.admin.dto.GoodsAllinone;
import com.jungle.wemall.admin.service.excel.ExcelRead;
import com.jungle.wemall.common.util.ResponseUtil;
import com.jungle.wemall.db.pojo.WemallGoods;
import com.jungle.wemall.db.service.WemallGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @description: 小程序商品后台管理
 * @author: jungle
 * @date: 2020-02-24 21:57
 */
@RestController
@RequestMapping("/admin/goods")
public class AdminGoodsController {
    @Autowired
    private WemallGoodsService wemallGoodsService;

    @PostMapping("/list")
    public Object list(@RequestBody String body){
        Map<String, Object> goods = wemallGoodsService.listGoods(body);
        if(null != goods){
            return ResponseUtil.ok(goods);
        }
        return ResponseUtil.fail();
    }

    @PostMapping("/create")
    public Object createGoods(@RequestBody GoodsAllinone goodsAllinone){
        int result = wemallGoodsService.createGoods(goodsAllinone.getGoods(), goodsAllinone.getSpecifications());
        return result == 1 ? ResponseUtil.ok() : ResponseUtil.fail();
    }

    @PostMapping("/update")
    public Object updateGoods(@RequestBody WemallGoods goods){
        int result = wemallGoodsService.updateGoods(goods);
        return result == 1 ? ResponseUtil.ok() : ResponseUtil.updatedDataFailed();
    }

    @PostMapping("/delete")
    public Object deleteCategory(@RequestBody String body){
        int result = wemallGoodsService.deleteGoods(body);
        return result > 0 ? ResponseUtil.ok() : ResponseUtil.fail(10001,"删除失败！");
    }

    @PostMapping("/excel")
    public Object excel(@RequestParam("file") MultipartFile file) throws IOException {

        String name = file.getOriginalFilename();
        // 类目id
        Integer categoryId = Integer.parseInt(name.substring(0, 6));
        List<WemallGoods> list = ExcelRead.excelRead(file.getInputStream(), categoryId);
        int result = 0;
        for(WemallGoods goods: list){
            wemallGoodsService.createGoods(goods, goods.getSpecifications());
            result ++;
        }
        if(result == list.size()){
            return ResponseUtil.ok();
        }
        return ResponseUtil.fail();
    }
}
