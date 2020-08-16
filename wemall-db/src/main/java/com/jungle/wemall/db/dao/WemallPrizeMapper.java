package com.jungle.wemall.db.dao;

import com.jungle.wemall.db.pojo.WemallPrize;

import java.util.List;

public interface WemallPrizeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WemallPrize record);

    int insertSelective(WemallPrize record);

    WemallPrize selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WemallPrize record);

    int updateByPrimaryKey(WemallPrize record);

    List<WemallPrize> list(Integer awardId);
    
    WemallPrize selectOne(Integer awardId);

    int updateNoUsed(WemallPrize prize);

    int deleteByAward(Integer awardId);
}