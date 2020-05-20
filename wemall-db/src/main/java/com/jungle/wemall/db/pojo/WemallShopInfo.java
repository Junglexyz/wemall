package com.jungle.wemall.db.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class WemallShopInfo {
    private Integer id;

    private String shopname;

    private String contactMan;

    private String contactMobile;

    private String beginTime;

    private String endTime;

    private String storeImg;

    private String instoreImg;

    private String logoImg;

    private String longitude;

    private String latitude;

    private String province;

    private String city;

    private String district;

    private String address;

    private String street;

    private String mobile;

    private String email;

    private String images;

    private String notice;

    private String brief;

    private BigDecimal score;

    private String sendTime;

    private BigDecimal sendCost;

    private BigDecimal floorSendCost;

    private Date createTime;

    private Date updateTime;

    public WemallShopInfo(Integer id, String shopname, String contactMan, String contactMobile, String beginTime, String endTime, String storeImg, String instoreImg, String logoImg, String longitude, String latitude, String province, String city, String district, String address, String street, String mobile, String email, String images, String notice, String brief, BigDecimal score, String sendTime, BigDecimal sendCost, BigDecimal floorSendCost, Date createTime, Date updateTime) {
        this.id = id;
        this.shopname = shopname;
        this.contactMan = contactMan;
        this.contactMobile = contactMobile;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.storeImg = storeImg;
        this.instoreImg = instoreImg;
        this.logoImg = logoImg;
        this.longitude = longitude;
        this.latitude = latitude;
        this.province = province;
        this.city = city;
        this.district = district;
        this.address = address;
        this.street = street;
        this.mobile = mobile;
        this.email = email;
        this.images = images;
        this.notice = notice;
        this.brief = brief;
        this.score = score;
        this.sendTime = sendTime;
        this.sendCost = sendCost;
        this.floorSendCost = floorSendCost;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public WemallShopInfo() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname == null ? null : shopname.trim();
    }

    public String getContactMan() {
        return contactMan;
    }

    public void setContactMan(String contactMan) {
        this.contactMan = contactMan == null ? null : contactMan.trim();
    }

    public String getContactMobile() {
        return contactMobile;
    }

    public void setContactMobile(String contactMobile) {
        this.contactMobile = contactMobile == null ? null : contactMobile.trim();
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime == null ? null : beginTime.trim();
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime == null ? null : endTime.trim();
    }

    public String getStoreImg() {
        return storeImg;
    }

    public void setStoreImg(String storeImg) {
        this.storeImg = storeImg == null ? null : storeImg.trim();
    }

    public String getInstoreImg() {
        return instoreImg;
    }

    public void setInstoreImg(String instoreImg) {
        this.instoreImg = instoreImg == null ? null : instoreImg.trim();
    }

    public String getLogoImg() {
        return logoImg;
    }

    public void setLogoImg(String logoImg) {
        this.logoImg = logoImg == null ? null : logoImg.trim();
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude == null ? null : longitude.trim();
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude == null ? null : latitude.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district == null ? null : district.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street == null ? null : street.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images == null ? null : images.trim();
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice == null ? null : notice.trim();
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief == null ? null : brief.trim();
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime == null ? null : sendTime.trim();
    }

    public BigDecimal getSendCost() {
        return sendCost;
    }

    public void setSendCost(BigDecimal sendCost) {
        this.sendCost = sendCost;
    }

    public BigDecimal getFloorSendCost() {
        return floorSendCost;
    }

    public void setFloorSendCost(BigDecimal floorSendCost) {
        this.floorSendCost = floorSendCost;
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