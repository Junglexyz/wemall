package com.jungle.wemall.db.service;

import com.jungle.wemall.db.dao.WemallRoleMapper;
import com.jungle.wemall.db.pojo.WemallRole;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @description:
 * @author: jungle
 * @date: 2020-04-01 15:22
 */
@Service
public class WemallRoleService {
    @Resource
    private WemallRoleMapper wemallRoleMapper;

    public Set<String> queryByIds(List<String> roleIds) {
        Set<String> roles = new HashSet<>(4);
        for(String roleId : roleIds){
            System.out.println(Integer.parseInt(roleId));
            WemallRole msrRole = wemallRoleMapper.selectByPrimaryKey(Integer.parseInt(roleId));
            roles.add(msrRole.getName());
        }
        return roles;
    }
}
