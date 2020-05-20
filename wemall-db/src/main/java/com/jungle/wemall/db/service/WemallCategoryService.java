package com.jungle.wemall.db.service;

import com.jungle.wemall.common.util.ResponseUtil;
import com.jungle.wemall.db.dao.WemallCategoryMapper;
import com.jungle.wemall.db.dao.WemallGoodsMapper;
import com.jungle.wemall.db.pojo.WemallCategory;
import com.jungle.wemall.db.pojo.WemallGoods;
import com.jungle.wemall.db.util.FastJsonUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @author: jungle
 * @date: 2020-03-08 20:23
 */
@Service
public class WemallCategoryService {
    @Resource
    private WemallCategoryMapper wemallCategoryMapper;
    @Resource
    private WemallGoodsMapper wemallGoodsMapper;
    public List<WemallCategory> getCategoryByPid() {
        List<WemallCategory> listCategory = wemallCategoryMapper.selectByPid(0);
        for(int i = 0; i < listCategory.size(); i++){
            List<WemallCategory> subListCategory = wemallCategoryMapper.selectByPid(listCategory.get(i).getId());
            listCategory.get(i).setChildren(subListCategory);
        }
        return  listCategory;
    }

    public int createCategory(WemallCategory wemallCategory) {
        return wemallCategoryMapper.insertSelective(wemallCategory);
    }

    public int updateCategory(WemallCategory wemallCategory) {
        return wemallCategoryMapper.updateByPrimaryKeySelective(wemallCategory);
    }

    public Object deleteCategory(String body) {
        Integer id = FastJsonUtil.getInteger(body, "id");
        Integer pid = FastJsonUtil.getInteger(body, "pid");
        String level = FastJsonUtil.getString(body, "level");
        int result = 0;
        // 类目等级  L1为一级类目
        String categoryClass = "L1";
        if (categoryClass.equals(level)){
            List<WemallCategory> listCategory = wemallCategoryMapper.selectByPid(id);
            // 检查子目录是否是有商品
            for(WemallCategory category: listCategory){
                List<WemallGoods> wemallGoods = wemallGoodsMapper.selectByCategoryId(category.getId());
                if(wemallGoods.size() > 0){
                    return ResponseUtil.fail(1001,"该类目还存在商品,不可删除");
                }
            }
            if(listCategory != null){
                result = wemallCategoryMapper.deleteByPid(id);
            }
            result = wemallCategoryMapper.deleteByPrimaryKey(id);
        } else{
            List<WemallGoods> wemallGoods = wemallGoodsMapper.selectByCategoryId(id);
            if(wemallGoods.size() > 0){
                return ResponseUtil.fail(1001,"该类目还存在商品不可删除");
            }
            result = wemallCategoryMapper.deleteByPrimaryKey(id);
        }
        return  result > 0 ? ResponseUtil.ok() : ResponseUtil.fail();
    }
}
