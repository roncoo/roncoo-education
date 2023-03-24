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
@ApiModel(value = "AuthCourseSignResp", description = "API-AUTH-课程信息")
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

    @ApiModelProperty(value = "点播播放配置参数")
    private String vodPlayConfig;

    @Deprecated
    @ApiModelProperty(value = "播放ts")
    private String ts;

    @Deprecated
    @ApiModelProperty(value = "播放sign")
    private String sign;

    @Deprecated
    @ApiModelProperty(value = "播放token")
    private String token;

    @Deprecated
    @ApiModelProperty(value = "播放code")
    private String code;
}
