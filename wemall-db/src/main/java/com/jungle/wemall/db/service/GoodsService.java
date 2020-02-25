package com.jungle.wemall.db.service;

import com.jungle.wemall.db.dao.GoodsMapper;
import com.jungle.wemall.db.pojo.Goods;
import com.jungle.wemall.db.util.JacksonUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: goodsService
 * @author: jungle
 * @date: 2020-02-19 23:35
 */
@Service
public class GoodsService {
    @Resource
    GoodsMapper goodsMapper;

    public int insertGoods(Goods goods){
        return  goodsMapper.insertSelective(goods);
    }

    /**
     * 分页获取商品列表
     * @param body
     * @return
     */
    public Map<String, Object> listGoods(String body){
        Integer page = Integer.parseInt(JacksonUtil.parseString(body, "page"));
        Map<String, Integer> data = new HashMap<>();
        data.put("page", (page - 1) * 10);
        data.put("pageSize",10);
        List<Goods> listGoods = goodsMapper.listGoods(data);
        int total = goodsMapper.getAllGoodsCount();
        Map<String, Object> result = new HashMap<>();
        result.put("listGoods", listGoods);
        result.put("total",total);
        return  result;
    }

    public int createGoods(Goods goods){
        return goodsMapper.insertSelective(goods);
    }
    public int updateGoods(Goods goods){
        return  goodsMapper.updateByPrimaryKeySelective(goods);
    }

    public int deleteGoods(String body) {
        Integer goodsId = Integer.parseInt(JacksonUtil.parseString(body, "goodsId"));
        int result = goodsMapper.deleteByPrimaryKey(goodsId);
        return result;
    }

    public List<Goods> listGoodsByCategoryId(String body) {
        Integer categoryId = Integer.parseInt(JacksonUtil.parseString(body, "categoryId"));
        return goodsMapper.selectByCategoryId(categoryId);
    }
}
