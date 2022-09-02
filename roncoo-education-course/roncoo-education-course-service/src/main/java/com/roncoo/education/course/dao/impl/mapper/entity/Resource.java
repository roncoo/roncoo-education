package com.roncoo.education.course.dao.impl.mapper.entity;

import java.io.Serializable;
import java.util.Date;

public class Resource implements Serializable {
    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    private Integer statusId;

    private Integer sort;

    private String recourceName;

    private Integer recourceType;

    private Long recourceSize;

    private String recourceUrl;

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

    public String getRecourceName() {
        return recourceName;
    }

    public void setRecourceName(String recourceName) {
        this.recourceName = recourceName == null ? null : recourceName.trim();
    }

    public Integer getRecourceType() {
        return recourceType;
    }

    public void setRecourceType(Integer recourceType) {
        this.recourceType = recourceType;
    }

    public Long getRecourceSize() {
        return recourceSize;
    }

    public void setRecourceSize(Long recourceSize) {
        this.recourceSize = recourceSize;
    }

    public String getRecourceUrl() {
        return recourceUrl;
    }

    public void setRecourceUrl(String recourceUrl) {
        this.recourceUrl = recourceUrl == null ? null : recourceUrl.trim();
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
        sb.append(", recourceName=").append(recourceName);
        sb.append(", recourceType=").append(recourceType);
        sb.append(", recourceSize=").append(recourceSize);
        sb.append(", recourceUrl=").append(recourceUrl);
        sb.append(", videoStatus=").append(videoStatus);
        sb.append(", videoLength=").append(videoLength);
        sb.append(", videoVid=").append(videoVid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}