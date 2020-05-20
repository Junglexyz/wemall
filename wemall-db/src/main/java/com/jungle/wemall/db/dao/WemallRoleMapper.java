package com.jungle.wemall.db.dao;

import com.jungle.wemall.db.pojo.WemallRole;

public interface WemallRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WemallRole record);

    int insertSelective(WemallRole record);

    WemallRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WemallRole record);

    int updateByPrimaryKey(WemallRole record);
}