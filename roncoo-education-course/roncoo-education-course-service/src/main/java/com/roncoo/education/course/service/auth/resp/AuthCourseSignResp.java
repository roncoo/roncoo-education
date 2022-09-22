package com.roncoo.education.course.service.auth.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

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

    @ApiModelProperty(value = "资源ID")
    private Long resourceId;

    @ApiModelProperty(value = "最大学习进度")
    private BigDecimal progress;

    @ApiModelProperty(value = "播放vid")
    private String vid;

    @ApiModelProperty(value = "播放ts")
    private String ts;

    @ApiModelProperty(value = "播放sign")
    private String sign;

    @ApiModelProperty(value = "播放token")
    private String token;

    @ApiModelProperty(value = "播放code")
    private String code;
}
