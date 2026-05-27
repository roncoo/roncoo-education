package com.roncoo.education.course.service.admin.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * ADMIN-资源学习记录
 * </p>
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
@Schema(description = "ADMIN-资源学习记录添加")
public class AdminUserStudySaveReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "课程ID")
    private Long courseId;

    @Schema(description = "章节ID")
    private Long chapterId;

    @Schema(description = "课时ID")
    private Long periodId;

    @Schema(description = "用户ID")
    private Long userId;

    @Schema(description = "进度(百分比)")
    private BigDecimal progress;
}
