package com.roncoo.education.course.service.api.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 课程信息
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class CourseInfoPageBO implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 当前页
     */
    @ApiModelProperty(value = "当前页")
    private int pageCurrent = 1;
    /**
     * 每页条数
     */
    @ApiModelProperty(value = "每页条数")
    private int pageSize = 20;
    /**
     * 一级分类编号
     */
    @ApiModelProperty(value = "一级分类编号")
    private Long categoryId1;
    /**
     * 二级分类编号
     */
    @ApiModelProperty(value = "二级分类编号")
    private Long categoryId2;
    /**
     * 三级分类编号
     */
    @ApiModelProperty(value = "三级分类编号")
    private Long categoryId3;
    /**
     * 课程名称
     */
    @ApiModelProperty(value = "课程名称")
    private String courseName;
    /**
     * 是否免费(1:免费，0:收费)
     */
    @ApiModelProperty(value = "是否免费(1:免费，0:收费)")
    private Integer isFree;
}
