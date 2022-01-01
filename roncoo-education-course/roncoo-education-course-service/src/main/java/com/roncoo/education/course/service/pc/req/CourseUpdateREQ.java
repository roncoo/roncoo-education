package com.roncoo.education.course.service.pc.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 课程信息-更新
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class CourseUpdateREQ implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID", required = true)
    private Long id;
    /**
     * 状态(1:正常，0:禁用)
     */
    @ApiModelProperty(value = "状态(1:正常，0:禁用)", required = false)
    private Integer statusId;
    /**
     * 排序
     */
    @ApiModelProperty(value = "排序", required = false)
    private Integer sort;
    /**
     * 课程排序
     */
    @ApiModelProperty(value = "课程排序", required = false)
    private Integer courseSort;
    /**
     * 一级分类ID
     */
    @ApiModelProperty(value = "一级分类ID", required = false)
    private Long categoryId1;
    /**
     * 二级分类ID
     */
    @ApiModelProperty(value = "二级分类ID", required = false)
    private Long categoryId2;
    /**
     * 三级分类ID
     */
    @ApiModelProperty(value = "三级分类ID", required = false)
    private Long categoryId3;
    /**
     * 课程名称
     */
    @ApiModelProperty(value = "课程名称", required = false)
    private String courseName;
    /**
     * 是否上架(1:上架，0:下架)
     */
    @ApiModelProperty(value = "是否上架(1:上架，0:下架)", required = false)
    private Integer isPutaway;
    /**
     * 是否免费：1免费，0收费
     */
    @ApiModelProperty(value = "是否免费：1免费，0收费", required = false)
    private Integer isFree;
    /**
     * 原价
     */
    @ApiModelProperty(value = "原价", required = false)
    private BigDecimal courseOriginal;
    /**
     * 优惠价
     */
    @ApiModelProperty(value = "优惠价", required = false)
    private BigDecimal courseDiscount;
    /**
     * 课程介绍
     */
    @ApiModelProperty(value = "课程介绍", required = false)
    private String introduce;

}
