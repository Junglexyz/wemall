package com.jungle.wemall.db.service;

import com.jungle.wemall.db.dao.WemallGoodsMapper;
import com.jungle.wemall.db.dao.WemallGoodsSpecificationMapper;
import com.jungle.wemall.db.pojo.WemallGoods;
import com.jungle.wemall.db.pojo.WemallGoodsSpecification;
import com.jungle.wemall.db.util.FastJsonUtil;
import com.jungle.wemall.db.util.JacksonUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Resource
    WemallGoodsSpecificationMapper wemallGoodsSpecificationMapper;


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
        String title = FastJsonUtil.getString(body, "title");
        Map<String, Object> data = new HashMap<>();
        data.put("page", (page - 1) * 10);
        data.put("pageSize", 10);
        data.put("categoryId", categoryId);
        if(!("").equals(title) && title != "" && title != null ){
            data.put("title", "%"+title+"%");
        }
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

    public int createGoods(WemallGoods goods, List<WemallGoodsSpecification> specifications){
        wemallGoodsMapper.insertSelective(goods);
        for(WemallGoodsSpecification specification: specifications){
            specification.setGoodsId(goods.getGoodsId());
            wemallGoodsSpecificationMapper.insertSelective(specification);
        }
        return 1;
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

    public WemallGoods singleGoods() {
        return wemallGoodsMapper.single();
    }

    @Transactional(rollbackFor=Exception.class)
    public int updatePrice(WemallGoods goods, List<WemallGoodsSpecification> specifications) {
        wemallGoodsMapper.updateByPrimaryKeySelective(goods);
        wemallGoodsSpecificationMapper.updateByPrimaryKeySelective(specifications.get(0));
        if(specifications.size() == 2){
            wemallGoodsSpecificationMapper.insertSelective(specifications.get(1));
        }
        return 1;
    }
}
