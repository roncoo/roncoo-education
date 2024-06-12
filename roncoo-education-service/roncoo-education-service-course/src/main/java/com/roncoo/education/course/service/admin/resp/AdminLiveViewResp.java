package com.roncoo.education.course.service.admin.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description = "ADMIN-直播信息查看")
public class AdminLiveViewResp implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime gmtCreate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "修改时间")
    private LocalDateTime gmtModified;

    @ApiModelProperty(value = "状态(1:正常，0:禁用)")
    private Integer statusId;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "直播名称")
    private String liveName;

    @ApiModelProperty(value = "直播介绍")
    private String liveIntroduce;

    @ApiModelProperty(value = "讲师ID")
    private Long lecturerId;

    @ApiModelProperty(value = "讲师名称")
    private String lecturerName;

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

    @ApiModelProperty(value = "资源ID(记录回放视频)")
    private Long resourceId;
}
