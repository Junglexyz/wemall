package com.jungle.wemall.wx.controller;

import com.jungle.wemall.common.util.ResponseUtil;
import com.jungle.wemall.db.pojo.WemallCategory;
import com.jungle.wemall.db.service.WemallCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wx/category")
public class WxCategoryController {
    @Autowired
    private WemallCategoryService wemallCategoryService;

    @GetMapping("/list")
    public Object listCategory(){
//        System.out.println("test");
        List<WemallCategory> listWemallCategory = wemallCategoryService.getCategoryByPid();
        if(listWemallCategory != null){
            return ResponseUtil.okList(listWemallCategory);
        }
        return ResponseUtil.fail();
    }

    /*@PostMapping("/create")
    public Object createCategory(@RequestBody WemallCategory wemallCategory){
        System.out.println(wemallCategory);
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
        int result = wemallCategoryService.deleteCategory(body);
        System.out.println("delete-result:"+result);
        return result > 0 ? ResponseUtil.ok() : ResponseUtil.fail(10001,"删除失败！");
    }*/
}
