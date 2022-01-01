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
public class AuthCourseAuditBO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "课程ID")
    private Long id;
    /**
     * 状态(1:正常，0:禁用)
     */
    @ApiModelProperty(value = "状态(1:正常，0:禁用)")
    private Integer statusId;
    /**
     * 审核状态(0:待审核,1:审核通过,2:审核不通过)
     */
    @ApiModelProperty(value = "审核状态(0:待审核,1:审核通过,2:审核不通过)")
    private Integer auditStatus;
    /**
     * 课程名称
     */
    @ApiModelProperty(value = "课程名称")
    private String courseName;
    /**
     * 是否免费：1免费，0收费
     */
    @ApiModelProperty(value = "是否免费：1免费，0收费")
    private Integer isFree;
    /**
     * 是否上架(1:上架，0:下架)
     */
    @ApiModelProperty(value = "是否上架(1:上架，0:下架)")
    private Integer isPutaway;

    /**
     * 当前页
     */
    @ApiModelProperty(value = "当前页", required = true)
    private Integer pageCurrent;
    /**
     * 每页记录数
     */
    @ApiModelProperty(value = "每页记录数", required = true)
    private Integer pageSize;
}
