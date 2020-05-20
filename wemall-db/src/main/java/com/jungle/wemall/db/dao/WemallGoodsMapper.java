package com.jungle.wemall.db.dao;

import com.jungle.wemall.db.pojo.WemallGoods;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface WemallGoodsMapper {
    int deleteByPrimaryKey(Integer goodsId);

    int insert(WemallGoods record);

    int insertSelective(WemallGoods record);

    WemallGoods selectByPrimaryKey(Integer goodsId);

    int updateByPrimaryKeySelective(WemallGoods record);

    int updateByPrimaryKey(WemallGoods record);

    List<WemallGoods> selectByCategoryId(Integer categoryId);

//    int getAllGoodsCount();

    List<WemallGoods> listGoods(@Param("data") Map<String, Object> data);

    List<WemallGoods> selectSpecial();

    Integer count(@Param("data") Map<String, Object> data);

    int reduceStock(@Param("goodsId")Integer goodsId, @Param("number")Integer number);

    /**
     * 批量插入
     * @param goodsList
     * @return
     */
    int insertByBatch(List<WemallGoods> goodsList);

    void addStock(@Param("goodsId")Integer goodsId, @Param("number")Integer number);

    List<WemallGoods> search(String content);
}