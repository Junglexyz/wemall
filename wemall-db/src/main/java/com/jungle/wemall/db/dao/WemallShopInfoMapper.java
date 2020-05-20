package com.jungle.wemall.db.dao;

import com.jungle.wemall.db.pojo.WemallShopInfo;

public interface WemallShopInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WemallShopInfo record);

    int insertSelective(WemallShopInfo record);

    WemallShopInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WemallShopInfo record);

    int updateByPrimaryKey(WemallShopInfo record);
}