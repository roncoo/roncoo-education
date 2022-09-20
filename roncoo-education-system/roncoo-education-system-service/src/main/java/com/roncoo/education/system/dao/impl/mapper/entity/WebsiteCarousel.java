package com.roncoo.education.system.dao.impl.mapper.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class WebsiteCarousel implements Serializable {
    private Long id;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;

    private Integer statusId;

    private Integer sort;

    private String carouselTitle;

    private String carouselImg;

    private String carouselUrl;

    private String carouselTarget;

    private LocalDateTime beginTime;

    private LocalDateTime endTime;

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

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getCarouselTitle() {
        return carouselTitle;
    }

    public void setCarouselTitle(String carouselTitle) {
        this.carouselTitle = carouselTitle == null ? null : carouselTitle.trim();
    }

    public String getCarouselImg() {
        return carouselImg;
    }

    public void setCarouselImg(String carouselImg) {
        this.carouselImg = carouselImg == null ? null : carouselImg.trim();
    }

    public String getCarouselUrl() {
        return carouselUrl;
    }

    public void setCarouselUrl(String carouselUrl) {
        this.carouselUrl = carouselUrl == null ? null : carouselUrl.trim();
    }

    public String getCarouselTarget() {
        return carouselTarget;
    }

    public void setCarouselTarget(String carouselTarget) {
        this.carouselTarget = carouselTarget == null ? null : carouselTarget.trim();
    }

    public LocalDateTime getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(LocalDateTime beginTime) {
        this.beginTime = beginTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
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
        sb.append(", carouselTitle=").append(carouselTitle);
        sb.append(", carouselImg=").append(carouselImg);
        sb.append(", carouselUrl=").append(carouselUrl);
        sb.append(", carouselTarget=").append(carouselTarget);
        sb.append(", beginTime=").append(beginTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}