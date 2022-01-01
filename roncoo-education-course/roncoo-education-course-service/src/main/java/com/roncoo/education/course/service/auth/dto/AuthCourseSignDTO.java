/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.course.service.auth.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 课时信息
 * </p>
 *
 * @author wujing123
 */
@Data
@Accessors(chain = true)
public class AuthCourseSignDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "h5播放时间戳", required = true)
    private String ts;

    @ApiModelProperty(value = "h5播放校验签名", required = true)
    private String sign;

    @ApiModelProperty(value = "h5播放token值", required = true)
    private String token;

    @ApiModelProperty(value = "code值", required = true)
    private String code;

}
