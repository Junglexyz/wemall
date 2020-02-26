package com.jungle.wemall.db.dao;

import com.jungle.wemall.db.pojo.Goods;

import java.util.List;
import java.util.Map;

public interface GoodsMapper {
    int deleteByPrimaryKey(Integer goodsId);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Integer goodsId);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);

    List<Goods> listGoods(Map data);

    int getAllGoodsCount();

    List<Goods> selectByCategoryId(Integer categoryId);
}