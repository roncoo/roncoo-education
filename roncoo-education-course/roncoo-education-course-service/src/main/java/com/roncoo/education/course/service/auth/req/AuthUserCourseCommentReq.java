package com.roncoo.education.course.service.auth.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

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
@ApiModel(description = "API-AUTH-课程评论")
public class AuthUserCourseCommentReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "课程ID")
    private Long courseId;

    @ApiModelProperty(value = "评论ID，被评论的ID", required = false)
    private Long commentId;

    @ApiModelProperty(value = "评论内容")
    private String commentText;
}
