package com.roncoo.education.user.service.dao.impl.mapper.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class LecturerExt implements Serializable {
    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    private Integer statusId;

    private Integer sort;

    private Long lecturerUserNo;

    private BigDecimal totalIncome;

    private BigDecimal historyMoney;

    private BigDecimal enableBalances;

    private BigDecimal freezeBalances;

    private String sign;

    private String bankCardNo;

    private String bankName;

    private String bankBranchName;

    private String bankUserName;

    private String bankIdCardNo;

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

    public Long getLecturerUserNo() {
        return lecturerUserNo;
    }

    public void setLecturerUserNo(Long lecturerUserNo) {
        this.lecturerUserNo = lecturerUserNo;
    }

    public BigDecimal getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(BigDecimal totalIncome) {
        this.totalIncome = totalIncome;
    }

    public BigDecimal getHistoryMoney() {
        return historyMoney;
    }

    public void setHistoryMoney(BigDecimal historyMoney) {
        this.historyMoney = historyMoney;
    }

    public BigDecimal getEnableBalances() {
        return enableBalances;
    }

    public void setEnableBalances(BigDecimal enableBalances) {
        this.enableBalances = enableBalances;
    }

    public BigDecimal getFreezeBalances() {
        return freezeBalances;
    }

    public void setFreezeBalances(BigDecimal freezeBalances) {
        this.freezeBalances = freezeBalances;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign == null ? null : sign.trim();
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
        sb.append(", lecturerUserNo=").append(lecturerUserNo);
        sb.append(", totalIncome=").append(totalIncome);
        sb.append(", historyMoney=").append(historyMoney);
        sb.append(", enableBalances=").append(enableBalances);
        sb.append(", freezeBalances=").append(freezeBalances);
        sb.append(", sign=").append(sign);
        sb.append(", bankCardNo=").append(bankCardNo);
        sb.append(", bankName=").append(bankName);
        sb.append(", bankBranchName=").append(bankBranchName);
        sb.append(", bankUserName=").append(bankUserName);
        sb.append(", bankIdCardNo=").append(bankIdCardNo);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}