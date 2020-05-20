package com.jungle.wemall.db.dao;

import com.jungle.wemall.db.pojo.WemallOrderComment;

public interface WemallOrderCommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WemallOrderComment record);

    int insertSelective(WemallOrderComment record);

    WemallOrderComment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WemallOrderComment record);

    int updateByPrimaryKey(WemallOrderComment record);
}