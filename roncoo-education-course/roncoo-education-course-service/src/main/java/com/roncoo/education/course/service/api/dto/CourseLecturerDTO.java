/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.course.service.api.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 讲师信息
 *
 * @author wujing123
 */
@Data
@Accessors(chain = true)
public class CourseLecturerDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 讲师用户编号
     */
    @ApiModelProperty(value = "讲师编号", required = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long lecturerUserNo;
    /**
     * 讲师名称
     */
    @ApiModelProperty(value = "讲师名称", required = true)
    private String lecturerName;
    /**
     * 职位
     */
    @ApiModelProperty(value = "讲师职位", required = true)
    private String position;
    /**
     * 头像
     */
    @ApiModelProperty(value = "讲师头像", required = true)
    private String imgLogo;
    /**
     * 简介
     */
    @ApiModelProperty(value = "讲师介绍", required = true)
    private String introduce;

}
