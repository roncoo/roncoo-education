package com.roncoo.education.system.service.dao.impl.mapper.entity;

import java.io.Serializable;
import java.util.Date;

public class Sys implements Serializable {
    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    private Integer statusId;

    private Integer sort;

    private Integer videoType;

    private String polyvUseid;

    private String polyvWritetoken;

    private String polyvReadtoken;

    private String polyvSecretkey;

    private Integer fileType;

    private String aliyunAccessKeyId;

    private String aliyunAccessKeySecret;

    private String aliyunOssUrl;

    private String aliyunOssBucket;

    private Integer payType;

    private String payUrl;

    private String payKey;

    private String paySecret;

    private String notifyUrl;

    private String smsCode;

    private String signName;

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

    public Integer getVideoType() {
        return videoType;
    }

    public void setVideoType(Integer videoType) {
        this.videoType = videoType;
    }

    public String getPolyvUseid() {
        return polyvUseid;
    }

    public void setPolyvUseid(String polyvUseid) {
        this.polyvUseid = polyvUseid == null ? null : polyvUseid.trim();
    }

    public String getPolyvWritetoken() {
        return polyvWritetoken;
    }

    public void setPolyvWritetoken(String polyvWritetoken) {
        this.polyvWritetoken = polyvWritetoken == null ? null : polyvWritetoken.trim();
    }

    public String getPolyvReadtoken() {
        return polyvReadtoken;
    }

    public void setPolyvReadtoken(String polyvReadtoken) {
        this.polyvReadtoken = polyvReadtoken == null ? null : polyvReadtoken.trim();
    }

    public String getPolyvSecretkey() {
        return polyvSecretkey;
    }

    public void setPolyvSecretkey(String polyvSecretkey) {
        this.polyvSecretkey = polyvSecretkey == null ? null : polyvSecretkey.trim();
    }

    public Integer getFileType() {
        return fileType;
    }

    public void setFileType(Integer fileType) {
        this.fileType = fileType;
    }

    public String getAliyunAccessKeyId() {
        return aliyunAccessKeyId;
    }

    public void setAliyunAccessKeyId(String aliyunAccessKeyId) {
        this.aliyunAccessKeyId = aliyunAccessKeyId == null ? null : aliyunAccessKeyId.trim();
    }

    public String getAliyunAccessKeySecret() {
        return aliyunAccessKeySecret;
    }

    public void setAliyunAccessKeySecret(String aliyunAccessKeySecret) {
        this.aliyunAccessKeySecret = aliyunAccessKeySecret == null ? null : aliyunAccessKeySecret.trim();
    }

    public String getAliyunOssUrl() {
        return aliyunOssUrl;
    }

    public void setAliyunOssUrl(String aliyunOssUrl) {
        this.aliyunOssUrl = aliyunOssUrl == null ? null : aliyunOssUrl.trim();
    }

    public String getAliyunOssBucket() {
        return aliyunOssBucket;
    }

    public void setAliyunOssBucket(String aliyunOssBucket) {
        this.aliyunOssBucket = aliyunOssBucket == null ? null : aliyunOssBucket.trim();
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public String getPayUrl() {
        return payUrl;
    }

    public void setPayUrl(String payUrl) {
        this.payUrl = payUrl == null ? null : payUrl.trim();
    }

    public String getPayKey() {
        return payKey;
    }

    public void setPayKey(String payKey) {
        this.payKey = payKey == null ? null : payKey.trim();
    }

    public String getPaySecret() {
        return paySecret;
    }

    public void setPaySecret(String paySecret) {
        this.paySecret = paySecret == null ? null : paySecret.trim();
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl == null ? null : notifyUrl.trim();
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode == null ? null : smsCode.trim();
    }

    public String getSignName() {
        return signName;
    }

    public void setSignName(String signName) {
        this.signName = signName == null ? null : signName.trim();
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
        sb.append(", videoType=").append(videoType);
        sb.append(", polyvUseid=").append(polyvUseid);
        sb.append(", polyvWritetoken=").append(polyvWritetoken);
        sb.append(", polyvReadtoken=").append(polyvReadtoken);
        sb.append(", polyvSecretkey=").append(polyvSecretkey);
        sb.append(", fileType=").append(fileType);
        sb.append(", aliyunAccessKeyId=").append(aliyunAccessKeyId);
        sb.append(", aliyunAccessKeySecret=").append(aliyunAccessKeySecret);
        sb.append(", aliyunOssUrl=").append(aliyunOssUrl);
        sb.append(", aliyunOssBucket=").append(aliyunOssBucket);
        sb.append(", payType=").append(payType);
        sb.append(", payUrl=").append(payUrl);
        sb.append(", payKey=").append(payKey);
        sb.append(", paySecret=").append(paySecret);
        sb.append(", notifyUrl=").append(notifyUrl);
        sb.append(", smsCode=").append(smsCode);
        sb.append(", signName=").append(signName);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}