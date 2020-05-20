package com.jungle.wemall.db.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class WemallOrderRefund {
    private Integer id;

    private String orderId;

    private Integer userId;

    private String username;

    private BigDecimal payMoney;

    private String refundReason;

    private Date handleTime;

    private Date finishTime;

    private Date createTime;

    private Date updateTime;

    private String status;

    public WemallOrderRefund(Integer id, String orderId, Integer userId, String username, BigDecimal payMoney, String refundReason, Date handleTime, Date finishTime, Date createTime, Date updateTime, String status) {
        this.id = id;
        this.orderId = orderId;
        this.userId = userId;
        this.username = username;
        this.payMoney = payMoney;
        this.refundReason = refundReason;
        this.handleTime = handleTime;
        this.finishTime = finishTime;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.status = status;
    }

    public WemallOrderRefund() {
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public BigDecimal getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(BigDecimal payMoney) {
        this.payMoney = payMoney;
    }

    public String getRefundReason() {
        return refundReason;
    }

    public void setRefundReason(String refundReason) {
        this.refundReason = refundReason == null ? null : refundReason.trim();
    }

    public Date getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(Date handleTime) {
        this.handleTime = handleTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}