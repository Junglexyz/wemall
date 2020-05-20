package com.jungle.wemall.db.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class WemallDaySum {
    private Integer id;

    private Integer userAmount;

    private Integer goodsAmount;

    private Integer orderAmount;

    private BigDecimal sumSales;

    private Integer dayStamp;

    private Date createTime;

    private Date updateTime;

    public WemallDaySum(Integer id, Integer userAmount, Integer goodsAmount, Integer orderAmount, BigDecimal sumSales, Integer dayStamp, Date createTime, Date updateTime) {
        this.id = id;
        this.userAmount = userAmount;
        this.goodsAmount = goodsAmount;
        this.orderAmount = orderAmount;
        this.sumSales = sumSales;
        this.dayStamp = dayStamp;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public WemallDaySum() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserAmount() {
        return userAmount;
    }

    public void setUserAmount(Integer userAmount) {
        this.userAmount = userAmount;
    }

    public Integer getGoodsAmount() {
        return goodsAmount;
    }

    public void setGoodsAmount(Integer goodsAmount) {
        this.goodsAmount = goodsAmount;
    }

    public Integer getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Integer orderAmount) {
        this.orderAmount = orderAmount;
    }

    public BigDecimal getSumSales() {
        return sumSales;
    }

    public void setSumSales(BigDecimal sumSales) {
        this.sumSales = sumSales;
    }

    public Integer getDayStamp() {
        return dayStamp;
    }

    public void setDayStamp(Integer dayStamp) {
        this.dayStamp = dayStamp;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}