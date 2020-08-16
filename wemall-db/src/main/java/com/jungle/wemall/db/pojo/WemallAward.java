package com.jungle.wemall.db.pojo;

import java.util.Date;

public class WemallAward {
    private Integer id;

    private String title;

    private String sumMoney;

    private Integer count;

    private Integer total;

    private Boolean deleted;

    private Date createTime;

    private Date updateTime;

    public WemallAward(Integer id, String title, String sumMoney, Integer count, Integer total, Boolean deleted, Date createTime, Date updateTime) {
        this.id = id;
        this.title = title;
        this.sumMoney = sumMoney;
        this.count = count;
        this.total = total;
        this.deleted = deleted;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public WemallAward() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getSumMoney() {
        return sumMoney;
    }

    public void setSumMoney(String sumMoney) {
        this.sumMoney = sumMoney == null ? null : sumMoney.trim();
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
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