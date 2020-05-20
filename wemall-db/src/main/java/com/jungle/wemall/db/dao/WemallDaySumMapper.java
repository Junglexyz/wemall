package com.jungle.wemall.db.dao;

import com.jungle.wemall.db.pojo.WemallDaySum;

public interface WemallDaySumMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WemallDaySum record);

    int insertSelective(WemallDaySum record);

    WemallDaySum selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WemallDaySum record);

    int updateByPrimaryKey(WemallDaySum record);
}