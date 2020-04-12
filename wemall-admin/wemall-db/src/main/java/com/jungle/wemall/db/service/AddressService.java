package com.jungle.wemall.db.service;

import com.jungle.wemall.db.dao.UserAddressMapper;
import com.jungle.wemall.db.pojo.UserAddress;
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
public class AddressService {
    @Resource
    private UserAddressMapper userAddressMapper;

    public int insertAddress(UserAddress userAddress){
        return userAddressMapper.insertSelective(userAddress);
    }

    public List<UserAddress> selectAddressByUserId(String body) {
        Integer userId = Integer.parseInt(JacksonUtil.parseString(body, "userId"));
        List<UserAddress> list  = userAddressMapper.selectAddressByUserId(userId);
        return list;
    }

    public int updateAddress(UserAddress userAddress) {
        return userAddressMapper.updateByPrimaryKeySelective(userAddress);
    }
}
