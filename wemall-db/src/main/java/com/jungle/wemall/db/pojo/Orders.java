package com.jungle.wemall.db.pojo;

import java.util.List;

public class Orders {
    private Integer id;

    private String orderId;

    private Integer userId;

    private Integer shopId;

    private Long boxCost;

    private Long sendCost;

    private Long totalMoney;

    private Long discountMoney;

    private String couponId;

    private Long couponMoney;

    private Long payMoney;

    private Byte payWay;

    private Integer demandTime;

    private Integer createTime;

    private char status;

    private List<OrdersDetail> ordersDetail;

    public Orders(Integer id, String orderId, Integer userId, Integer shopId, Long boxCost, Long sendCost, Long totalMoney, Long discountMoney, String couponId, Long couponMoney, Long payMoney, Byte payWay, Integer demandTime, Integer createTime, char status) {
        this.id = id;
        this.orderId = orderId;
        this.userId = userId;
        this.shopId = shopId;
        this.boxCost = boxCost;
        this.sendCost = sendCost;
        this.totalMoney = totalMoney;
        this.discountMoney = discountMoney;
        this.couponId = couponId;
        this.couponMoney = couponMoney;
        this.payMoney = payMoney;
        this.payWay = payWay;
        this.demandTime = demandTime;
        this.createTime = createTime;
        this.status = status;
    }

    public Orders(Integer id, String orderId, Integer userId, Integer shopId, Long boxCost, Long sendCost, Long totalMoney, Long discountMoney, String couponId, Long couponMoney, Long payMoney, Byte payWay, Integer demandTime, Integer createTime, char status, List<OrdersDetail> ordersDetail) {
        this.id = id;
        this.orderId = orderId;
        this.userId = userId;
        this.shopId = shopId;
        this.boxCost = boxCost;
        this.sendCost = sendCost;
        this.totalMoney = totalMoney;
        this.discountMoney = discountMoney;
        this.couponId = couponId;
        this.couponMoney = couponMoney;
        this.payMoney = payMoney;
        this.payWay = payWay;
        this.demandTime = demandTime;
        this.createTime = createTime;
        this.status = status;
        this.ordersDetail = ordersDetail;
    }

    public Orders() {
        super();
    }

    public List<OrdersDetail> getOrdersDetail() {
        return ordersDetail;
    }

    public void setOrdersDetail(List<OrdersDetail> ordersDetail) {
        this.ordersDetail = ordersDetail;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Long getBoxCost() {
        return boxCost;
    }

    public void setBoxCost(Long boxCost) {
        this.boxCost = boxCost;
    }

    public Long getSendCost() {
        return sendCost;
    }

    public void setSendCost(Long sendCost) {
        this.sendCost = sendCost;
    }

    public Long getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Long totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Long getDiscountMoney() {
        return discountMoney;
    }

    public void setDiscountMoney(Long discountMoney) {
        this.discountMoney = discountMoney;
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId == null ? null : couponId.trim();
    }

    public Long getCouponMoney() {
        return couponMoney;
    }

    public void setCouponMoney(Long couponMoney) {
        this.couponMoney = couponMoney;
    }

    public Long getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(Long payMoney) {
        this.payMoney = payMoney;
    }

    public Byte getPayWay() {
        return payWay;
    }

    public void setPayWay(Byte payWay) {
        this.payWay = payWay;
    }

    public Integer getDemandTime() {
        return demandTime;
    }

    public void setDemandTime(Integer demandTime) {
        this.demandTime = demandTime;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", orderId='" + orderId + '\'' +
                ", userId=" + userId +
                ", shopId=" + shopId +
                ", boxCost=" + boxCost +
                ", sendCost=" + sendCost +
                ", totalMoney=" + totalMoney +
                ", discountMoney=" + discountMoney +
                ", couponId='" + couponId + '\'' +
                ", couponMoney=" + couponMoney +
                ", payMoney=" + payMoney +
                ", payWay=" + payWay +
                ", demandTime=" + demandTime +
                ", createTime=" + createTime +
                ", status=" + status +
                ", ordersDetail=" + ordersDetail +
                '}';
    }
}