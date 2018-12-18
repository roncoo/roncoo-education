package com.roncoo.education.user.service.dao.impl.mapper.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class Region implements Serializable {
    private Long id;

    private Long parentId;

    private Integer level;

    private BigDecimal centerLng;

    private BigDecimal centerLat;

    private Integer provinceId;

    private String provinceCode;

    private String provinceName;

    private Integer cityId;

    private String cityCode;

    private String cityName;

    private String regionName;

    private String districtName;

    private String mergerName;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public BigDecimal getCenterLng() {
        return centerLng;
    }

    public void setCenterLng(BigDecimal centerLng) {
        this.centerLng = centerLng;
    }

    public BigDecimal getCenterLat() {
        return centerLat;
    }

    public void setCenterLat(BigDecimal centerLat) {
        this.centerLat = centerLat;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode == null ? null : provinceCode.trim();
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName == null ? null : provinceName.trim();
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode == null ? null : cityCode.trim();
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName == null ? null : regionName.trim();
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName == null ? null : districtName.trim();
    }

    public String getMergerName() {
        return mergerName;
    }

    public void setMergerName(String mergerName) {
        this.mergerName = mergerName == null ? null : mergerName.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", parentId=").append(parentId);
        sb.append(", level=").append(level);
        sb.append(", centerLng=").append(centerLng);
        sb.append(", centerLat=").append(centerLat);
        sb.append(", provinceId=").append(provinceId);
        sb.append(", provinceCode=").append(provinceCode);
        sb.append(", provinceName=").append(provinceName);
        sb.append(", cityId=").append(cityId);
        sb.append(", cityCode=").append(cityCode);
        sb.append(", cityName=").append(cityName);
        sb.append(", regionName=").append(regionName);
        sb.append(", districtName=").append(districtName);
        sb.append(", mergerName=").append(mergerName);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}