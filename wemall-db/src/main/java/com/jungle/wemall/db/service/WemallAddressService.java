package com.jungle.wemall.db.service;

import com.jungle.wemall.db.dao.WemallUserAddressMapper;
import com.jungle.wemall.db.pojo.WemallUserAddress;
import com.jungle.wemall.db.util.FastJsonUtil;
import com.jungle.wemall.db.util.JacksonUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description: 地址
 * @author: jungle
 * @date: 2020-02-24 16:46
 */
@Service
public class WemallAddressService {
    @Resource
    private WemallUserAddressMapper wemallUserAddressMapper;

    public int insertAddress(WemallUserAddress userAddress){
        // 插入地址是否为默认值 1为默认值
        String defaulted = "1";
        if(defaulted.equals(userAddress.getDefaulted()) || userAddress.getDefaulted() == defaulted){
            removeDefault(userAddress.getUserId());
        }
        return wemallUserAddressMapper.insertSelective(userAddress);
    }

    public List<WemallUserAddress> selectAddressByUserId(String body) {
        Integer userId = FastJsonUtil.getInteger(body, "userId");
        List<WemallUserAddress> list  = wemallUserAddressMapper.selectAddressByUserId(userId);
        return list;
    }

    public int updateAddress(WemallUserAddress userAddress) {
        // 更新地址是否为默认值 1为默认值
        String defaulted = "1";
        if(defaulted.equals(userAddress.getDefaulted()) || userAddress.getDefaulted() == defaulted){
            removeDefault(userAddress.getUserId());
        }
        return wemallUserAddressMapper.updateByPrimaryKeySelective(userAddress);
    }

    /**
     * 移除默认地址
     * @return
     */
    private boolean removeDefault(Integer userId){
        return wemallUserAddressMapper.deleteDefault(userId);
    }
}
