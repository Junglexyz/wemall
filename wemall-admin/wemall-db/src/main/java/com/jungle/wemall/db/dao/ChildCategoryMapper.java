package com.jungle.wemall.db.dao;

import com.jungle.wemall.db.pojo.ChildCategory;

public interface ChildCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ChildCategory record);

    int insertSelective(ChildCategory record);

    ChildCategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ChildCategory record);

    int updateByPrimaryKey(ChildCategory record);
}