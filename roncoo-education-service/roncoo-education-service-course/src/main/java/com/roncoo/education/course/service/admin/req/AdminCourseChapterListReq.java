package com.roncoo.education.course.service.admin.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * ADMIN-章节信息
 * </p>
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
@Schema(description = "ADMIN-章节信息列表")
public class AdminCourseChapterListReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "课程ID")
    private Long courseId;
}
