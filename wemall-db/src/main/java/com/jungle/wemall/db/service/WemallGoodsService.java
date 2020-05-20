package com.jungle.wemall.db.service;

import com.jungle.wemall.db.dao.WemallGoodsMapper;
import com.jungle.wemall.db.pojo.WemallGoods;
import com.jungle.wemall.db.util.FastJsonUtil;
import com.jungle.wemall.db.util.JacksonUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: goodsService
 * @author: jungle
 * @date: 2020-02-27 15:17
 */
@Service
public class WemallGoodsService {
    @Resource
    WemallGoodsMapper wemallGoodsMapper;

    public int insertGoods(WemallGoods goods){
        return  wemallGoodsMapper.insertSelective(goods);
    }

    /**
     * 分页获取商品列表
     * @param body
     * @return
     */
    public Map<String, Object> listGoods(String body){
        Integer page = FastJsonUtil.getInteger(body, "page");
        Integer categoryId = FastJsonUtil.getInteger(body, "categoryId");
        String status = FastJsonUtil.getString(body, "status");
        Map<String, Object> data = new HashMap<>();
        data.put("page", (page - 1) * 10);
        data.put("pageSize", 10);
        data.put("categoryId", categoryId);
        if(status != null){
            data.put("status", status);
        }
        List<WemallGoods> listGoods = wemallGoodsMapper.listGoods(data);
        int total = wemallGoodsMapper.count(data);
        Map<String, Object> result = new HashMap<>(4);
        result.put("listGoods", listGoods);
        result.put("total",total);
        return  result;
    }

    public int createGoods(WemallGoods goods){
        return wemallGoodsMapper.insertSelective(goods);
    }

    public int createGoodsByBatch(List<WemallGoods> goodsList){
        return wemallGoodsMapper.insertByBatch(goodsList);
    }

    public int updateGoods(WemallGoods goods){
        return  wemallGoodsMapper.updateByPrimaryKeySelective(goods);
    }

    public int deleteGoods(String body) {
        Integer goodsId = Integer.parseInt(JacksonUtil.parseString(body, "goodsId"));
        int result = wemallGoodsMapper.deleteByPrimaryKey(goodsId);
        return result;
    }

    public List<WemallGoods> listGoodsByCategoryId(String body) {
        Integer categoryId = Integer.parseInt(JacksonUtil.parseString(body, "categoryId"));
        return wemallGoodsMapper.selectByCategoryId(categoryId);
    }

    public List<WemallGoods> selectSpecial() {
        return wemallGoodsMapper.selectSpecial();
    }

    public Integer countGoods(){
        Map<String, Object> data = new HashMap<>(2);
        data.put("categoryId", null);
        return wemallGoodsMapper.count(data);
    }

    public List<WemallGoods> search(String content) {
        if(content == null || "".equals(content)){
            return new ArrayList<>();
        }
        String like = "%" + content + "%";
        return wemallGoodsMapper.search(like);
    }

}
