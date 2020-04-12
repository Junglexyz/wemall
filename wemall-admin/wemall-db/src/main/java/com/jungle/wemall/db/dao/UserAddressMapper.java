package com.jungle.wemall.db.dao;

import com.jungle.wemall.db.pojo.UserAddress;

import java.util.List;

public interface UserAddressMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserAddress record);

    int insertSelective(UserAddress record);

    UserAddress selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserAddress record);

    int updateByPrimaryKey(UserAddress record);

    List<UserAddress> selectAddressByUserId(Integer userId);
}