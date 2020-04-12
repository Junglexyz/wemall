package com.jungle.wemall.db.pojo;

public class OrdersDetail {
    private Integer id;

    private String orderId;

    private Integer shopId;

    private String shopname;

    private Integer foodId;

    private String title;

    private String cover;

    private Long originPrice;

    private Long sellPrice;

    private Long discountMoney;

    private Integer number;

    public OrdersDetail(Integer id, String orderId, Integer shopId, String shopname, Integer foodId, String title, String cover, Long originPrice, Long sellPrice, Long discountMoney, Integer number) {
        this.id = id;
        this.orderId = orderId;
        this.shopId = shopId;
        this.shopname = shopname;
        this.foodId = foodId;
        this.title = title;
        this.cover = cover;
        this.originPrice = originPrice;
        this.sellPrice = sellPrice;
        this.discountMoney = discountMoney;
        this.number = number;
    }

    public OrdersDetail() {
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

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname == null ? null : shopname.trim();
    }

    public Integer getFoodId() {
        return foodId;
    }

    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
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

    public Long getDiscountMoney() {
        return discountMoney;
    }

    public void setDiscountMoney(Long discountMoney) {
        this.discountMoney = discountMoney;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "OrdersDetail{" +
                "id=" + id +
                ", orderId='" + orderId + '\'' +
                ", shopId=" + shopId +
                ", shopname='" + shopname + '\'' +
                ", foodId=" + foodId +
                ", title='" + title + '\'' +
                ", cover='" + cover + '\'' +
                ", originPrice=" + originPrice +
                ", sellPrice=" + sellPrice +
                ", discountMoney=" + discountMoney +
                ", number=" + number +
                '}';
    }
}