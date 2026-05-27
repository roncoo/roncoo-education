package com.roncoo.education.course.service.admin.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * ADMIN-课时信息
 * </p>
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
@Schema(description = "ADMIN-课时信息列表")
public class AdminCourseChapterPeriodListReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "章节ID")
    private Long chapterId;
}
