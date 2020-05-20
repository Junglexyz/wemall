package com.jungle.wemall.db.pojo;

import java.util.Date;

public class WemallAd {
    private Integer id;

    private String name;

    private String link;

    private String imgUrl;

    private String position;

    private String content;

    private Date startTime;

    private Date endTime;

    private Boolean enabled;

    private Date createTime;

    private Date updateTime;

    private Boolean deleted;

    public WemallAd(Integer id, String name, String link, String imgUrl, String position, String content, Date startTime, Date endTime, Boolean enabled, Date createTime, Date updateTime, Boolean deleted) {
        this.id = id;
        this.name = name;
        this.link = link;
        this.imgUrl = imgUrl;
        this.position = position;
        this.content = content;
        this.startTime = startTime;
        this.endTime = endTime;
        this.enabled = enabled;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.deleted = deleted;
    }

    public WemallAd() {
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link == null ? null : link.trim();
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
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

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
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
}