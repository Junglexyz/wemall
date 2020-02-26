package com.jungle.wemall.admin.controller;


import com.jungle.wemall.admin.util.JacksonUtil;
import com.jungle.wemall.admin.util.ResponseUtil;
import com.jungle.wemall.db.pojo.WemallCategoty;
import com.jungle.wemall.db.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public Object listCategory(){
        List<WemallCategoty> listWemallCategoty = categoryService.getCategoryByPid();
        return ResponseUtil.ok(listWemallCategoty);
    }

    @PostMapping("/create")
    public Object createCategory(@RequestBody WemallCategoty wemallCategoty){
        System.out.println(wemallCategoty);
        int result = categoryService.createCategory(wemallCategoty);
        return result == 1 ? ResponseUtil.ok() : ResponseUtil.updatedDataFailed();
    }

    @PostMapping("/update")
    public Object updateCategory(@RequestBody WemallCategoty wemallCategoty){
        int result = categoryService.updateCategory(wemallCategoty);
        return result == 1 ? ResponseUtil.ok() : ResponseUtil.updatedDataFailed();
    }

    @PostMapping("/delete")
    public Object deleteCategory(@RequestBody String body){
        int result = categoryService.deleteCategory(body);
        System.out.println("delete-result:"+result);
        return result > 0 ? ResponseUtil.ok() : ResponseUtil.fail(10001,"删除失败！");
    }
}
