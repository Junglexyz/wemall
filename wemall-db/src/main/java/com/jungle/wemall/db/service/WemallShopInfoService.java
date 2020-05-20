package com.jungle.wemall.db.service;

import com.jungle.wemall.db.dao.WemallShopInfoMapper;
import com.jungle.wemall.db.pojo.WemallShopInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @description: 店铺基本信息
 * @author: jungle
 * @date: 2020-02-28 18:46
 */
@Service
public class WemallShopInfoService {
    @Resource
    private WemallShopInfoMapper wemallShopInfoMapper;

    public WemallShopInfo getShopInfo(){
        return wemallShopInfoMapper.selectByPrimaryKey(100000);
    }

    public int updateShopInfo(WemallShopInfo wemallShopInfo) {
        return wemallShopInfoMapper.updateByPrimaryKeySelective(wemallShopInfo);
    }
}
