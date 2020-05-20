package com.jungle.wemall.db.service;

import com.jungle.wemall.db.dao.WemallUserMapper;
import com.jungle.wemall.db.pojo.WemallUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @description: 用户信息
 * @author: jungle
 * @date: 2020-03-02 09:54
 */
@Service
public class WemallUserService {
    @Resource
    private WemallUserMapper wemallUserMapper;

    public WemallUser findUserById(Integer id){
        return wemallUserMapper.selectByPrimaryKey(id);
    }

    public int insert(WemallUser user){
        return wemallUserMapper.insertSelective(user);
    }

    public int update(WemallUser user){
        return wemallUserMapper.updateByPrimaryKeySelective(user);
    }

    public WemallUser findUserByMobile(String mobile) {
        return wemallUserMapper.selectByMobile(mobile);
    }
    public Integer countUser(){
        return wemallUserMapper.count();
    }
}
