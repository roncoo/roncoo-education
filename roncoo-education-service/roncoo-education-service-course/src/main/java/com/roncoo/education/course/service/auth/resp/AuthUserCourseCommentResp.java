package com.roncoo.education.course.service.auth.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.roncoo.education.course.service.biz.resp.CourseResp;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * API-AUTH-课程评论
 * </p>
 *
 * @author wujing
 * @date 2023-03-24
 */
@Data
@Accessors(chain = true)
@Schema(description = "API-AUTH-课程评论")
public class AuthUserCourseCommentResp implements Serializable {

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

    @Schema(description = "用户ID")
    private Long userId;

    @Schema(description = "课程ID")
    private Long courseId;

    @Schema(description = "评论ID")
    private Long commentId;

    @Schema(description = "评论内容")
    private String commentText;

    @Schema(description = "课程信息")
    private CourseResp courseResp;
}
