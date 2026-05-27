package com.roncoo.education.course.service.admin.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * ADMIN-课程信息
 * </p>
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
@Schema(description = "ADMIN-课程信息分页")
public class AdminCoursePageResp implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "创建时间")
    private LocalDateTime gmtCreate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "修改时间")
    private LocalDateTime gmtModified;

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

    @Schema(description = "划线价")
    private BigDecimal rulingPrice;

    @Schema(description = "价格")
    private BigDecimal coursePrice;

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

    @Schema(description = "讲师名称")
    private String lecturerName;

    @Schema(description = "分类名称")
    private String categoryName;
}
