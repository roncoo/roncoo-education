package com.roncoo.education.course.service.auth.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 课程视频信息
 *
 * @author wuyun
 */
@Data
@Accessors(chain = true)
public class AuthPeriodIdVideoBO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 课时ID
     */
    @ApiModelProperty(value = "课时ID", required = true)
    private Long periodId;

}
