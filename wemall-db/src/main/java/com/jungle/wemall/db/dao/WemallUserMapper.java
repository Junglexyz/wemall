package com.jungle.wemall.db.dao;

import com.jungle.wemall.db.pojo.WemallUser;

public interface WemallUserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(WemallUser record);

    int insertSelective(WemallUser record);

    WemallUser selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(WemallUser record);

    int updateByPrimaryKey(WemallUser record);

    WemallUser selectByMobile(String mobile);

    Integer count();
}