package com.roncoo.education.course.service.auth.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description = "API-AUTH-课程信息")
public class AuthCourseSignResp implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "学习ID")
    private Long studyId;

    @ApiModelProperty(value = "课时ID")
    private Long periodId;

    @ApiModelProperty(value = "资源ID")
    private Long resourceId;

    @ApiModelProperty(value = "开始播放时长，单位秒")
    private Long startTime;

    @ApiModelProperty(value = "播放vid")
    private String vid;

    @ApiModelProperty(value = "点播平台")
    private Integer vodPlatform;

    @ApiModelProperty(value = "当前观看时长，单位秒")
    private Integer currentDuration;

    @ApiModelProperty(value = "点播播放配置参数")
    private String vodPlayConfig;

    @ApiModelProperty(value = "当前观看页数")
    private Integer currentPage;

    @ApiModelProperty(value = "文档学习配置参数")
    private String docStudyConfig;
}
