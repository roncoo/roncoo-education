package com.roncoo.education.course.service.auth.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 讲师信息
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class AuthLecturerDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "课程ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtCreate;
    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtModified;
    /**
     * 状态(1:正常，0:禁用)
     */
    @ApiModelProperty(value = "状态(1:正常,0:禁用)")
    private Integer statusId;
    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer sort;
    /**
     * 讲师用户编号
     */
    @ApiModelProperty(value = "讲师用户编号")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long lecturerUserNo;
    /**
     * 讲师名称
     */
    @ApiModelProperty(value = "讲师名称")
    private String lecturerName;
    /**
     * 讲师手机
     */
    @ApiModelProperty(value = "讲师手机")
    private String lecturerMobile;
    /**
     * 讲师邮箱
     */
    @ApiModelProperty(value = "讲师邮箱")
    private String lecturerEmail;
    /**
     * 职位
     */
    @ApiModelProperty(value = "职位")
    private String position;
    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    private String headImgUrl;
    /**
     * 简介
     */
    @ApiModelProperty(value = "简介")
    private String introduce;
    /**
     * 讲师分成比例
     */
    @ApiModelProperty(value = "讲师分成比例")
    private BigDecimal lecturerProportion;
}
