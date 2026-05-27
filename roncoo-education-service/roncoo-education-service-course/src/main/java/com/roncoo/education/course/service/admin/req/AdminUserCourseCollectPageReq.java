package com.roncoo.education.course.service.admin.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * ADMIN-课程收藏
 * </p>
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
@Schema(description = "ADMIN-课程收藏分页")
public class AdminUserCourseCollectPageReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "手机号")
    private String mobile;

    @Schema(description = "课程ID")
    private Long courseId;

    @Schema(description = "当前页")
    private int pageCurrent = 1;

    @Schema(description = "每页条数")
    private int pageSize = 20;
}
