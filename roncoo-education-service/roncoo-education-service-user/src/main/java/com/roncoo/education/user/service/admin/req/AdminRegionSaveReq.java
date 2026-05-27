package com.roncoo.education.user.service.admin.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * ADMIN-行政区域表
 * </p>
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
@Schema(description = "ADMIN-行政区域表添加")
public class AdminRegionSaveReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "父id")
    private Long parentId;

    @Schema(description = "级别")
    private Integer level;

    @Schema(description = "区域编码（国标）")
    private String provinceCode;

    @Schema(description = "中心经度")
    private BigDecimal centerLng;

    @Schema(description = "中心维度")
    private BigDecimal centerLat;

    @Schema(description = "省Id")
    private Integer provinceId;

    @Schema(description = "省名称")
    private String provinceName;

    @Schema(description = "市Id")
    private Integer cityId;

    @Schema(description = "城市编码")
    private String cityCode;

    @Schema(description = "市名称")
    private String cityName;

    @Schema(description = "区域名称")
    private String regionName;

    @Schema(description = "区名称")
    private String districtName;

    @Schema(description = "全路径名称")
    private String mergerName;
}
