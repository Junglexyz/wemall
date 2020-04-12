package com.jungle.wemall.db.pojo;

import java.util.List;

public class Category {
    private Integer id;

    private Integer shopId;

    private String name;

    private String cover;

    private String describle;

    private Byte status;

    private Integer createTime;

    public List<ChildCategory> getListChildCategory() {
        return listChildCategory;
    }

    public void setListChildCategory(List<ChildCategory> listChildCategory) {
        this.listChildCategory = listChildCategory;
    }

    private List<ChildCategory> listChildCategory;

    public Category(Integer id, Integer shopId, String name, String cover, String describle, Byte status, Integer createTime) {
        this.id = id;
        this.shopId = shopId;
        this.name = name;
        this.cover = cover;
        this.describle = describle;
        this.status = status;
        this.createTime = createTime;
    }

    public Category(Integer id, Integer shopId, String name, String cover, String describle, Byte status, Integer createTime, List<ChildCategory> listChildCategory) {
        this.id = id;
        this.shopId = shopId;
        this.name = name;
        this.cover = cover;
        this.describle = describle;
        this.status = status;
        this.createTime = createTime;
        this.listChildCategory = listChildCategory;
    }

    public Category() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover == null ? null : cover.trim();
    }

    public String getDescrible() {
        return describle;
    }

    public void setDescrible(String describle) {
        this.describle = describle == null ? null : describle.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", shopId=" + shopId +
                ", name='" + name + '\'' +
                ", cover='" + cover + '\'' +
                ", describle='" + describle + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                ", childCategory=" + listChildCategory +
                '}';
    }
}