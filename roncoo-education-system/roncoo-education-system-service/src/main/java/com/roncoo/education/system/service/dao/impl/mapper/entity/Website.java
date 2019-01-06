package com.roncoo.education.system.service.dao.impl.mapper.entity;

import java.io.Serializable;
import java.util.Date;

public class Website implements Serializable {
    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    private Integer statusId;

    private Integer sort;

    private String logoIco;

    private String logoImg;

    private String websiteTitle;

    private String websiteKeyword;

    private String websiteDesc;

    private String copyright;

    private String icp;

    private String prn;

    private String weixin;

    private String weixinXcx;

    private String weibo;

    private Integer isEnableStatistics;

    private String statisticsCode;

    private Integer isShowService;

    private String service1;

    private String service2;

    private String userAgreement;

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

    public String getLogoIco() {
        return logoIco;
    }

    public void setLogoIco(String logoIco) {
        this.logoIco = logoIco == null ? null : logoIco.trim();
    }

    public String getLogoImg() {
        return logoImg;
    }

    public void setLogoImg(String logoImg) {
        this.logoImg = logoImg == null ? null : logoImg.trim();
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

    public String getIcp() {
        return icp;
    }

    public void setIcp(String icp) {
        this.icp = icp == null ? null : icp.trim();
    }

    public String getPrn() {
        return prn;
    }

    public void setPrn(String prn) {
        this.prn = prn == null ? null : prn.trim();
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin == null ? null : weixin.trim();
    }

    public String getWeixinXcx() {
        return weixinXcx;
    }

    public void setWeixinXcx(String weixinXcx) {
        this.weixinXcx = weixinXcx == null ? null : weixinXcx.trim();
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

    public String getUserAgreement() {
        return userAgreement;
    }

    public void setUserAgreement(String userAgreement) {
        this.userAgreement = userAgreement == null ? null : userAgreement.trim();
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
        sb.append(", logoIco=").append(logoIco);
        sb.append(", logoImg=").append(logoImg);
        sb.append(", websiteTitle=").append(websiteTitle);
        sb.append(", websiteKeyword=").append(websiteKeyword);
        sb.append(", websiteDesc=").append(websiteDesc);
        sb.append(", copyright=").append(copyright);
        sb.append(", icp=").append(icp);
        sb.append(", prn=").append(prn);
        sb.append(", weixin=").append(weixin);
        sb.append(", weixinXcx=").append(weixinXcx);
        sb.append(", weibo=").append(weibo);
        sb.append(", isEnableStatistics=").append(isEnableStatistics);
        sb.append(", statisticsCode=").append(statisticsCode);
        sb.append(", isShowService=").append(isShowService);
        sb.append(", service1=").append(service1);
        sb.append(", service2=").append(service2);
        sb.append(", userAgreement=").append(userAgreement);
        sb.append(", recruitTitle=").append(recruitTitle);
        sb.append(", recruitInfo=").append(recruitInfo);
        sb.append(", entryAgreement=").append(entryAgreement);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}