package com.roncoo.education.course.service.admin.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * ADMIN-课程信息
 * </p>
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
@Schema(description = "ADMIN-课程信息修改")
public class AdminCourseEditReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "状态(1:正常，0:禁用)")
    private Integer statusId;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "讲师ID")
    private Long lecturerId;

    @Schema(description = "分类ID")
    private Long categoryId;

    @Schema(description = "课程名称")
    private String courseName;

    @Schema(description = "课程封面")
    private String courseLogo;

    @Schema(description = "课程简介")
    private String introduce;

    @Schema(description = "是否免费(1:免费，0:收费)")
    private Integer isFree;

    @Schema(description = "原价")
    private BigDecimal coursePrice;

    @Schema(description = "优惠价")
    private BigDecimal rulingPrice;

    @Schema(description = "是否上架(1:上架，0:下架)")
    private Integer isPutaway;

    @Schema(description = "购买人数")
    private Integer countBuy;

    @Schema(description = "学习人数")
    private Integer countStudy;

    @Schema(description = "倍速播放")
    private Integer speedDouble;

    @Schema(description = "拖拽播放")
    private Integer speedDrag;
}
