package com.roncoo.education.user.service.api.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 行政区域表
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class RegionDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 父id
     */
    @ApiModelProperty(value = "父id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long parentId;
    /**
     * 级别
     */
    @ApiModelProperty(value = "级别")
    private Integer level;
    /**
     * 中心经度
     */
    @ApiModelProperty(value = "中心经度")
    private BigDecimal centerLng;
    /**
     * 中心维度
     */
    @ApiModelProperty(value = "中心维度")
    private BigDecimal centerLat;
    /**
     * 省Id
     */
    @ApiModelProperty(value = "省Id")
    private Integer provinceId;
    /**
     * 区域编码（国标）
     */
    @ApiModelProperty(value = "区域编码(国标)")
    private String provinceCode;
    /**
     * 省名称
     */
    @ApiModelProperty(value = "省名称")
    private String provinceName;
    /**
     * 市Id
     */
    @ApiModelProperty(value = "市Id")
    private Integer cityId;
    /**
     * 城市编码
     */
    @ApiModelProperty(value = "城市编码")
    private String cityCode;
    /**
     * 市名称
     */
    @ApiModelProperty(value = "市名称")
    private String cityName;
    /**
     * 区域名称
     */
    @ApiModelProperty(value = "区域名称")
    private String regionName;
    /**
     * 区名称
     */
    @ApiModelProperty(value = "区名称")
    private String districtName;
    /**
     * 全路径名称
     */
    @ApiModelProperty(value = "全路径名称")
    private String mergerName;
}
