package com.jungle.wemall.db.pojo;

import java.util.Date;

public class WemallRole {
    private Integer id;

    private String name;

    private String summary;

    private Boolean enabled;

    private Date addTime;

    private Date updateTime;

    private Boolean deleted;

    public WemallRole(Integer id, String name, String summary, Boolean enabled, Date addTime, Date updateTime, Boolean deleted) {
        this.id = id;
        this.name = name;
        this.summary = summary;
        this.enabled = enabled;
        this.addTime = addTime;
        this.updateTime = updateTime;
        this.deleted = deleted;
    }

    public WemallRole() {
        super();
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

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
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
}