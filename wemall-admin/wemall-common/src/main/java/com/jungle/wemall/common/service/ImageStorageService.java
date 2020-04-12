package com.jungle.wemall.common.service;

import com.jungle.wemall.common.storage.AliyunStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @description: 文件存储服务
 * @author: jungle
 * @date: 2020-02-23 21:19
 */
@Service
public class ImageStorageService {
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    private AliyunStorage aliyunStorage;

    /**
     * 存储图片至阿里云OSS
     * @param file
     * @param keyName
     * @return
     */
    public String upload(MultipartFile file, String keyName){
        String key = Long.toString(stringRedisTemplate.opsForValue().increment(keyName, 1));
        System.out.println(key);
        String fileName = file.getOriginalFilename();
        String suffix = null;
        if(fileName.endsWith(".png")){
            suffix = ".png";
        }else if (fileName.endsWith(".jpg")){
            suffix = ".jpg";
        }else{
            return null;
        }
        try {
            aliyunStorage.store(file.getInputStream(), file.getSize(),
                    file.getContentType(), keyName+"/"+key + suffix);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return key + suffix;
    }
}
