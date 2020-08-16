package com.jungle.wemall.db.dao;

import com.jungle.wemall.db.pojo.WemallAward;

import java.util.List;

public interface WemallAwardMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WemallAward record);

    int insertSelective(WemallAward record);

    WemallAward selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WemallAward record);

    int updateByPrimaryKey(WemallAward record);
    
    WemallAward selectOne();
    
    List<WemallAward> list();

    int reduceOne(Integer awardId);
}