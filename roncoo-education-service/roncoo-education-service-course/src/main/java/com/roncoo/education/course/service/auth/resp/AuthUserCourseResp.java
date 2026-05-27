package com.roncoo.education.course.service.auth.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * API-AUTH-课程用户关联表
 * </p>
 *
 * @author wujing
 * @date 2022-08-25
 */
@Data
@Accessors(chain = true)
@Schema(description = "API-AUTH-课程用户关联表")
public class AuthUserCourseResp implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "创建时间")
    private LocalDateTime gmtCreate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "修改时间")
    private LocalDateTime gmtModified;

    @Schema(description = "用户ID")
    private Long userId;

    @Schema(description = "课程ID")
    private Long courseId;

    @Schema(description = "购买类型(1支付，2免费)")
    private Integer buyType;

    @Schema(description = "课程信息")
    private AuthCourseResp courseResp;

    @Schema(description = "当前学习的课时名称")
    private String periodName;

    @Schema(description = "当前学习的课时进度")
    private BigDecimal periodProgress;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "当前学习的课时时间")
    private LocalDateTime periodTime;

    @Schema(description = "该课程总进度")
    private BigDecimal courseProgress;
}
