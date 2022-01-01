package com.roncoo.education.course.service.auth.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 课程信息-审核
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class AuthCourseAuditStandBO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 课程ID
     */
    @ApiModelProperty(value = "课程ID")
    private Long id;
    /**
     * 是否上架(1:上架，0:下架)
     */
    @ApiModelProperty(value = "是否上架(1:上架，0:下架)")
    private Integer isPutaway;
    /**
     * 用户编号
     */
    @ApiModelProperty(value = "用户编号")
    private Long userNo;
}
