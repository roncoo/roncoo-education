package com.roncoo.education.course.service.api.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * API-资源学习记录
 * </p>
 *
 * @author wujing
 * @date 2022-09-03
 */
@Data
@Accessors(chain = true)
@Schema(description = "API-资源学习记录")
public class ApiUserStudyReq implements Serializable {

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
