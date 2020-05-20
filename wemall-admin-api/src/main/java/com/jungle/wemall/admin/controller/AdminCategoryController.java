package com.jungle.wemall.admin.controller;

import com.jungle.wemall.common.util.ResponseUtil;
import com.jungle.wemall.db.pojo.WemallCategory;
import com.jungle.wemall.db.service.WemallCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/category")
public class AdminCategoryController {
    @Autowired
    private WemallCategoryService wemallCategoryService;

    @GetMapping("/list")
    public Object listCategory(){
        List<WemallCategory> listWemallCategory = wemallCategoryService.getCategoryByPid();
        if(listWemallCategory != null){
            return ResponseUtil.ok(listWemallCategory);
        }
        return ResponseUtil.fail();
    }

    @PostMapping("/create")
    public Object createCategory(@RequestBody WemallCategory wemallCategory){
        int result = wemallCategoryService.createCategory(wemallCategory);
        return result == 1 ? ResponseUtil.ok() : ResponseUtil.updatedDataFailed();
    }

    @PostMapping("/update")
    public Object updateCategory(@RequestBody WemallCategory wemallCategory){
        int result = wemallCategoryService.updateCategory(wemallCategory);
        return result == 1 ? ResponseUtil.ok() : ResponseUtil.updatedDataFailed();
    }

    @PostMapping("/delete")
    public Object deleteCategory(@RequestBody String body){
        Object result = wemallCategoryService.deleteCategory(body);
        return result;
    }
}
