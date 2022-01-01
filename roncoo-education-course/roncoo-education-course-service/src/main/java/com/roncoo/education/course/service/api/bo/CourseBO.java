package com.roncoo.education.course.service.api.bo;

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
public class CourseBO implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 当前页
     */
    private int pageCurrent = 1;
    /**
     * 每页记录数
     */
    private int pageSize = 20;
    /**
     * 一级分类ID
     */
    private Long categoryId1;
    /**
     * 二级分类ID
     */
    private Long categoryId2;
    /**
     * 三级分类ID
     */
    private Long categoryId3;
    /**
     * 课程名称
     */
    private String courseName;
    /**
     * 是否免费：1免费，0收费
     */
    private Integer isFree;

}
