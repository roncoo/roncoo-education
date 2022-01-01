package com.roncoo.education.course.service.api.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 广告信息
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class AdvDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "主键id")
    private Long id;
    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer sort;
    /**
     * 广告标题
     */
    @ApiModelProperty(value = "广告标题")
    private String advTitle;
    /**
     * 广告图片
     */
    @ApiModelProperty(value = "广告图片")
    private String advImg;
    /**
     * 广告链接
     */
    @ApiModelProperty(value = "广告链接")
    private String advUrl;
    /**
     * 广告跳转方式
     */
    @ApiModelProperty(value = "广告跳转方式")
    private String advTarget;
    /**
     * 广告位置(1首页轮播)
     */
    @ApiModelProperty(value = "广告位置(1首页轮播)")
    private Integer advLocation;
}
