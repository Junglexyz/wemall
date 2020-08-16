package com.jungle.wemall.db.dto;

/**
 * @description: 奖品参数
 * @author: jungle
 * @date: 2020-06-03 23:38
 */
public class Award {
    private String title;
    private Integer count;
    private Double totalPrice;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
