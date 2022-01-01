package com.roncoo.education.course.service.pc.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 课程推荐-分页列出
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class CourseRecommendPageREQ implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 状态(1:正常;0:禁用)
     */
    @ApiModelProperty(value = "状态(1:正常;0:禁用)", required = false)
    private Integer statusId;
    /**
     * 分类ID
     */
    @ApiModelProperty(value = "分类ID", required = true)
    private Long categoryId;
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
}
