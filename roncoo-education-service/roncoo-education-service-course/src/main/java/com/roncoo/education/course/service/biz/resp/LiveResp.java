package com.roncoo.education.course.service.biz.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * ADMIN-直播信息
 * </p>
 *
 * @author fengyw
 */
@Data
@Accessors(chain = true)
@Schema(description = "直播信息查看")
public class LiveResp implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "创建时间")
    private LocalDateTime gmtCreate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "修改时间")
    private LocalDateTime gmtModified;

    @Schema(description = "状态(1:正常，0:禁用)")
    private Integer statusId;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "直播名称")
    private String liveName;

    @Schema(description = "直播介绍")
    private String liveIntroduce;

    @Schema(description = "讲师ID")
    private Long lecturerId;

    @Schema(description = "讲师名称")
    private String lecturerName;

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

    @Schema(description = "资源ID(记录回放视频)")
    private Long resourceId;
}
