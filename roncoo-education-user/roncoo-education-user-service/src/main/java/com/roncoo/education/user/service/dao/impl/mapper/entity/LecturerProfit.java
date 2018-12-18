package com.roncoo.education.user.service.dao.impl.mapper.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class LecturerProfit implements Serializable {
    private Long id;

    private Date gmtCreate;

    private Long lecturerUserNo;

    private String bankCardNo;

    private String bankName;

    private String bankBranchName;

    private String bankUserName;

    private String bankIdCardNo;

    private BigDecimal lecturerProfit;

    private BigDecimal platformProfit;

    private Integer profitStatus;

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

    public Long getLecturerUserNo() {
        return lecturerUserNo;
    }

    public void setLecturerUserNo(Long lecturerUserNo) {
        this.lecturerUserNo = lecturerUserNo;
    }

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo == null ? null : bankCardNo.trim();
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }

    public String getBankBranchName() {
        return bankBranchName;
    }

    public void setBankBranchName(String bankBranchName) {
        this.bankBranchName = bankBranchName == null ? null : bankBranchName.trim();
    }

    public String getBankUserName() {
        return bankUserName;
    }

    public void setBankUserName(String bankUserName) {
        this.bankUserName = bankUserName == null ? null : bankUserName.trim();
    }

    public String getBankIdCardNo() {
        return bankIdCardNo;
    }

    public void setBankIdCardNo(String bankIdCardNo) {
        this.bankIdCardNo = bankIdCardNo == null ? null : bankIdCardNo.trim();
    }

    public BigDecimal getLecturerProfit() {
        return lecturerProfit;
    }

    public void setLecturerProfit(BigDecimal lecturerProfit) {
        this.lecturerProfit = lecturerProfit;
    }

    public BigDecimal getPlatformProfit() {
        return platformProfit;
    }

    public void setPlatformProfit(BigDecimal platformProfit) {
        this.platformProfit = platformProfit;
    }

    public Integer getProfitStatus() {
        return profitStatus;
    }

    public void setProfitStatus(Integer profitStatus) {
        this.profitStatus = profitStatus;
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
        sb.append(", lecturerUserNo=").append(lecturerUserNo);
        sb.append(", bankCardNo=").append(bankCardNo);
        sb.append(", bankName=").append(bankName);
        sb.append(", bankBranchName=").append(bankBranchName);
        sb.append(", bankUserName=").append(bankUserName);
        sb.append(", bankIdCardNo=").append(bankIdCardNo);
        sb.append(", lecturerProfit=").append(lecturerProfit);
        sb.append(", platformProfit=").append(platformProfit);
        sb.append(", profitStatus=").append(profitStatus);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}