package com.jungle.wemall.db.dao;

import com.jungle.wemall.db.pojo.WemallCategory;

import java.util.List;

public interface WemallCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WemallCategory record);

    int insertSelective(WemallCategory record);

    WemallCategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WemallCategory record);

    int updateByPrimaryKey(WemallCategory record);

    List<WemallCategory> selectByPid(int pid);

    int deleteByPid(Integer pid);
}