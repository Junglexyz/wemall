package com.jungle.wemall.db.dao;

import com.jungle.wemall.db.pojo.WemallGoodsSpecification;

import java.util.List;

public interface WemallGoodsSpecificationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WemallGoodsSpecification record);

    int insertSelective(WemallGoodsSpecification record);

    WemallGoodsSpecification selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WemallGoodsSpecification record);

    int updateByPrimaryKey(WemallGoodsSpecification record);

    int insertByBatch(List<WemallGoodsSpecification> specification);
}