package com.jungle.wemall.db.service;

import com.jungle.wemall.db.dao.CategoryMapper;
import com.jungle.wemall.db.dao.ChildCategoryMapper;
import com.jungle.wemall.db.dao.WemallCategotyMapper;
import com.jungle.wemall.db.pojo.Category;
import com.jungle.wemall.db.pojo.ChildCategory;
import com.jungle.wemall.db.pojo.WemallCategoty;
import com.jungle.wemall.db.util.JacksonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;

/**
 * @description: cagegoryService
 * @author: jungle
 * @date: 2020-02-19 22:39
 */
@Service
public class CategoryService {
    @Resource
    WemallCategotyMapper wemallCategotyMapper;

    /**
     * 添加分类
     * @param wemallCategoty
     * @return
     */
    public int createCategory(WemallCategoty wemallCategoty){
        return  wemallCategotyMapper.insertSelective(wemallCategoty);
    }
    /**
     * 获取所有两级目录
     */
    public List<WemallCategoty> getCategoryByPid(){
        List<WemallCategoty> listCategories = wemallCategotyMapper.selectByPid(0);
        int len = listCategories.size();
        for(int i = 0; i < len; i++){
            List<WemallCategoty> subCategory = wemallCategotyMapper.selectByPid(listCategories.get(i).getId());
            listCategories.get(i).setChildren(subCategory);
        }
        return listCategories;
    }
    /**
     * 获取一级目录
     */
    public List<WemallCategoty> getCategoryL1(){
        return wemallCategotyMapper.selectByPid(0);
    }

    public int updateCategory(WemallCategoty wemallCategoty) {
        return wemallCategotyMapper.updateByPrimaryKeySelective(wemallCategoty);
    }

    public int deleteCategory(String body) {
        Integer id = Integer.parseInt(JacksonUtil.parseString(body, "id"));
        String level = JacksonUtil.parseString(body, "level");
        int result = wemallCategotyMapper.deleteByPrimaryKey(id);
        if(("L1".equals(level) || "L1" == level) && wemallCategotyMapper.selectByPid(id) != null){
            result = wemallCategotyMapper.deleteByPrimaryByPid(id);
        }
        return result;
    }
}
