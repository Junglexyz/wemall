package com.jungle.wemall.db.service;

import com.jungle.wemall.db.dao.WemallGoodsSpecificationMapper;
import com.jungle.wemall.db.pojo.WemallGoodsSpecification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @description: 商品规格
 * @author: jungle
 * @date: 2020-05-27 15:11
 */
@Service
public class WemallGoodsSpecificationService {
    @Resource
    private WemallGoodsSpecificationMapper goodsSpecificationMapper;

    public int createSpec(WemallGoodsSpecification goodsSpecification){
        return goodsSpecificationMapper.insertSelective(goodsSpecification);
    }

    public int deleteSpec(Integer id){
        return goodsSpecificationMapper.deleteByPrimaryKey(id);
    }

    public int update(WemallGoodsSpecification goodsSpecification) {
        return goodsSpecificationMapper.updateByPrimaryKeySelective(goodsSpecification);
    }
}
