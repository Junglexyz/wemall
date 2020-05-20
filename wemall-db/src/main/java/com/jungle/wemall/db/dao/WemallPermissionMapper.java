package com.jungle.wemall.db.dao;

import com.jungle.wemall.db.pojo.WemallPermission;

public interface WemallPermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WemallPermission record);

    int insertSelective(WemallPermission record);

    WemallPermission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WemallPermission record);

    int updateByPrimaryKey(WemallPermission record);

    WemallPermission selectByRoleId(int parseInt);
}