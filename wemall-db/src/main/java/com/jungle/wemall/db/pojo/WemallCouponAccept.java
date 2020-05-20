package com.jungle.wemall.db.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class WemallCouponAccept {
    private Integer id;

    private Integer userId;

    private Integer couponId;

    private String title;

    private BigDecimal targetMoney;

    private BigDecimal discountMoney;

    private String types;

    private String mobile;

    private String status;

    private Date endTime;

    private Date createTime;

    private Date updateTime;

    public WemallCouponAccept(Integer id, Integer userId, Integer couponId, String title, BigDecimal targetMoney, BigDecimal discountMoney, String types, String mobile, String status, Date endTime, Date createTime, Date updateTime) {
        this.id = id;
        this.userId = userId;
        this.couponId = couponId;
        this.title = title;
        this.targetMoney = targetMoney;
        this.discountMoney = discountMoney;
        this.types = types;
        this.mobile = mobile;
        this.status = status;
        this.endTime = endTime;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public WemallCouponAccept() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
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

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types == null ? null : types.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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