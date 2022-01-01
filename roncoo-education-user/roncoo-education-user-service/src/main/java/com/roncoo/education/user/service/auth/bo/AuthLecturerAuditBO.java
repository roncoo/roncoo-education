package com.roncoo.education.user.service.auth.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 讲师信息-审核
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class AuthLecturerAuditBO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "讲师主键", required = true)
    private Long id;
    /**
     * 讲师用户编号
     */
    @ApiModelProperty(value = "讲师用户编号", required = true)
    private Long lecturerUserNo;
    /**
     * 讲师名称
     */
    @ApiModelProperty(value = "讲师名称", required = false)
    private String lecturerName;
    /**
     * 头像
     */
    @ApiModelProperty(value = "头像", required = false)
    private String headImgUrl;
    /**
     * 简介
     */
    @ApiModelProperty(value = "简介", required = false)
    private String introduce;
}
