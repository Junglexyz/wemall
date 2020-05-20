package com.jungle.wemall.db.service;

import com.jungle.wemall.db.dao.WemallWithdrawRecordMapper;
import com.jungle.wemall.db.pojo.WemallWithdrawRecord;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description: 团购收益提现记录
 * @author: jungle
 * @date: 2020-03-16 18:38
 */
@Service
public class WemallWithdrawRecordService {
    @Resource
    private WemallWithdrawRecordMapper wemallWithdrawRecordMapper;

    public List<WemallWithdrawRecord> listByUserId(Integer userId){
        return wemallWithdrawRecordMapper.listByUserId(userId);
    }

    public int insertRecord(WemallWithdrawRecord wemallWithdrawRecord){
        return wemallWithdrawRecordMapper.insertSelective(wemallWithdrawRecord);
    }
}
