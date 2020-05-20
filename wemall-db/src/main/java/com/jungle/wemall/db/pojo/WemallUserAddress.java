package com.jungle.wemall.db.pojo;

import java.util.Date;

public class WemallUserAddress {
    private Integer id;

    private Integer userId;

    private String username;

    private String gender;

    private String mobile;

    private String province;

    private String city;

    private String district;

    private String county;

    private String town;

    private String village;

    private String groups;

    private String team;

    private String longitude;

    private String latitude;

    private String address;

    private String street;

    private Byte tag;

    private String defaulted;

    private Boolean deleted;

    private Date createTime;

    private Date updateTime;

    public WemallUserAddress(Integer id, Integer userId, String username, String gender, String mobile, String province, String city, String district, String county, String town, String village, String groups, String team, String longitude, String latitude, String address, String street, Byte tag, String defaulted, Boolean deleted, Date createTime, Date updateTime) {
        this.id = id;
        this.userId = userId;
        this.username = username;
        this.gender = gender;
        this.mobile = mobile;
        this.province = province;
        this.city = city;
        this.district = district;
        this.county = county;
        this.town = town;
        this.village = village;
        this.groups = groups;
        this.team = team;
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = address;
        this.street = street;
        this.tag = tag;
        this.defaulted = defaulted;
        this.deleted = deleted;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public WemallUserAddress() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
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

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county == null ? null : county.trim();
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town == null ? null : town.trim();
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village == null ? null : village.trim();
    }

    public String getGroups() {
        return groups;
    }

    public void setGroups(String groups) {
        this.groups = groups == null ? null : groups.trim();
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team == null ? null : team.trim();
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

    public Byte getTag() {
        return tag;
    }

    public void setTag(Byte tag) {
        this.tag = tag;
    }

    public String getDefaulted() {
        return defaulted;
    }

    public void setDefaulted(String defaulted) {
        this.defaulted = defaulted == null ? null : defaulted.trim();
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
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