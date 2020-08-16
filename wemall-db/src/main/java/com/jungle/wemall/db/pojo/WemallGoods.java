package com.jungle.wemall.db.pojo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class WemallGoods {
    private Integer goodsId;

    private Integer categoryId;

    private String title;

    private String goodsDescribe;

    private String cover;

    private BigDecimal originPrice;

    private BigDecimal sellPrice;

    private BigDecimal discount;

    private BigDecimal returnCash;

    private BigDecimal purchasingPrice;

    private String purchasingUnit;

    private Integer stock;

    private Integer limitNum;

    private String options;

    private Integer totalSales;

    private Integer monthSales;

    private BigDecimal praiseRate;

    private Boolean special;

    private String status;

    private Date createTime;

    private Date updateTime;

    private List<WemallGoodsSpecification> specifications;

    public WemallGoods(Integer goodsId, Integer categoryId, String title, String goodsDescribe, String cover, BigDecimal originPrice, BigDecimal sellPrice, BigDecimal discount, BigDecimal returnCash, BigDecimal purchasingPrice, String purchasingUnit, Integer stock, Integer limitNum, String options, Integer totalSales, Integer monthSales, BigDecimal praiseRate, Boolean special, String status, Date createTime, Date updateTime) {
        this.goodsId = goodsId;
        this.categoryId = categoryId;
        this.title = title;
        this.goodsDescribe = goodsDescribe;
        this.cover = cover;
        this.originPrice = originPrice;
        this.sellPrice = sellPrice;
        this.discount = discount;
        this.returnCash = returnCash;
        this.purchasingPrice = purchasingPrice;
        this.purchasingUnit = purchasingUnit;
        this.stock = stock;
        this.limitNum = limitNum;
        this.options = options;
        this.totalSales = totalSales;
        this.monthSales = monthSales;
        this.praiseRate = praiseRate;
        this.special = special;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public WemallGoods(Integer goodsId, Integer categoryId, String title, String goodsDescribe, String cover, BigDecimal originPrice, BigDecimal sellPrice, BigDecimal discount, BigDecimal returnCash, BigDecimal purchasingPrice, String purchasingUnit, Integer stock, Integer limitNum, String options, Integer totalSales, Integer monthSales, BigDecimal praiseRate, Boolean special, String status, Date createTime, Date updateTime, List<WemallGoodsSpecification> specifications) {
        this.goodsId = goodsId;
        this.categoryId = categoryId;
        this.title = title;
        this.goodsDescribe = goodsDescribe;
        this.cover = cover;
        this.originPrice = originPrice;
        this.sellPrice = sellPrice;
        this.discount = discount;
        this.returnCash = returnCash;
        this.purchasingPrice = purchasingPrice;
        this.purchasingUnit = purchasingUnit;
        this.stock = stock;
        this.limitNum = limitNum;
        this.options = options;
        this.totalSales = totalSales;
        this.monthSales = monthSales;
        this.praiseRate = praiseRate;
        this.special = special;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.specifications = specifications;
    }

    public List<WemallGoodsSpecification> getSpecifications() {
        return specifications;
    }

    public void setSpecifications(List<WemallGoodsSpecification> specifications) {
        this.specifications = specifications;
    }

    public WemallGoods() {
        super();
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getGoodsDescribe() {
        return goodsDescribe;
    }

    public void setGoodsDescribe(String goodsDescribe) {
        this.goodsDescribe = goodsDescribe == null ? null : goodsDescribe.trim();
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

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getReturnCash() {
        return returnCash;
    }

    public void setReturnCash(BigDecimal returnCash) {
        this.returnCash = returnCash;
    }

    public BigDecimal getPurchasingPrice() {
        return purchasingPrice;
    }

    public void setPurchasingPrice(BigDecimal purchasingPrice) {
        this.purchasingPrice = purchasingPrice;
    }

    public String getPurchasingUnit() {
        return purchasingUnit;
    }

    public void setPurchasingUnit(String purchasingUnit) {
        this.purchasingUnit = purchasingUnit == null ? null : purchasingUnit.trim();
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getLimitNum() {
        return limitNum;
    }

    public void setLimitNum(Integer limitNum) {
        this.limitNum = limitNum;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options == null ? null : options.trim();
    }

    public Integer getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(Integer totalSales) {
        this.totalSales = totalSales;
    }

    public Integer getMonthSales() {
        return monthSales;
    }

    public void setMonthSales(Integer monthSales) {
        this.monthSales = monthSales;
    }

    public BigDecimal getPraiseRate() {
        return praiseRate;
    }

    public void setPraiseRate(BigDecimal praiseRate) {
        this.praiseRate = praiseRate;
    }

    public Boolean getSpecial() {
        return special;
    }

    public void setSpecial(Boolean special) {
        this.special = special;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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