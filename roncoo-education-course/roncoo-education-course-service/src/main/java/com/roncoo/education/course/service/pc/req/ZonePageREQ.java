package com.roncoo.education.course.service.pc.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 专区-分页列出
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class ZonePageREQ implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 位置(1电脑端，2微信端)
     */
    @ApiModelProperty(value = "位置(1电脑端，2微信端)", required = true)
    private Integer zoneLocation;
    /**
     * 专区名称
     */
    @ApiModelProperty(value = "专区名称", required = false)
    private String zoneName;
    /**
     * 状态(1:正常，0:禁用)
     */
    @ApiModelProperty(value = "状态(1:正常，0:禁用)", required = false)
    private Integer statusId;
    /**
     * 当前页
     */
    @ApiModelProperty(value = "当前页", required = true)
    private Integer pageCurrent = 1;

    /**
     * 每页条数
     */
    @ApiModelProperty(value = "每页条数", required = true)
    private Integer pageSize = 20;
}
