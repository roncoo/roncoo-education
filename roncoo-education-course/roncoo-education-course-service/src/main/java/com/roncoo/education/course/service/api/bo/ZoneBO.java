package com.roncoo.education.course.service.api.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 专区
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class ZoneBO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 专区编号
     */
    @ApiModelProperty(value = "专区编号")
    private Long id;

    /**
     * 位置(1电脑端，2微信端)
     */
    @ApiModelProperty(value = "位置(1电脑端，2微信端)", required = true)
    private Integer zoneLocation;

    /**
     * 当前页
     */
    @ApiModelProperty(value = "当前页")
    private Integer pageCurrent = 1;

    /**
     * 每页条数
     */
    @ApiModelProperty(value = "每页条数")
    private Integer pageSize = 20;
}
