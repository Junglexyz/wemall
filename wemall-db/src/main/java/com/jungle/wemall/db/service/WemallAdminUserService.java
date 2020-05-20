package com.jungle.wemall.db.service;

import com.jungle.wemall.db.dao.WemallAdminUserMapper;
import com.jungle.wemall.db.pojo.WemallAdminUser;
import com.jungle.wemall.db.util.FastJsonUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description: 后台权限验证
 * @author: jungle
 * @date: 2020-02-27 12:04
 */
@Service
public class WemallAdminUserService {
    @Resource
    private WemallAdminUserMapper wemallAdminUserMapper;

    public WemallAdminUser login(String account, String password){
        return wemallAdminUserMapper.selectByPassword(account,password);
    }

    public List<WemallAdminUser> findAdmin(String username) {
        return wemallAdminUserMapper.selectByUserName(username);
    }

    public int updateById(WemallAdminUser admin) {
        return wemallAdminUserMapper.updateByPrimaryKey(admin);
    }
}
