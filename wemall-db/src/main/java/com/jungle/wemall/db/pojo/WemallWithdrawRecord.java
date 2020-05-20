package com.jungle.wemall.db.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class WemallWithdrawRecord {
    private Integer id;

    private Integer userId;

    private String title;

    private BigDecimal changeMoney;

    private BigDecimal balance;

    private String types;

    private Date createTime;

    private Date updateTime;

    public WemallWithdrawRecord(Integer id, Integer userId, String title, BigDecimal changeMoney, BigDecimal balance, String types, Date createTime, Date updateTime) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.changeMoney = changeMoney;
        this.balance = balance;
        this.types = types;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public WemallWithdrawRecord() {
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public BigDecimal getChangeMoney() {
        return changeMoney;
    }

    public void setChangeMoney(BigDecimal changeMoney) {
        this.changeMoney = changeMoney;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types == null ? null : types.trim();
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