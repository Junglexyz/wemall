package com.jungle.wemall.db.dao;

import com.jungle.wemall.db.pojo.WemallAdminUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WemallAdminUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WemallAdminUser record);

    int insertSelective(WemallAdminUser record);

    WemallAdminUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WemallAdminUser record);

    int updateByPrimaryKey(WemallAdminUser record);

    WemallAdminUser selectByPassword(@Param("account") String account, @Param("password") String password);

    List<WemallAdminUser> selectByUserName(String username);
}