package com.jungle.wemall.db.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Address {
    private Integer addressId;

    private Integer userId;

    private String userName;

    private String address;

    private Boolean defaulted;

    private BigDecimal latitude;

    private BigDecimal longitude;

    private Date createTime;

    private Date updateTime;

    public Address(Integer addressId, Integer userId, String userName, String address, Boolean defaulted, BigDecimal latitude, BigDecimal longitude, Date createTime, Date updateTime) {
        this.addressId = addressId;
        this.userId = userId;
        this.userName = userName;
        this.address = address;
        this.defaulted = defaulted;
        this.latitude = latitude;
        this.longitude = longitude;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Address() {
        super();
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Boolean getDefaulted() {
        return defaulted;
    }

    public void setDefaulted(Boolean defaulted) {
        this.defaulted = defaulted;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
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