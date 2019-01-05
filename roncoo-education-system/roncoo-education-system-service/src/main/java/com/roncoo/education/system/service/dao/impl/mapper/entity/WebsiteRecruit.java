package com.roncoo.education.system.service.dao.impl.mapper.entity;

import java.io.Serializable;
import java.util.Date;

public class WebsiteRecruit implements Serializable {
    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    private Integer statusId;

    private Integer sort;

    private Integer recruitType;

    private String recruitTitle;

    private String recruitInfo;

    private String entryAgreement;

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

    public Integer getRecruitType() {
        return recruitType;
    }

    public void setRecruitType(Integer recruitType) {
        this.recruitType = recruitType;
    }

    public String getRecruitTitle() {
        return recruitTitle;
    }

    public void setRecruitTitle(String recruitTitle) {
        this.recruitTitle = recruitTitle == null ? null : recruitTitle.trim();
    }

    public String getRecruitInfo() {
        return recruitInfo;
    }

    public void setRecruitInfo(String recruitInfo) {
        this.recruitInfo = recruitInfo == null ? null : recruitInfo.trim();
    }

    public String getEntryAgreement() {
        return entryAgreement;
    }

    public void setEntryAgreement(String entryAgreement) {
        this.entryAgreement = entryAgreement == null ? null : entryAgreement.trim();
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
        sb.append(", recruitType=").append(recruitType);
        sb.append(", recruitTitle=").append(recruitTitle);
        sb.append(", recruitInfo=").append(recruitInfo);
        sb.append(", entryAgreement=").append(entryAgreement);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}