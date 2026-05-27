package com.roncoo.education.course.service.auth.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * API-AUTH-课程信息
 * </p>
 *
 * @author wujing
 * @date 2022-08-25
 */
@Data
@Accessors(chain = true)
@Schema(description = "API-AUTH-课程信息")
public class AuthCourseSignResp implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "学习ID")
    private Long studyId;

    @Schema(description = "课时ID")
    private Long periodId;

    @Schema(description = "课时类型")
    private Integer periodType;

    @Schema(description = "资源ID")
    private Long resourceId;

    @Schema(description = "资源类型")
    private Integer resourceType;

    @Schema(description = "播放vid")
    private String vid;

    @Schema(description = "点播平台")
    private Integer vodPlatform;

    @Schema(description = "当前观看时长，单位秒")
    private Integer currentDuration;

    @Schema(description = "点播播放配置参数")
    private String vodPlayConfig;

    @Schema(description = "直播观看配置参数")
    private String liveViewConfig;

    @Schema(description = "存储平台")
    private Integer storagePlatform;

    @Schema(description = "当前观看页数")
    private Integer currentPage;

    @Schema(description = "文档学习配置参数")
    private String docStudyConfig;

    @Schema(description = "图片学习配置参数")
    private String picStudyConfig;
}
