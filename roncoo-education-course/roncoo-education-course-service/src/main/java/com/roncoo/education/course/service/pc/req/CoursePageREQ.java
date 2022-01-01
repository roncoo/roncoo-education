package com.roncoo.education.course.service.pc.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 课程信息-分页列出
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class CoursePageREQ implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 当前页
     */
    @ApiModelProperty(value = "当前页", required = true)
    private int pageCurrent = 1;
    /**
     * 每页记录数
     */
    @ApiModelProperty(value = "每页记录数", required = true)
    private int pageSize = 20;
    /**
     * 状态(1:正常，0:禁用)
     */
    @ApiModelProperty(value = "状态(1:正常，0:禁用)", required = false)
    private Integer statusId;
    /**
     * 一级分类ID
     */
    @ApiModelProperty(value = "一级分类ID", required = false)
    private Long categoryId1;
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
     * 专区编号
     */
    @ApiModelProperty(value = "专区编号", required = false)
    private Long zoneId;
}
