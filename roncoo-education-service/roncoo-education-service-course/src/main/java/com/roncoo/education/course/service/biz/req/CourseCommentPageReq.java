package com.roncoo.education.course.service.biz.req;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "API-课程评论")
public class CourseCommentPageReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "课程ID")
    private Long courseId;

    /**
     * 当前页
     */
    @Schema(description = "当前页")
    private Integer pageCurrent = 1;
    /**
     * 每页条数
     */
    @Schema(description = "每页条数")
    private Integer pageSize = 20;
}
