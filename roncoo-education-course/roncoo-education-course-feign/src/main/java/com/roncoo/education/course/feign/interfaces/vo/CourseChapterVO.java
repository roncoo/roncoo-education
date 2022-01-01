package com.roncoo.education.course.feign.interfaces.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 章节信息
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class CourseChapterVO implements Serializable {

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
     * 状态(1:正常，0:禁用)
     */
    private Integer statusId;
    /**
     * 1
     */
    private Integer sort;
    /**
     * 课程ID
     */
    private Long courseId;
    /**
     * 章节名称
     */
    private String chapterName;
    /**
     * 章节描述
     */
    private String chapterDesc;
    /**
     * 是否免费：1免费，0收费
     */
    private Integer isFree;
    /**
     * 原价
     */
    private BigDecimal chapterOriginal;
    /**
     * 优惠价
     */
    private BigDecimal chapterDiscount;
    /**
     * 课时集合
     */
    private List<CourseChapterPeriodVO> courseChapterPeriodVOList;
    /**
     * 所属课程
     */
    private String courseName;

}
