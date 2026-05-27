package com.roncoo.education.course.service.auth.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

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
public class AuthUserCourseCommentReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "课程ID")
    private Long courseId;

    @Schema(description = "评论ID，被评论的ID", required = false)
    private Long commentId;

    @NotBlank(message = "请填写评论内容")
    @Schema(description = "评论内容")
    private String commentText;
}
