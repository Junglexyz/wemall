package com.jungle.wemall.db.service;

import com.jungle.wemall.db.dao.ShopInfoMapper;
import com.jungle.wemall.db.pojo.ShopInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @description: 店铺基本信息
 * @author: jungle
 * @date: 2020-02-26 15:45
 */
@Service
public class ShopInfoService {
    @Resource
    private ShopInfoMapper shopInfoMapper;

    public ShopInfo getShopInfo(){
        return shopInfoMapper.selectByPrimaryKey(1);
    }
}
