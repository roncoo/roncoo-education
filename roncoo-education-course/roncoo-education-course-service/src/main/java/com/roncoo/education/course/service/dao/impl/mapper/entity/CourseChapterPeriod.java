package com.roncoo.education.course.service.dao.impl.mapper.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class CourseChapterPeriod implements Serializable {
    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    private Integer statusId;

    private Integer sort;

    private Long courseId;

    private Long chapterId;

    private String periodName;

    private String periodDesc;

    private Integer isFree;

    private BigDecimal periodOriginal;

    private BigDecimal periodDiscount;

    private Integer countBuy;

    private Integer countStudy;

    private Integer isDoc;

    private String docName;

    private String docUrl;

    private String isVideo;

    private Long videoNo;

    private String videoName;

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

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getChapterId() {
        return chapterId;
    }

    public void setChapterId(Long chapterId) {
        this.chapterId = chapterId;
    }

    public String getPeriodName() {
        return periodName;
    }

    public void setPeriodName(String periodName) {
        this.periodName = periodName == null ? null : periodName.trim();
    }

    public String getPeriodDesc() {
        return periodDesc;
    }

    public void setPeriodDesc(String periodDesc) {
        this.periodDesc = periodDesc == null ? null : periodDesc.trim();
    }

    public Integer getIsFree() {
        return isFree;
    }

    public void setIsFree(Integer isFree) {
        this.isFree = isFree;
    }

    public BigDecimal getPeriodOriginal() {
        return periodOriginal;
    }

    public void setPeriodOriginal(BigDecimal periodOriginal) {
        this.periodOriginal = periodOriginal;
    }

    public BigDecimal getPeriodDiscount() {
        return periodDiscount;
    }

    public void setPeriodDiscount(BigDecimal periodDiscount) {
        this.periodDiscount = periodDiscount;
    }

    public Integer getCountBuy() {
        return countBuy;
    }

    public void setCountBuy(Integer countBuy) {
        this.countBuy = countBuy;
    }

    public Integer getCountStudy() {
        return countStudy;
    }

    public void setCountStudy(Integer countStudy) {
        this.countStudy = countStudy;
    }

    public Integer getIsDoc() {
        return isDoc;
    }

    public void setIsDoc(Integer isDoc) {
        this.isDoc = isDoc;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName == null ? null : docName.trim();
    }

    public String getDocUrl() {
        return docUrl;
    }

    public void setDocUrl(String docUrl) {
        this.docUrl = docUrl == null ? null : docUrl.trim();
    }

    public String getIsVideo() {
        return isVideo;
    }

    public void setIsVideo(String isVideo) {
        this.isVideo = isVideo == null ? null : isVideo.trim();
    }

    public Long getVideoNo() {
        return videoNo;
    }

    public void setVideoNo(Long videoNo) {
        this.videoNo = videoNo;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName == null ? null : videoName.trim();
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
        sb.append(", courseId=").append(courseId);
        sb.append(", chapterId=").append(chapterId);
        sb.append(", periodName=").append(periodName);
        sb.append(", periodDesc=").append(periodDesc);
        sb.append(", isFree=").append(isFree);
        sb.append(", periodOriginal=").append(periodOriginal);
        sb.append(", periodDiscount=").append(periodDiscount);
        sb.append(", countBuy=").append(countBuy);
        sb.append(", countStudy=").append(countStudy);
        sb.append(", isDoc=").append(isDoc);
        sb.append(", docName=").append(docName);
        sb.append(", docUrl=").append(docUrl);
        sb.append(", isVideo=").append(isVideo);
        sb.append(", videoNo=").append(videoNo);
        sb.append(", videoName=").append(videoName);
        sb.append(", videoLength=").append(videoLength);
        sb.append(", videoVid=").append(videoVid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}