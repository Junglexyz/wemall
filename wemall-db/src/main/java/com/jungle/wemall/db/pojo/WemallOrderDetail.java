package com.jungle.wemall.db.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class WemallOrderDetail {
    private Integer id;

    private String orderId;

    private Integer goodsId;

    private String title;

    private String cover;

    private BigDecimal originPrice;

    private BigDecimal sellPrice;

    private BigDecimal discountMoney;

    private Integer number;

    private Date createTime;

    private Date updateTime;

    public WemallOrderDetail(Integer id, String orderId, Integer goodsId, String title, String cover, BigDecimal originPrice, BigDecimal sellPrice, BigDecimal discountMoney, Integer number, Date createTime, Date updateTime) {
        this.id = id;
        this.orderId = orderId;
        this.goodsId = goodsId;
        this.title = title;
        this.cover = cover;
        this.originPrice = originPrice;
        this.sellPrice = sellPrice;
        this.discountMoney = discountMoney;
        this.number = number;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public WemallOrderDetail() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover == null ? null : cover.trim();
    }

    public BigDecimal getOriginPrice() {
        return originPrice;
    }

    public void setOriginPrice(BigDecimal originPrice) {
        this.originPrice = originPrice;
    }

    public BigDecimal getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(BigDecimal sellPrice) {
        this.sellPrice = sellPrice;
    }

    public BigDecimal getDiscountMoney() {
        return discountMoney;
    }

    public void setDiscountMoney(BigDecimal discountMoney) {
        this.discountMoney = discountMoney;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
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