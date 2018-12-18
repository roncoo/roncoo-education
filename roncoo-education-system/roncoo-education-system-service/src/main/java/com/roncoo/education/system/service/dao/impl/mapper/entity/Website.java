package com.roncoo.education.system.service.dao.impl.mapper.entity;

import java.io.Serializable;
import java.util.Date;

public class Website implements Serializable {
    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    private Integer statusId;

    private Integer sort;

    private String websiteTitle;

    private String websiteKeyword;

    private String websiteDesc;

    private String copyright;

    private String weixin;

    private String weibo;

    private Integer isEnableStatistics;

    private String statisticsCode;

    private Integer isShowService;

    private String service1;

    private String service2;

    private String service3;

    private String service4;

    private String service5;

    private Integer polyvStatus;

    private String polyvLogo;

    private Integer isWatermark;

    private String picWatermark;

    private Integer picx;

    private Integer picy;

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

    public String getWebsiteTitle() {
        return websiteTitle;
    }

    public void setWebsiteTitle(String websiteTitle) {
        this.websiteTitle = websiteTitle == null ? null : websiteTitle.trim();
    }

    public String getWebsiteKeyword() {
        return websiteKeyword;
    }

    public void setWebsiteKeyword(String websiteKeyword) {
        this.websiteKeyword = websiteKeyword == null ? null : websiteKeyword.trim();
    }

    public String getWebsiteDesc() {
        return websiteDesc;
    }

    public void setWebsiteDesc(String websiteDesc) {
        this.websiteDesc = websiteDesc == null ? null : websiteDesc.trim();
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright == null ? null : copyright.trim();
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin == null ? null : weixin.trim();
    }

    public String getWeibo() {
        return weibo;
    }

    public void setWeibo(String weibo) {
        this.weibo = weibo == null ? null : weibo.trim();
    }

    public Integer getIsEnableStatistics() {
        return isEnableStatistics;
    }

    public void setIsEnableStatistics(Integer isEnableStatistics) {
        this.isEnableStatistics = isEnableStatistics;
    }

    public String getStatisticsCode() {
        return statisticsCode;
    }

    public void setStatisticsCode(String statisticsCode) {
        this.statisticsCode = statisticsCode == null ? null : statisticsCode.trim();
    }

    public Integer getIsShowService() {
        return isShowService;
    }

    public void setIsShowService(Integer isShowService) {
        this.isShowService = isShowService;
    }

    public String getService1() {
        return service1;
    }

    public void setService1(String service1) {
        this.service1 = service1 == null ? null : service1.trim();
    }

    public String getService2() {
        return service2;
    }

    public void setService2(String service2) {
        this.service2 = service2 == null ? null : service2.trim();
    }

    public String getService3() {
        return service3;
    }

    public void setService3(String service3) {
        this.service3 = service3 == null ? null : service3.trim();
    }

    public String getService4() {
        return service4;
    }

    public void setService4(String service4) {
        this.service4 = service4 == null ? null : service4.trim();
    }

    public String getService5() {
        return service5;
    }

    public void setService5(String service5) {
        this.service5 = service5 == null ? null : service5.trim();
    }

    public Integer getPolyvStatus() {
        return polyvStatus;
    }

    public void setPolyvStatus(Integer polyvStatus) {
        this.polyvStatus = polyvStatus;
    }

    public String getPolyvLogo() {
        return polyvLogo;
    }

    public void setPolyvLogo(String polyvLogo) {
        this.polyvLogo = polyvLogo == null ? null : polyvLogo.trim();
    }

    public Integer getIsWatermark() {
        return isWatermark;
    }

    public void setIsWatermark(Integer isWatermark) {
        this.isWatermark = isWatermark;
    }

    public String getPicWatermark() {
        return picWatermark;
    }

    public void setPicWatermark(String picWatermark) {
        this.picWatermark = picWatermark == null ? null : picWatermark.trim();
    }

    public Integer getPicx() {
        return picx;
    }

    public void setPicx(Integer picx) {
        this.picx = picx;
    }

    public Integer getPicy() {
        return picy;
    }

    public void setPicy(Integer picy) {
        this.picy = picy;
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
        sb.append(", websiteTitle=").append(websiteTitle);
        sb.append(", websiteKeyword=").append(websiteKeyword);
        sb.append(", websiteDesc=").append(websiteDesc);
        sb.append(", copyright=").append(copyright);
        sb.append(", weixin=").append(weixin);
        sb.append(", weibo=").append(weibo);
        sb.append(", isEnableStatistics=").append(isEnableStatistics);
        sb.append(", statisticsCode=").append(statisticsCode);
        sb.append(", isShowService=").append(isShowService);
        sb.append(", service1=").append(service1);
        sb.append(", service2=").append(service2);
        sb.append(", service3=").append(service3);
        sb.append(", service4=").append(service4);
        sb.append(", service5=").append(service5);
        sb.append(", polyvStatus=").append(polyvStatus);
        sb.append(", polyvLogo=").append(polyvLogo);
        sb.append(", isWatermark=").append(isWatermark);
        sb.append(", picWatermark=").append(picWatermark);
        sb.append(", picx=").append(picx);
        sb.append(", picy=").append(picy);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}