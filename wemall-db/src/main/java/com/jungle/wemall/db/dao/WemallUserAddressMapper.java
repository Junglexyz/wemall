package com.jungle.wemall.db.dao;

import com.jungle.wemall.db.pojo.WemallUserAddress;

import java.util.List;

public interface WemallUserAddressMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WemallUserAddress record);

    int insertSelective(WemallUserAddress record);

    WemallUserAddress selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WemallUserAddress record);

    int updateByPrimaryKey(WemallUserAddress record);

    List<WemallUserAddress> selectAddressByUserId(Integer userId);

    boolean deleteDefault(Integer userId);
}