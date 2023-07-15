package com.roncoo.education.user.service.admin.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description = "ADMIN-行政区域表添加")
public class AdminRegionSaveReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "父id")
    private Long parentId;

    @ApiModelProperty(value = "级别")
    private Integer level;

    @ApiModelProperty(value = "区域编码（国标）")
    private String provinceCode;

    @ApiModelProperty(value = "中心经度")
    private BigDecimal centerLng;

    @ApiModelProperty(value = "中心维度")
    private BigDecimal centerLat;

    @ApiModelProperty(value = "省Id")
    private Integer provinceId;

    @ApiModelProperty(value = "省名称")
    private String provinceName;

    @ApiModelProperty(value = "市Id")
    private Integer cityId;

    @ApiModelProperty(value = "城市编码")
    private String cityCode;

    @ApiModelProperty(value = "市名称")
    private String cityName;

    @ApiModelProperty(value = "区域名称")
    private String regionName;

    @ApiModelProperty(value = "区名称")
    private String districtName;

    @ApiModelProperty(value = "全路径名称")
    private String mergerName;
}
