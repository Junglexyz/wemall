package com.jungle.wemall.db.service;

import com.jungle.wemall.db.dao.WemallAdMapper;
import com.jungle.wemall.db.pojo.WemallAd;
import com.jungle.wemall.db.util.FastJsonUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description: 广告服务
 * @author: jungle
 * @date: 2020-02-29 09:51
 */
@Service
public class WemallAdService {
    @Resource
    private WemallAdMapper wemallAdMapper;

    public List<WemallAd> listAd(){
        return  wemallAdMapper.listAd();
    }

    public int updateAd(WemallAd wemallAd) {
        return wemallAdMapper.updateByPrimaryKeySelective(wemallAd);
    }

    public int createAd(WemallAd wemallAd) {
        return wemallAdMapper.insertSelective(wemallAd);
    }

    public int deleteAd(String body) {
        Integer id = FastJsonUtil.getInteger(body, "id");
        return wemallAdMapper.deleteByPrimaryKey(id);
    }
}
