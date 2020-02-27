package com.jungle.wemall.db.service;

import com.jungle.wemall.db.dao.UserMapper;
import com.jungle.wemall.db.pojo.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @description: 用户信息
 * @author: jungle
 * @date: 2020-02-26 23:48
 */
@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    public User findUserById(Integer id){
        return userMapper.selectByPrimaryKey(id);
    }

    public int insert(User user){
        return userMapper.insertSelective(user);
    }
}
