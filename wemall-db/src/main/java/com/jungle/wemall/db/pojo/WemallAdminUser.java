package com.jungle.wemall.db.pojo;

import java.util.Date;

public class WemallAdminUser {
    private Integer id;

    private String account;

    private String password;

    private String username;

    private String avatar;

    private String roleIds;

    private Integer groupId;

    private String lastLogin;

    private String status;

    private Date createTime;

    private Date updateTime;

    public WemallAdminUser(Integer id, String account, String password, String username, String avatar, String roleIds, Integer groupId, String lastLogin, String status, Date createTime, Date updateTime) {
        this.id = id;
        this.account = account;
        this.password = password;
        this.username = username;
        this.avatar = avatar;
        this.roleIds = roleIds;
        this.groupId = groupId;
        this.lastLogin = lastLogin;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public WemallAdminUser() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds == null ? null : roleIds.trim();
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin == null ? null : lastLogin.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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