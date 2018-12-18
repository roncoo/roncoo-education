package com.roncoo.education.course.service.dao.impl.mapper.entity;

import java.io.Serializable;
import java.util.Date;

public class Zone implements Serializable {
    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    private Integer statusId;

    private Integer sort;

    private String zoneName;

    private String zoneDesc;

    private Integer zoneLocation;

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

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName == null ? null : zoneName.trim();
    }

    public String getZoneDesc() {
        return zoneDesc;
    }

    public void setZoneDesc(String zoneDesc) {
        this.zoneDesc = zoneDesc == null ? null : zoneDesc.trim();
    }

    public Integer getZoneLocation() {
        return zoneLocation;
    }

    public void setZoneLocation(Integer zoneLocation) {
        this.zoneLocation = zoneLocation;
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
        sb.append(", zoneName=").append(zoneName);
        sb.append(", zoneDesc=").append(zoneDesc);
        sb.append(", zoneLocation=").append(zoneLocation);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}