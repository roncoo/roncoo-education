package com.roncoo.education.course.service.dao.impl.mapper.entity;

import java.io.Serializable;
import java.util.Date;

public class Adv implements Serializable {
    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    private Integer statusId;

    private Integer sort;

    private String advTitle;

    private String advImg;

    private String advUrl;

    private String advTarget;

    private Integer advLocation;

    private Date beginTime;

    private Date endTime;

    private Integer platShow;

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

    public String getAdvTitle() {
        return advTitle;
    }

    public void setAdvTitle(String advTitle) {
        this.advTitle = advTitle == null ? null : advTitle.trim();
    }

    public String getAdvImg() {
        return advImg;
    }

    public void setAdvImg(String advImg) {
        this.advImg = advImg == null ? null : advImg.trim();
    }

    public String getAdvUrl() {
        return advUrl;
    }

    public void setAdvUrl(String advUrl) {
        this.advUrl = advUrl == null ? null : advUrl.trim();
    }

    public String getAdvTarget() {
        return advTarget;
    }

    public void setAdvTarget(String advTarget) {
        this.advTarget = advTarget == null ? null : advTarget.trim();
    }

    public Integer getAdvLocation() {
        return advLocation;
    }

    public void setAdvLocation(Integer advLocation) {
        this.advLocation = advLocation;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getPlatShow() {
        return platShow;
    }

    public void setPlatShow(Integer platShow) {
        this.platShow = platShow;
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
        sb.append(", advTitle=").append(advTitle);
        sb.append(", advImg=").append(advImg);
        sb.append(", advUrl=").append(advUrl);
        sb.append(", advTarget=").append(advTarget);
        sb.append(", advLocation=").append(advLocation);
        sb.append(", beginTime=").append(beginTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", platShow=").append(platShow);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}