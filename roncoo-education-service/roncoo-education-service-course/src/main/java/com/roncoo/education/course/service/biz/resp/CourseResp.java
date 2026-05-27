package com.roncoo.education.course.service.biz.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * API-课程信息
 * </p>
 *
 * @author wujing
 * @date 2022-08-25
 */
@Data
@Accessors(chain = true)
@Schema(description = "API-课程信息")
public class CourseResp implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "是否允许学习: 0禁止学习，1可以学习")
    private Integer allowStudy = 0;

    @Schema(description = "主键")
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "创建时间")
    private LocalDateTime gmtCreate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "修改时间")
    private LocalDateTime gmtModified;

    @Schema(description = "课程名称")
    private String courseName;

    @Schema(description = "课程封面")
    private String courseLogo;

    @Schema(description = "课程简介")
    private String introduce;

    @Schema(description = "是否免费(1:免费，0:收费)")
    private Integer isFree;

    @Schema(description = "原价")
    private BigDecimal rulingPrice;

    @Schema(description = "优惠价")
    private BigDecimal coursePrice;

    @Schema(description = "购买人数")
    private Integer countBuy;

    @Schema(description = "学习人数")
    private Integer countStudy;

    @Schema(description = "倍速播放")
    private Integer speedDouble;

    @Schema(description = "拖拽播放")
    private Integer speedDrag;

    @Schema(description = "分类ID")
    private Long categoryId;

    @Schema(description = "讲师ID")
    private Long lecturerId;

    @Schema(description = "讲师信息")
    private CourseLecturerResp lecturerResp;

    @Schema(description = "章节信息")
    private List<CourseChapterResp> chapterRespList;

    @Schema(description = "课程收藏状态")
    private Boolean courseCollect = false;

}
