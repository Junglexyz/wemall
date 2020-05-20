package com.jungle.wemall.admin.controller;

import com.jungle.wemall.common.service.ImageStorageService;
import com.jungle.wemall.common.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @description: 图片存储
 * @author: jungle
 * @date: 2020-02-23 8:31
 */
@RestController
@RequestMapping("/admin/storage")
public class AdminStorageController {
    @Autowired
    ImageStorageService imageStorageService;
    /**
     * 图片存储
     * keyName: /bucketName/keyName.png
     */
    @PostMapping("/upload")
    public Object upload(@RequestParam("file") MultipartFile file, @RequestHeader("X-Upload-Type") String xUploadType)  {
        String keyName = null;
        if(null != xUploadType){
            keyName = xUploadType;
        }else{
            keyName = "common";
        }
        String key = imageStorageService.upload(file, keyName);
        return ResponseUtil.ok(key);
    }
}
