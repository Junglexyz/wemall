package com.jungle.wemall.db.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class WemallUser {
    private Integer userId;

    private String username;

    private String mobile;

    private String token;

    private String password;

    private String openId;

    private BigDecimal wallet;

    private String email;

    private String truename;

    private String gender;

    private Byte status;

    private Date createTime;

    private Date updateTime;

    public WemallUser(Integer userId, String username, String mobile, String token, String password, String openId, BigDecimal wallet, String email, String truename, String gender, Byte status, Date createTime, Date updateTime) {
        this.userId = userId;
        this.username = username;
        this.mobile = mobile;
        this.token = token;
        this.password = password;
        this.openId = openId;
        this.wallet = wallet;
        this.email = email;
        this.truename = truename;
        this.gender = gender;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public WemallUser() {
        super();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public BigDecimal getWallet() {
        return wallet;
    }

    public void setWallet(BigDecimal wallet) {
        this.wallet = wallet;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getTruename() {
        return truename;
    }

    public void setTruename(String truename) {
        this.truename = truename == null ? null : truename.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
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