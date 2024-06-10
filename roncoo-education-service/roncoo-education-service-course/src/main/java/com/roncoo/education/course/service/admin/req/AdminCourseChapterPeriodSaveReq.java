package com.roncoo.education.course.service.admin.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description = "ADMIN-课时信息添加")
public class AdminCourseChapterPeriodSaveReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "状态(1:正常，0:禁用)")
    private Integer statusId;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "课程ID")
    private Long courseId;

    @ApiModelProperty(value = "章节ID")
    private Long chapterId;

    @ApiModelProperty(value = "课时名称")
    private String periodName;

    @ApiModelProperty(value = "课时描述")
    private String periodDesc;

    @ApiModelProperty(value = "是否免费(1免费，0收费)")
    private Integer isFree;

    @ApiModelProperty(value = "资源ID")
    private Long resourceId;

    @ApiModelProperty(value = "课时类型(10资源，20直播)")
    private Integer periodType;

    @ApiModelProperty(value = "讲师ID")
    private Long lecturerId;

    @ApiModelProperty(value = "直播模式(1三分屏)")
    private Integer liveModel;

    @ApiModelProperty(value = "直播延迟(1正常延迟，2无延迟)")
    private Integer liveDelay;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "开播时间")
    private LocalDateTime beginTime;

    @ApiModelProperty(value = "开播时长，单位:秒")
    private Integer liveDuration;

    @ApiModelProperty(value = "直播状态(1待开播，2直播中，3待回放，4直播结束)")
    private Integer liveStatus;

    @ApiModelProperty(value = "回放保存(1保存，2丢弃)")
    private Integer playbackSave;

}
