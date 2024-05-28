package com.roncoo.education.user.dao.impl.mapper.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Users implements Serializable {
    private Long id;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;

    private Integer statusId;

    private String mobile;

    private String mobileSalt;

    private String mobilePsw;

    private String nickname;

    private Integer userSex;

    private Integer userAge;

    private String userHead;

    private String remark;

    private String unionId;

    private String openId;

    private String city;

    private String province;

    private String country;

    private Integer registerSource;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(LocalDateTime gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public LocalDateTime getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(LocalDateTime gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getMobileSalt() {
        return mobileSalt;
    }

    public void setMobileSalt(String mobileSalt) {
        this.mobileSalt = mobileSalt == null ? null : mobileSalt.trim();
    }

    public String getMobilePsw() {
        return mobilePsw;
    }

    public void setMobilePsw(String mobilePsw) {
        this.mobilePsw = mobilePsw == null ? null : mobilePsw.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public Integer getUserSex() {
        return userSex;
    }

    public void setUserSex(Integer userSex) {
        this.userSex = userSex;
    }

    public Integer getUserAge() {
        return userAge;
    }

    public void setUserAge(Integer userAge) {
        this.userAge = userAge;
    }

    public String getUserHead() {
        return userHead;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead == null ? null : userHead.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId == null ? null : unionId.trim();
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public Integer getRegisterSource() {
        return registerSource;
    }

    public void setRegisterSource(Integer registerSource) {
        this.registerSource = registerSource;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", gmtCreate=").append(gmtCreate);
        sb.append(", gmtModified=").append(gmtModified);
        sb.append(", statusId=").append(statusId);
        sb.append(", mobile=").append(mobile);
        sb.append(", mobileSalt=").append(mobileSalt);
        sb.append(", mobilePsw=").append(mobilePsw);
        sb.append(", nickname=").append(nickname);
        sb.append(", userSex=").append(userSex);
        sb.append(", userAge=").append(userAge);
        sb.append(", userHead=").append(userHead);
        sb.append(", remark=").append(remark);
        sb.append(", unionId=").append(unionId);
        sb.append(", openId=").append(openId);
        sb.append(", city=").append(city);
        sb.append(", province=").append(province);
        sb.append(", country=").append(country);
        sb.append(", registerSource=").append(registerSource);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}