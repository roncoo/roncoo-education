package com.roncoo.education.course.service.auth.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * API-AUTH-课程收藏
 * </p>
 *
 * @author wujing
 * @date 2023-03-24
 */
@Data
@Accessors(chain = true)
@Schema(description = "API-AUTH-课程收藏")
public class AuthUserCourseCollectReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "课程ID")
    private Long courseId;
}
