package com.jungle.wemall.db.dao;

import com.jungle.wemall.db.pojo.WemallCategoty;

import java.util.List;

public interface WemallCategotyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WemallCategoty record);

    int insertSelective(WemallCategoty record);

    WemallCategoty selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WemallCategoty record);

    int updateByPrimaryKey(WemallCategoty record);

    List<WemallCategoty> selectByPid(Integer pid);

    int deleteByPrimaryByPid(Integer id);
}