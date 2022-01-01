package com.roncoo.education.course.service.api.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 课程信息
 *
 * @author kyh
 */
@Data
@Accessors(chain = true)
public class CourseRecommendDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 课程名称
     */
    @ApiModelProperty(value = "课程名称")
    private String courseName;
    /**
     * 课程id
     */
    @ApiModelProperty(value = "课程id")
    private Long courseId;
    /**
     * 课程封面
     */
    @ApiModelProperty(value = "课程封面")
    private String courseImg;
    /**
     * 原价
     */
    @ApiModelProperty(value = "课程原价")
    private BigDecimal courseOriginal;
    /**
     * 优惠价
     */
    @ApiModelProperty(value = "优惠价")
    private BigDecimal courseDiscount;
    /**
     * 是否免费：1免费，2收费
     */
    @ApiModelProperty(value = "是否免费(1:免费;2:收费)")
    private Integer isFree;
}
