package com.roncoo.education.user.service.dao.impl.mapper.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Lecturer implements Serializable {
    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    private Integer statusId;

    private Integer sort;

    private Long lecturerUserNo;

    private String lecturerName;

    private String lecturerMobile;

    private String lecturerEmail;

    private String position;

    private String headImgUrl;

    private String introduce;

    private BigDecimal lecturerProportion;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Long getLecturerUserNo() {
        return lecturerUserNo;
    }

    public void setLecturerUserNo(Long lecturerUserNo) {
        this.lecturerUserNo = lecturerUserNo;
    }

    public String getLecturerName() {
        return lecturerName;
    }

    public void setLecturerName(String lecturerName) {
        this.lecturerName = lecturerName == null ? null : lecturerName.trim();
    }

    public String getLecturerMobile() {
        return lecturerMobile;
    }

    public void setLecturerMobile(String lecturerMobile) {
        this.lecturerMobile = lecturerMobile == null ? null : lecturerMobile.trim();
    }

    public String getLecturerEmail() {
        return lecturerEmail;
    }

    public void setLecturerEmail(String lecturerEmail) {
        this.lecturerEmail = lecturerEmail == null ? null : lecturerEmail.trim();
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl == null ? null : headImgUrl.trim();
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
    }

    public BigDecimal getLecturerProportion() {
        return lecturerProportion;
    }

    public void setLecturerProportion(BigDecimal lecturerProportion) {
        this.lecturerProportion = lecturerProportion;
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
        sb.append(", sort=").append(sort);
        sb.append(", lecturerUserNo=").append(lecturerUserNo);
        sb.append(", lecturerName=").append(lecturerName);
        sb.append(", lecturerMobile=").append(lecturerMobile);
        sb.append(", lecturerEmail=").append(lecturerEmail);
        sb.append(", position=").append(position);
        sb.append(", headImgUrl=").append(headImgUrl);
        sb.append(", introduce=").append(introduce);
        sb.append(", lecturerProportion=").append(lecturerProportion);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}