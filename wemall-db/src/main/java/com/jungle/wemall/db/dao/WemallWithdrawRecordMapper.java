package com.jungle.wemall.db.dao;

import com.jungle.wemall.db.pojo.WemallWithdrawRecord;

import java.util.List;

public interface WemallWithdrawRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WemallWithdrawRecord record);

    int insertSelective(WemallWithdrawRecord record);

    WemallWithdrawRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WemallWithdrawRecord record);

    int updateByPrimaryKey(WemallWithdrawRecord record);

    List<WemallWithdrawRecord> listByUserId(Integer userId);
}