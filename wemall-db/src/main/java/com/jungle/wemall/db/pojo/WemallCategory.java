package com.jungle.wemall.db.pojo;

import java.util.Date;
import java.util.List;

public class WemallCategory {
    private Integer id;

    private String name;

    private Integer pid;

    private String iconUrl;

    private String picUrl;

    private String level;

    private Integer sortOrder;

    private Date createTime;

    private Date updateTime;

    private Boolean deleted;

    private String coupon;

    private List<WemallCategory> children;

    public WemallCategory(Integer id, String name, Integer pid, String iconUrl, String picUrl, String level, Integer sortOrder, Date createTime, Date updateTime, Boolean deleted, String coupon) {
        this.id = id;
        this.name = name;
        this.pid = pid;
        this.iconUrl = iconUrl;
        this.picUrl = picUrl;
        this.level = level;
        this.sortOrder = sortOrder;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.deleted = deleted;
        this.coupon = coupon;
    }

    public WemallCategory() {
        super();
    }

    public List<WemallCategory> getChildren() {
        return children;
    }

    public void setChildren(List<WemallCategory> children) {
        this.children = children;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl == null ? null : iconUrl.trim();
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl == null ? null : picUrl.trim();
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
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

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getCoupon() {
        return coupon;
    }

    public void setCoupon(String coupon) {
        this.coupon = coupon == null ? null : coupon.trim();
    }
}