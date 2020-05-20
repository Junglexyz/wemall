package com.jungle.wemall.db.service;

import com.jungle.wemall.db.dao.WemallPermissionMapper;
import com.jungle.wemall.db.pojo.WemallPermission;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @description:
 * @author: jungle
 * @date: 2020-04-01 15:40
 */
@Service
public class WemallPermissionService {
    @Resource
    private WemallPermissionMapper wemallPermissionMapper;

    public Set<String> queryByRoleIds(List<String> roleIds) {
        Set<String> permissoins = new HashSet<>();
        for (String roleId: roleIds){
            WemallPermission msrPermission = wemallPermissionMapper.selectByRoleId(Integer.parseInt(roleId));
            permissoins.add(msrPermission.getPermission());
        }
        return permissoins;
    }
}
