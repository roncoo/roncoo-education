package com.roncoo.education.course.service.dao.impl.mapper.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class CourseChapter implements Serializable {
    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    private Integer statusId;

    private Integer sort;

    private Long courseId;

    private String chapterName;

    private String chapterDesc;

    private Integer isFree;

    private BigDecimal chapterOriginal;

    private BigDecimal chapterDiscount;

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

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName == null ? null : chapterName.trim();
    }

    public String getChapterDesc() {
        return chapterDesc;
    }

    public void setChapterDesc(String chapterDesc) {
        this.chapterDesc = chapterDesc == null ? null : chapterDesc.trim();
    }

    public Integer getIsFree() {
        return isFree;
    }

    public void setIsFree(Integer isFree) {
        this.isFree = isFree;
    }

    public BigDecimal getChapterOriginal() {
        return chapterOriginal;
    }

    public void setChapterOriginal(BigDecimal chapterOriginal) {
        this.chapterOriginal = chapterOriginal;
    }

    public BigDecimal getChapterDiscount() {
        return chapterDiscount;
    }

    public void setChapterDiscount(BigDecimal chapterDiscount) {
        this.chapterDiscount = chapterDiscount;
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
        sb.append(", chapterName=").append(chapterName);
        sb.append(", chapterDesc=").append(chapterDesc);
        sb.append(", isFree=").append(isFree);
        sb.append(", chapterOriginal=").append(chapterOriginal);
        sb.append(", chapterDiscount=").append(chapterDiscount);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}