package com.roncoo.education.user.feign.interfaces.qo;

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
public class RegionQO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 当前页
     */
    private int pageCurrent;
    /**
     * 每页记录数
     */
    private int pageSize;
    /**
     * 主键
     */
    private Long id;
    /**
     * 父id
     */
    private Long parentId;
    /**
     * 级别
     */
    private Integer level;
    /**
     * 中心经度
     */
    private BigDecimal centerLng;
    /**
     * 中心维度
     */
    private BigDecimal centerLat;
    /**
     * 省Id
     */
    private Integer provinceId;
    /**
     * 区域编码（国标）
     */
    private String provinceCode;
    /**
     * 省名称
     */
    private String provinceName;
    /**
     * 市Id
     */
    private Integer cityId;
    /**
     * 城市编码
     */
    private String cityCode;
    /**
     * 市名称
     */
    private String cityName;
    /**
     * 区域名称
     */
    private String regionName;
    /**
     * 区名称
     */
    private String districtName;
    /**
     * 全路径名称
     */
    private String mergerName;
}
