package com.jungle.wemall.db.pojo;

public class ShopInfo {
    private Integer id;

    private Integer shopId;

    private String tag;

    private String shopname;

    private String contactMan;

    private String contactMobile;

    private Integer cateid;

    private Integer beginTime;

    private Integer endTime;

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

    private Float score;

    private String sendTime;

    private Long boxCost;

    private Long sendCost;

    private Long floorSendCost;

    public ShopInfo(Integer id, Integer shopId, String tag, String shopname, String contactMan, String contactMobile, Integer cateid, Integer beginTime, Integer endTime, String storeImg, String instoreImg, String logoImg, String longitude, String latitude, String province, String city, String district, String address, String street, String mobile, String email, String images, String notice, Float score, String sendTime, Long boxCost, Long sendCost, Long floorSendCost) {
        this.id = id;
        this.shopId = shopId;
        this.tag = tag;
        this.shopname = shopname;
        this.contactMan = contactMan;
        this.contactMobile = contactMobile;
        this.cateid = cateid;
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
        this.score = score;
        this.sendTime = sendTime;
        this.boxCost = boxCost;
        this.sendCost = sendCost;
        this.floorSendCost = floorSendCost;
    }

    public ShopInfo() {
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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
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

    public Integer getCateid() {
        return cateid;
    }

    public void setCateid(Integer cateid) {
        this.cateid = cateid;
    }

    public Integer getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Integer beginTime) {
        this.beginTime = beginTime;
    }

    public Integer getEndTime() {
        return endTime;
    }

    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
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

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime == null ? null : sendTime.trim();
    }

    public Long getBoxCost() {
        return boxCost;
    }

    public void setBoxCost(Long boxCost) {
        this.boxCost = boxCost;
    }

    public Long getSendCost() {
        return sendCost;
    }

    public void setSendCost(Long sendCost) {
        this.sendCost = sendCost;
    }

    public Long getFloorSendCost() {
        return floorSendCost;
    }

    public void setFloorSendCost(Long floorSendCost) {
        this.floorSendCost = floorSendCost;
    }
}