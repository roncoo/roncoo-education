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
@Schema(description = "ADMIN-课时信息分页")
public class AdminCourseChapterPeriodPageReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "状态(1:正常，0:禁用)")
    private Integer statusId;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "课程ID")
    private Long courseId;

    @Schema(description = "章节ID")
    private Long chapterId;

    @Schema(description = "课时名称")
    private String periodName;

    @Schema(description = "课时描述")
    private String periodDesc;

    @Schema(description = "是否免费(1免费，0收费)")
    private Integer isFree;

    @Schema(description = "资源ID")
    private Long resourceId;

    @Schema(description = "课时类型(10资源，20直播)")
    private Integer periodType;

    @Schema(description = "直播ID")
    private Long liveId;

    @Schema(description = "当前页")
    private int pageCurrent = 1;

    @Schema(description = "每页条数")
    private int pageSize = 20;
}
