package com.roncoo.education.user.service.dao.impl.mapper.entity;

import java.io.Serializable;
import java.util.Date;

public class UserLogModified implements Serializable {
    private Long id;

    private Date gmtCreate;

    private Long userNo;

    private String mobileOld;

    private String mobileNew;

    private String remark;

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

    public Long getUserNo() {
        return userNo;
    }

    public void setUserNo(Long userNo) {
        this.userNo = userNo;
    }

    public String getMobileOld() {
        return mobileOld;
    }

    public void setMobileOld(String mobileOld) {
        this.mobileOld = mobileOld == null ? null : mobileOld.trim();
    }

    public String getMobileNew() {
        return mobileNew;
    }

    public void setMobileNew(String mobileNew) {
        this.mobileNew = mobileNew == null ? null : mobileNew.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", gmtCreate=").append(gmtCreate);
        sb.append(", userNo=").append(userNo);
        sb.append(", mobileOld=").append(mobileOld);
        sb.append(", mobileNew=").append(mobileNew);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}