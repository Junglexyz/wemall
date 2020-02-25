package com.jungle.wemall.admin.controller;

import com.jungle.wemall.admin.util.ResponseUtil;
import com.jungle.wemall.common.service.ImageStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @description: 图片存储
 * @author: jungle
 * @date: 2020-02-23 21:07
 */
@RestController
@RequestMapping("/admin/storage")
public class StorageController {
    @Autowired
    ImageStorageService imageStorageService;
    /**
     * 图片存储
     * keyName: /bucketName/keyName.png
     */
    @PostMapping("/upload")
    public Object upload(@RequestParam("file") MultipartFile file)  {
        String key = imageStorageService.upload(file, "goods");
        return ResponseUtil.ok(key);
    }
}
