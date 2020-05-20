package com.jungle.wemall.db.pojo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class WemallOrder {
    private Integer id;

    private String orderId;

    private Integer userId;

    private Integer addressId;

    private BigDecimal sendCost;

    private Integer totalNumber;

    private BigDecimal totalMoney;

    private BigDecimal discountMoney;

    private String couponId;

    private BigDecimal couponMoney;

    private BigDecimal payMoney;

    private BigDecimal returnMoney;

    private String payWay;

    private String deliveryWay;

    private String packupCode;

    private Integer packNumber;

    private Date dayStamp;

    private Date createTime;

    private Date payTime;

    private Date deliverTime;

    private Date finalTime;

    private Date updateTime;

    private String status;

    private List<WemallOrderDetail> orderDetail;

    public WemallOrder(Integer id, String orderId, Integer userId, Integer addressId, BigDecimal sendCost, Integer totalNumber, BigDecimal totalMoney, BigDecimal discountMoney, String couponId, BigDecimal couponMoney, BigDecimal payMoney, BigDecimal returnMoney, String payWay, String deliveryWay, String packupCode, Integer packNumber, Date dayStamp, Date createTime, Date payTime, Date deliverTime, Date finalTime, Date updateTime, String status) {
        this.id = id;
        this.orderId = orderId;
        this.userId = userId;
        this.addressId = addressId;
        this.sendCost = sendCost;
        this.totalNumber = totalNumber;
        this.totalMoney = totalMoney;
        this.discountMoney = discountMoney;
        this.couponId = couponId;
        this.couponMoney = couponMoney;
        this.payMoney = payMoney;
        this.returnMoney = returnMoney;
        this.payWay = payWay;
        this.deliveryWay = deliveryWay;
        this.packupCode = packupCode;
        this.packNumber = packNumber;
        this.dayStamp = dayStamp;
        this.createTime = createTime;
        this.payTime = payTime;
        this.deliverTime = deliverTime;
        this.finalTime = finalTime;
        this.updateTime = updateTime;
        this.status = status;
    }

    public WemallOrder(Integer id, String orderId, Integer userId, Integer addressId, BigDecimal sendCost, Integer totalNumber, BigDecimal totalMoney, BigDecimal discountMoney, String couponId, BigDecimal couponMoney, BigDecimal payMoney, BigDecimal returnMoney, String payWay, String deliveryWay, String packupCode, Integer packNumber, Date dayStamp, Date createTime, Date payTime, Date deliverTime, Date finalTime, Date updateTime, String status, List<WemallOrderDetail> orderDetail) {
        this.id = id;
        this.orderId = orderId;
        this.userId = userId;
        this.addressId = addressId;
        this.sendCost = sendCost;
        this.totalNumber = totalNumber;
        this.totalMoney = totalMoney;
        this.discountMoney = discountMoney;
        this.couponId = couponId;
        this.couponMoney = couponMoney;
        this.payMoney = payMoney;
        this.returnMoney = returnMoney;
        this.payWay = payWay;
        this.deliveryWay = deliveryWay;
        this.packupCode = packupCode;
        this.packNumber = packNumber;
        this.dayStamp = dayStamp;
        this.createTime = createTime;
        this.payTime = payTime;
        this.deliverTime = deliverTime;
        this.finalTime = finalTime;
        this.updateTime = updateTime;
        this.status = status;
        this.orderDetail = orderDetail;
    }

    public WemallOrder() {
        super();
    }

    public List<WemallOrderDetail> getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(List<WemallOrderDetail> orderDetail) {
        this.orderDetail = orderDetail;
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

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public BigDecimal getSendCost() {
        return sendCost;
    }

    public void setSendCost(BigDecimal sendCost) {
        this.sendCost = sendCost;
    }

    public Integer getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(Integer totalNumber) {
        this.totalNumber = totalNumber;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public BigDecimal getDiscountMoney() {
        return discountMoney;
    }

    public void setDiscountMoney(BigDecimal discountMoney) {
        this.discountMoney = discountMoney;
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId == null ? null : couponId.trim();
    }

    public BigDecimal getCouponMoney() {
        return couponMoney;
    }

    public void setCouponMoney(BigDecimal couponMoney) {
        this.couponMoney = couponMoney;
    }

    public BigDecimal getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(BigDecimal payMoney) {
        this.payMoney = payMoney;
    }

    public BigDecimal getReturnMoney() {
        return returnMoney;
    }

    public void setReturnMoney(BigDecimal returnMoney) {
        this.returnMoney = returnMoney;
    }

    public String getPayWay() {
        return payWay;
    }

    public String getDeliveryWay() {
        return deliveryWay;
    }

    public void setDeliveryWay(String deliveryWay) {
        this.deliveryWay = deliveryWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay == null ? null : payWay.trim();
    }

    public String getPackupCode() {
        return packupCode;
    }

    public void setPackupCode(String packupCode) {
        this.packupCode = packupCode == null ? null : packupCode.trim();
    }

    public Integer getPackNumber() {
        return packNumber;
    }

    public void setPackNumber(Integer packNumber) {
        this.packNumber = packNumber;
    }

    public Date getDayStamp() {
        return dayStamp;
    }

    public void setDayStamp(Date dayStamp) {
        this.dayStamp = dayStamp;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Date getDeliverTime() {
        return deliverTime;
    }

    public void setDeliverTime(Date deliverTime) {
        this.deliverTime = deliverTime;
    }

    public Date getFinalTime() {
        return finalTime;
    }

    public void setFinalTime(Date finalTime) {
        this.finalTime = finalTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}