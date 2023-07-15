package com.roncoo.education.course.feign.interfaces.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 课程信息
 *
 * @author wujing
 * @date 2022-08-27
 */
@Data
@Accessors(chain = true)
public class CourseViewVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 修改时间
     */
    private LocalDateTime gmtModified;

    /**
     * 状态(1:正常，0:禁用)
     */
    private Integer statusId;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 讲师ID
     */
    private Long lecturerId;

    /**
     * 分类ID
     */
    private Long categoryId;

    /**
     * 课程名称
     */
    private String courseName;

    /**
     * 课程封面
     */
    private String courseLogo;

    /**
     * 课程简介
     */
    private String introduce;

    /**
     * 是否免费(1:免费，0:收费)
     */
    private Integer isFree;

    /**
     * 划线价
     */
    private BigDecimal rulingPrice;

    /**
     * 课程价格
     */
    private BigDecimal coursePrice;

    /**
     * 是否上架(1:上架，0:下架)
     */
    private Integer isPutaway;

    /**
     * 课程排序(前端显示使用)
     */
    private Integer courseSort;

    /**
     * 购买人数
     */
    private Integer countBuy;

    /**
     * 学习人数
     */
    private Integer countStudy;
}
