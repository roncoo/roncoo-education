package com.roncoo.education.course.service.auth.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * API-AUTH-课程用户学习日志
 * </p>
 *
 * @author wujing
 * @date 2022-09-03
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "API-AUTH-课程用户学习日志")
public class AuthUserStudyReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "学习ID")
    private Long studyId;

    @ApiModelProperty(value = "资源ID")
    private Long resourceId;

    @ApiModelProperty(value = "当前观看时长")
    private BigDecimal currentDuration;

    @ApiModelProperty(value = "总时长")
    private BigDecimal totalDuration;

}
