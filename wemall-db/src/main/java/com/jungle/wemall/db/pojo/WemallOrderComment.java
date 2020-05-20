package com.jungle.wemall.db.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class WemallOrderComment {
    private Integer id;

    private String orderId;

    private Integer userId;

    private String content;

    private String images;

    private String status;

    private BigDecimal star;

    private Date createTime;

    private Date updateTime;

    public WemallOrderComment(Integer id, String orderId, Integer userId, String content, String images, String status, BigDecimal star, Date createTime, Date updateTime) {
        this.id = id;
        this.orderId = orderId;
        this.userId = userId;
        this.content = content;
        this.images = images;
        this.status = status;
        this.star = star;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public WemallOrderComment() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images == null ? null : images.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public BigDecimal getStar() {
        return star;
    }

    public void setStar(BigDecimal star) {
        this.star = star;
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