package com.jungle.wemall.db.pojo;

import java.util.Date;

public class WemallPrize {
    private Integer id;

    private Integer awardId;

    private String grade;

    private Integer couponId;

    private String userTel;

    private Integer userId;

    private Boolean used;

    private Date createTime;

    private Date updateTime;

    private Integer count;

    public WemallPrize(Integer id, Integer awardId, String grade, Integer couponId, String userTel, Integer userId, Boolean used, Date createTime, Date updateTime) {
        this.id = id;
        this.awardId = awardId;
        this.grade = grade;
        this.couponId = couponId;
        this.userTel = userTel;
        this.userId = userId;
        this.used = used;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public WemallPrize() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAwardId() {
        return awardId;
    }

    public void setAwardId(Integer awardId) {
        this.awardId = awardId;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade == null ? null : grade.trim();
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel == null ? null : userTel.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Boolean getUsed() {
        return used;
    }

    public void setUsed(Boolean used) {
        this.used = used;
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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}