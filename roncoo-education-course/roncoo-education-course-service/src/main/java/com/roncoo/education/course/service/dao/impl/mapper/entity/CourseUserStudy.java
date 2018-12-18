package com.roncoo.education.course.service.dao.impl.mapper.entity;

import java.io.Serializable;
import java.util.Date;

public class CourseUserStudy implements Serializable {
    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    private Integer statusId;

    private Integer sort;

    private Long courseId;

    private Long userNo;

    private Integer periodTotal;

    private Integer periodStudy;

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

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getUserNo() {
        return userNo;
    }

    public void setUserNo(Long userNo) {
        this.userNo = userNo;
    }

    public Integer getPeriodTotal() {
        return periodTotal;
    }

    public void setPeriodTotal(Integer periodTotal) {
        this.periodTotal = periodTotal;
    }

    public Integer getPeriodStudy() {
        return periodStudy;
    }

    public void setPeriodStudy(Integer periodStudy) {
        this.periodStudy = periodStudy;
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
        sb.append(", courseId=").append(courseId);
        sb.append(", userNo=").append(userNo);
        sb.append(", periodTotal=").append(periodTotal);
        sb.append(", periodStudy=").append(periodStudy);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}