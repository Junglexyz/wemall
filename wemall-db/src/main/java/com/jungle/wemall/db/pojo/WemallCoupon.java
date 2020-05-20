package com.jungle.wemall.db.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class WemallCoupon {
    private Integer couponId;

    private String types;

    private String title;

    private BigDecimal targetMoney;

    private BigDecimal discountMoney;

    private Date startTime;

    private Date endTime;

    private Integer number;

    private String status;

    private Date createTime;

    private Date updateTime;

    public WemallCoupon(Integer couponId, String types, String title, BigDecimal targetMoney, BigDecimal discountMoney, Date startTime, Date endTime, Integer number, String status, Date createTime, Date updateTime) {
        this.couponId = couponId;
        this.types = types;
        this.title = title;
        this.targetMoney = targetMoney;
        this.discountMoney = discountMoney;
        this.startTime = startTime;
        this.endTime = endTime;
        this.number = number;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public WemallCoupon() {
        super();
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types == null ? null : types.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public BigDecimal getTargetMoney() {
        return targetMoney;
    }

    public void setTargetMoney(BigDecimal targetMoney) {
        this.targetMoney = targetMoney;
    }

    public BigDecimal getDiscountMoney() {
        return discountMoney;
    }

    public void setDiscountMoney(BigDecimal discountMoney) {
        this.discountMoney = discountMoney;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
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