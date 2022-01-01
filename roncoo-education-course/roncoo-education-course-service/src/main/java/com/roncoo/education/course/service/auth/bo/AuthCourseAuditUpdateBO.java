package com.roncoo.education.course.service.auth.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 课程信息-审核
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class AuthCourseAuditUpdateBO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "课程ID")
    private Long id;
    /**
     * 课程名称
     */
    @ApiModelProperty(value = "课程名称", required = true)
    private String courseName;
    /**
     * 一级分类ID
     */
    @ApiModelProperty(value = "一级分类ID")
    private Long categoryId1;
    /**
     * 二级分类ID
     */
    @ApiModelProperty(value = "二级分类ID")
    private Long categoryId2;
    /**
     * 三级分类ID
     */
    @ApiModelProperty(value = "三级分类ID")
    private Long categoryId3;
    /**
     * 是否免费：1免费，0收费
     */
    @ApiModelProperty(value = "是否免费：1免费，0收费", required = true)
    private Integer isFree;
    /**
     * 课程封面
     */
    @ApiModelProperty(value = "课程封面", required = true)
    private String courseLogo;
    /**
     * 课程简介
     */
    @ApiModelProperty(value = "课程简介", required = true)
    private String introduce;
    /**
     * 原价
     */
    @ApiModelProperty(value = "原价", required = true)
    private BigDecimal courseOriginal;
    /**
     * 用户编号
     */
    @ApiModelProperty(value = "用户编号", required = true)
    private Long userNo;
}
