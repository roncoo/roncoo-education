package com.roncoo.education.course.service.auth.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * API-AUTH-课程信息
 * </p>
 *
 * @author wujing
 * @date 2022-08-25
 */
@Data
@Accessors(chain = true)
@Schema(description = "API-AUTH-课程信息")
public class AuthCourseSignReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "课程ID，若课程ID存在，则获取该课程的最新学习课时")
    private Long courseId;

    @Schema(description = "课时ID")
    private Long periodId;

    @Schema(description = "观看者IP")
    private String clientIp;

}
