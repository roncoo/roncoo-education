package com.roncoo.education.course.service.api.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

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
public class ApiCoursePageResp implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "创建时间")
    private LocalDateTime gmtCreate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "修改时间")
    private LocalDateTime gmtModified;

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
    private BigDecimal rulingPrice;

    @Schema(description = "优惠价")
    private BigDecimal coursePrice;

}
