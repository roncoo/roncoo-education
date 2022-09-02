package com.roncoo.education.course.dao.impl.mapper.entity;

import java.io.Serializable;
import java.util.Date;

public class Resource implements Serializable {
    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    private Integer statusId;

    private Integer sort;

    private String recourseName;

    private Integer recourseType;

    private Long recourseSize;

    private String recourseUrl;

    private Integer videoStatus;

    private String videoLength;

    private String videoVid;

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

    public String getRecourseName() {
        return recourseName;
    }

    public void setRecourseName(String recourseName) {
        this.recourseName = recourseName == null ? null : recourseName.trim();
    }

    public Integer getRecourseType() {
        return recourseType;
    }

    public void setRecourseType(Integer recourseType) {
        this.recourseType = recourseType;
    }

    public Long getRecourseSize() {
        return recourseSize;
    }

    public void setRecourseSize(Long recourseSize) {
        this.recourseSize = recourseSize;
    }

    public String getRecourseUrl() {
        return recourseUrl;
    }

    public void setRecourseUrl(String recourseUrl) {
        this.recourseUrl = recourseUrl == null ? null : recourseUrl.trim();
    }

    public Integer getVideoStatus() {
        return videoStatus;
    }

    public void setVideoStatus(Integer videoStatus) {
        this.videoStatus = videoStatus;
    }

    public String getVideoLength() {
        return videoLength;
    }

    public void setVideoLength(String videoLength) {
        this.videoLength = videoLength == null ? null : videoLength.trim();
    }

    public String getVideoVid() {
        return videoVid;
    }

    public void setVideoVid(String videoVid) {
        this.videoVid = videoVid == null ? null : videoVid.trim();
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
        sb.append(", recourseName=").append(recourseName);
        sb.append(", recourseType=").append(recourseType);
        sb.append(", recourseSize=").append(recourseSize);
        sb.append(", recourseUrl=").append(recourseUrl);
        sb.append(", videoStatus=").append(videoStatus);
        sb.append(", videoLength=").append(videoLength);
        sb.append(", videoVid=").append(videoVid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}