package com.roncoo.education.course.common.bean.bo.auth;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 章节信息-审核
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class AuthCourseChapterAuditBO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;
    /**
     * 状态(1:正常，0:禁用)
     */
    private Integer statusId;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 审核状态(0:待审核;1:审核通过;2:审核不通过)
     */
    private Integer auditStatus;
    /**
     * 审核意见
     */
    private String auditOpinion;
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
}
