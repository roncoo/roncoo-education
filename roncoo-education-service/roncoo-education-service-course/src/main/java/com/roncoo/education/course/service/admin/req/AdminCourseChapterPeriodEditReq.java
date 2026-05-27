package com.roncoo.education.course.service.admin.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * ADMIN-课时信息
 * </p>
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
@Schema(description = "ADMIN-课时信息修改")
public class AdminCourseChapterPeriodEditReq implements Serializable {

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

    @Schema(description = "讲师ID")
    private Long lecturerId;

    @Schema(description = "直播介绍")
    private String liveIntroduce;

    @Schema(description = "直播模式(1三分屏)")
    private Integer liveModel;

    @Schema(description = "直播延迟(1正常延迟，2无延迟)")
    private Integer liveDelay;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "开播时间")
    private LocalDateTime beginTime;

    @Schema(description = "开播时长，单位:秒")
    private Integer liveDuration;

    @Schema(description = "直播状态(1待开播，2直播中，3待回放，4直播结束)")
    private Integer liveStatus;

    @Schema(description = "回放保存(1保存，2丢弃)")
    private Integer playbackSave;
}
