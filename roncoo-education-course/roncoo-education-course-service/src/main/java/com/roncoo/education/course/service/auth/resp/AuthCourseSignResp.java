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

    @ApiModelProperty(value = "sign")
    private String sign;

    @ApiModelProperty(value = "学习ID")
    private Long studyId;

    @ApiModelProperty(value = "最大学习进度")
    private BigDecimal progress;
}
