package com.jungle.wemall.wx.dto;

import com.jungle.wemall.db.pojo.WemallGoods;
import com.jungle.wemall.db.pojo.WemallGoodsSpecification;

import java.util.List;

/**
 * @description: 商品所有信息
 * @author: jungle
 * @date: 2020-05-22 08:52
 */
public class GoodsAllinone {
    private WemallGoods goods;

    private List<WemallGoodsSpecification> specifications;

    public WemallGoods getGoods() {
        return goods;
    }

    public void setGoods(WemallGoods goods) {
        this.goods = goods;
    }

    public List<WemallGoodsSpecification> getSpecifications() {
        return specifications;
    }

    public void setSpecifications(List<WemallGoodsSpecification> specifications) {
        this.specifications = specifications;
    }
}
