package com.jungle.wemall.db.pojo;

public class ChildCategory {
    private Integer id;

    private Byte type;

    private String childName;

    private String childCover;

    private Integer parentId;

    private String childDescrible;

    private Byte childStatus;

    private Integer createTime;

    public ChildCategory(Integer id, Byte type, String childName, String childCover, Integer parentId, String childDescrible, Byte childStatus, Integer createTime) {
        this.id = id;
        this.type = type;
        this.childName = childName;
        this.childCover = childCover;
        this.parentId = parentId;
        this.childDescrible = childDescrible;
        this.childStatus = childStatus;
        this.createTime = createTime;
    }

    public ChildCategory() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName == null ? null : childName.trim();
    }

    public String getChildCover() {
        return childCover;
    }

    public void setChildCover(String childCover) {
        this.childCover = childCover == null ? null : childCover.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getChildDescrible() {
        return childDescrible;
    }

    public void setChildDescrible(String childDescrible) {
        this.childDescrible = childDescrible == null ? null : childDescrible.trim();
    }

    public Byte getChildStatus() {
        return childStatus;
    }

    public void setChildStatus(Byte childStatus) {
        this.childStatus = childStatus;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "ChildCategory{" +
                "id=" + id +
                ", type=" + type +
                ", childName='" + childName + '\'' +
                ", childCover='" + childCover + '\'' +
                ", parentId=" + parentId +
                ", childDescrible='" + childDescrible + '\'' +
                ", childStatus=" + childStatus +
                ", createTime=" + createTime +
                '}';
    }
}