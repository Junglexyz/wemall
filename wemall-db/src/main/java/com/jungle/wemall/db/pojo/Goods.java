package com.jungle.wemall.db.pojo;

public class Goods {
    private Integer goodsId;

    private Integer shopId;

    private Integer categoryId;

    private String title;

    private String describle;

    private String cover;

    private Long originPrice;

    private Long sellPrice;

    private Long discount;

    private Long returnCash;

    private Integer liked;

    private Integer limitNum;

    private String options;

    private Integer totalSales;

    private Integer monthSales;

    private Float praiseRate;

    private Byte status;

    private Integer createTime;

    private Integer updateTime;

    public Goods(Integer goodsId, Integer shopId, Integer categoryId, String title, String describle, String cover, Long originPrice, Long sellPrice, Long discount, Long returnCash, Integer liked, Integer limitNum, String options, Integer totalSales, Integer monthSales, Float praiseRate, Byte status, Integer createTime, Integer updateTime) {
        this.goodsId = goodsId;
        this.shopId = shopId;
        this.categoryId = categoryId;
        this.title = title;
        this.describle = describle;
        this.cover = cover;
        this.originPrice = originPrice;
        this.sellPrice = sellPrice;
        this.discount = discount;
        this.returnCash = returnCash;
        this.liked = liked;
        this.limitNum = limitNum;
        this.options = options;
        this.totalSales = totalSales;
        this.monthSales = monthSales;
        this.praiseRate = praiseRate;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Goods() {
        super();
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
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

    public String getDescrible() {
        return describle;
    }

    public void setDescrible(String describle) {
        this.describle = describle == null ? null : describle.trim();
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover == null ? null : cover.trim();
    }

    public Long getOriginPrice() {
        return originPrice;
    }

    public void setOriginPrice(Long originPrice) {
        this.originPrice = originPrice;
    }

    public Long getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Long sellPrice) {
        this.sellPrice = sellPrice;
    }

    public Long getDiscount() {
        return discount;
    }

    public void setDiscount(Long discount) {
        this.discount = discount;
    }

    public Long getReturnCash() {
        return returnCash;
    }

    public void setReturnCash(Long returnCash) {
        this.returnCash = returnCash;
    }

    public Integer getLiked() {
        return liked;
    }

    public void setLiked(Integer liked) {
        this.liked = liked;
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

    public Float getPraiseRate() {
        return praiseRate;
    }

    public void setPraiseRate(Float praiseRate) {
        this.praiseRate = praiseRate;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    public Integer getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Integer updateTime) {
        this.updateTime = updateTime;
    }
}