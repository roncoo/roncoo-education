package com.roncoo.education.course.dao.impl.mapper.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Resource implements Serializable {
    private Long id;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;

    private Integer statusId;

    private Integer sort;

    private String resourceName;

    private Integer resourceType;

    private Long resourceSize;

    private String resourceUrl;

    private Integer vodPlatform;

    private Integer storagePlatform;

    private Integer videoStatus;

    private Integer videoLength;

    private String videoVid;

    private Integer docPage;

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

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName == null ? null : resourceName.trim();
    }

    public Integer getResourceType() {
        return resourceType;
    }

    public void setResourceType(Integer resourceType) {
        this.resourceType = resourceType;
    }

    public Long getResourceSize() {
        return resourceSize;
    }

    public void setResourceSize(Long resourceSize) {
        this.resourceSize = resourceSize;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl == null ? null : resourceUrl.trim();
    }

    public Integer getVodPlatform() {
        return vodPlatform;
    }

    public void setVodPlatform(Integer vodPlatform) {
        this.vodPlatform = vodPlatform;
    }

    public Integer getStoragePlatform() {
        return storagePlatform;
    }

    public void setStoragePlatform(Integer storagePlatform) {
        this.storagePlatform = storagePlatform;
    }

    public Integer getVideoStatus() {
        return videoStatus;
    }

    public void setVideoStatus(Integer videoStatus) {
        this.videoStatus = videoStatus;
    }

    public Integer getVideoLength() {
        return videoLength;
    }

    public void setVideoLength(Integer videoLength) {
        this.videoLength = videoLength;
    }

    public String getVideoVid() {
        return videoVid;
    }

    public void setVideoVid(String videoVid) {
        this.videoVid = videoVid == null ? null : videoVid.trim();
    }

    public Integer getDocPage() {
        return docPage;
    }

    public void setDocPage(Integer docPage) {
        this.docPage = docPage;
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
        sb.append(", resourceName=").append(resourceName);
        sb.append(", resourceType=").append(resourceType);
        sb.append(", resourceSize=").append(resourceSize);
        sb.append(", resourceUrl=").append(resourceUrl);
        sb.append(", vodPlatform=").append(vodPlatform);
        sb.append(", storagePlatform=").append(storagePlatform);
        sb.append(", videoStatus=").append(videoStatus);
        sb.append(", videoLength=").append(videoLength);
        sb.append(", videoVid=").append(videoVid);
        sb.append(", docPage=").append(docPage);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}