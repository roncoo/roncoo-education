package com.roncoo.education.course.service.auth.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * API-AUTH-课程收藏
 * </p>
 *
 * @author wujing
 * @date 2023-03-24
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "API-AUTH-课程收藏")
public class AuthUserCourseCollectPageReq implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 当前页
     */
    @ApiModelProperty(value = "当前页")
    private Integer pageCurrent = 1;
    /**
     * 每页条数
     */
    @ApiModelProperty(value = "每页条数")
    private Integer pageSize = 20;
}
