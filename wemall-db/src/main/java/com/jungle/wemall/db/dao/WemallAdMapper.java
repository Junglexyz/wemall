package com.jungle.wemall.db.dao;

import com.jungle.wemall.db.pojo.WemallAd;

import java.util.List;

public interface WemallAdMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WemallAd record);

    int insertSelective(WemallAd record);

    WemallAd selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WemallAd record);

    int updateByPrimaryKey(WemallAd record);

    List<WemallAd> listAd();
}