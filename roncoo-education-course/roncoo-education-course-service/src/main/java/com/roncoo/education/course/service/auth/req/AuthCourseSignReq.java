package com.roncoo.education.course.service.auth.req;

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
@ApiModel(value = "AuthCourseSignReq", description = "API-AUTH-课程信息")
public class AuthCourseSignReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "课时ID")
    private Long periodId;

}
