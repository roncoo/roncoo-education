package com.roncoo.education.course.feign.interfaces.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 专区课程关联表
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class ZoneCourseVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;
    /**
     * 创建时间
     */
    private Date gmtCreate;
    /**
     * 修改时间
     */
    private Date gmtModified;
    /**
     * 状态(1:正常;0:禁用)
     */
    private Integer statusId;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 专区编号
     */
    private Long zoneId;
    /**
     * 课程ID
     */
    private Long courseId;
    /**
     * 课程名称
     */
    private String courseName;
    /**
     * 位置(0电脑端，1微信端)
     */
    private Integer zoneLocation;
    /**
     * 一级分类名
     */
    private String categoryName1;
    /**
     * 二级分类名
     */
    private String categoryName2;
    /**
     * 三级分类名
     */
    private String categoryName3;
    /**
     * 当前页
     */
    private int pageCurrent;
    /**
     * 每页记录数
     */
    private int pageSize;
    /**
     * 章节集合
     */
    private List<CourseChapterVO> courseChapterList;
}
