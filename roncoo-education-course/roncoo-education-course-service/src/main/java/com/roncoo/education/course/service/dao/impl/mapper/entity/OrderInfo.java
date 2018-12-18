package com.roncoo.education.course.service.dao.impl.mapper.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class OrderInfo implements Serializable {
    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    private Long lecturerUserNo;

    private String lecturerName;

    private Long userNo;

    private String mobile;

    private Date registerTime;

    private Long orderNo;

    private Long courseId;

    private String courseName;

    private BigDecimal pricePayable;

    private BigDecimal priceDiscount;

    private BigDecimal pricePaid;

    private BigDecimal platformProfit;

    private BigDecimal lecturerProfit;

    private Integer tradeType;

    private Integer payType;

    private Integer channelType;

    private Integer orderStatus;

    private Integer isShowLecturer;

    private Integer isShowUser;

    private String remarkCus;

    private String remark;

    private Date payTime;
    
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

    public Long getLecturerUserNo() {
        return lecturerUserNo;
    }

    public void setLecturerUserNo(Long lecturerUserNo) {
        this.lecturerUserNo = lecturerUserNo;
    }

    public String getLecturerName() {
        return lecturerName;
    }

    public void setLecturerName(String lecturerName) {
        this.lecturerName = lecturerName == null ? null : lecturerName.trim();
    }

    public Long getUserNo() {
        return userNo;
    }

    public void setUserNo(Long userNo) {
        this.userNo = userNo;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public Long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName == null ? null : courseName.trim();
    }

    public BigDecimal getPricePayable() {
        return pricePayable;
    }

    public void setPricePayable(BigDecimal pricePayable) {
        this.pricePayable = pricePayable;
    }

    public BigDecimal getPriceDiscount() {
        return priceDiscount;
    }

    public void setPriceDiscount(BigDecimal priceDiscount) {
        this.priceDiscount = priceDiscount;
    }

    public BigDecimal getPricePaid() {
        return pricePaid;
    }

    public void setPricePaid(BigDecimal pricePaid) {
        this.pricePaid = pricePaid;
    }

    public BigDecimal getPlatformProfit() {
        return platformProfit;
    }

    public void setPlatformProfit(BigDecimal platformProfit) {
        this.platformProfit = platformProfit;
    }

    public BigDecimal getLecturerProfit() {
        return lecturerProfit;
    }

    public void setLecturerProfit(BigDecimal lecturerProfit) {
        this.lecturerProfit = lecturerProfit;
    }

    public Integer getTradeType() {
        return tradeType;
    }

    public void setTradeType(Integer tradeType) {
        this.tradeType = tradeType;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Integer getChannelType() {
        return channelType;
    }

    public void setChannelType(Integer channelType) {
        this.channelType = channelType;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getIsShowLecturer() {
        return isShowLecturer;
    }

    public void setIsShowLecturer(Integer isShowLecturer) {
        this.isShowLecturer = isShowLecturer;
    }

    public Integer getIsShowUser() {
        return isShowUser;
    }

    public void setIsShowUser(Integer isShowUser) {
        this.isShowUser = isShowUser;
    }

    public String getRemarkCus() {
        return remarkCus;
    }

    public void setRemarkCus(String remarkCus) {
        this.remarkCus = remarkCus == null ? null : remarkCus.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
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
        sb.append(", lecturerUserNo=").append(lecturerUserNo);
        sb.append(", lecturerName=").append(lecturerName);
        sb.append(", userNo=").append(userNo);
        sb.append(", mobile=").append(mobile);
        sb.append(", registerTime=").append(registerTime);
        sb.append(", orderNo=").append(orderNo);
        sb.append(", courseId=").append(courseId);
        sb.append(", courseName=").append(courseName);
        sb.append(", pricePayable=").append(pricePayable);
        sb.append(", priceDiscount=").append(priceDiscount);
        sb.append(", pricePaid=").append(pricePaid);
        sb.append(", platformProfit=").append(platformProfit);
        sb.append(", lecturerProfit=").append(lecturerProfit);
        sb.append(", tradeType=").append(tradeType);
        sb.append(", payType=").append(payType);
        sb.append(", channelType=").append(channelType);
        sb.append(", orderStatus=").append(orderStatus);
        sb.append(", isShowLecturer=").append(isShowLecturer);
        sb.append(", isShowUser=").append(isShowUser);
        sb.append(", remarkCus=").append(remarkCus);
        sb.append(", remark=").append(remark);
        sb.append(", payTime=").append(payTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}