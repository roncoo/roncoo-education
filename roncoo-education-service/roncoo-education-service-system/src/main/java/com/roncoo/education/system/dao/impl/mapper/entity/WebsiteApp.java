package com.roncoo.education.system.dao.impl.mapper.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class WebsiteApp implements Serializable {
    private Long id;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;

    private Integer statusId;

    private Integer sort;

    private LocalDateTime publishTime;

    private Integer appType;

    private String appVersion;

    private Integer updateForce;

    private String updateUrl;

    private String updateTips;

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

    public LocalDateTime getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(LocalDateTime publishTime) {
        this.publishTime = publishTime;
    }

    public Integer getAppType() {
        return appType;
    }

    public void setAppType(Integer appType) {
        this.appType = appType;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion == null ? null : appVersion.trim();
    }

    public Integer getUpdateForce() {
        return updateForce;
    }

    public void setUpdateForce(Integer updateForce) {
        this.updateForce = updateForce;
    }

    public String getUpdateUrl() {
        return updateUrl;
    }

    public void setUpdateUrl(String updateUrl) {
        this.updateUrl = updateUrl == null ? null : updateUrl.trim();
    }

    public String getUpdateTips() {
        return updateTips;
    }

    public void setUpdateTips(String updateTips) {
        this.updateTips = updateTips == null ? null : updateTips.trim();
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
        sb.append(", publishTime=").append(publishTime);
        sb.append(", appType=").append(appType);
        sb.append(", appVersion=").append(appVersion);
        sb.append(", updateForce=").append(updateForce);
        sb.append(", updateUrl=").append(updateUrl);
        sb.append(", updateTips=").append(updateTips);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}