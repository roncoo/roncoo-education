package com.roncoo.education.user.service.api.resp;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
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
public class RegionResp implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Schema(description = "主键")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 父id
     */
    @Schema(description = "父id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long parentId;
    /**
     * 级别
     */
    @Schema(description = "级别")
    private Integer level;
    /**
     * 中心经度
     */
    @Schema(description = "中心经度")
    private BigDecimal centerLng;
    /**
     * 中心维度
     */
    @Schema(description = "中心维度")
    private BigDecimal centerLat;
    /**
     * 省Id
     */
    @Schema(description = "省Id")
    private Integer provinceId;
    /**
     * 区域编码（国标）
     */
    @Schema(description = "区域编码(国标)")
    private String provinceCode;
    /**
     * 省名称
     */
    @Schema(description = "省名称")
    private String provinceName;
    /**
     * 市Id
     */
    @Schema(description = "市Id")
    private Integer cityId;
    /**
     * 城市编码
     */
    @Schema(description = "城市编码")
    private String cityCode;
    /**
     * 市名称
     */
    @Schema(description = "市名称")
    private String cityName;
    /**
     * 区域名称
     */
    @Schema(description = "区域名称")
    private String regionName;
    /**
     * 区名称
     */
    @Schema(description = "区名称")
    private String districtName;
    /**
     * 全路径名称
     */
    @Schema(description = "全路径名称")
    private String mergerName;
}
