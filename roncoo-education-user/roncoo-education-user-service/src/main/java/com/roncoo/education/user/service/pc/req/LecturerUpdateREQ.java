package com.roncoo.education.user.service.pc.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 讲师信息更新(直接生效)
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class LecturerUpdateREQ implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键", required = true)
    private Long id;
    /**
     * 状态(1:正常，0:禁用)
     */
    @ApiModelProperty(value = "状态(1:正常，0:禁用)", required = false)
    private Integer statusId;
    /**
     * 排序
     */
    @ApiModelProperty(value = "排序", required = false)
    private Integer sort;
    /**
     * 讲师用户编号
     */
    @ApiModelProperty(value = "讲师用户编号", required = false)
    private Long lecturerUserNo;
    /**
     * 讲师名称
     */
    @ApiModelProperty(value = "讲师名称", required = false)
    private String lecturerName;
    /**
     * 讲师手机
     */
    @ApiModelProperty(value = "讲师手机", required = false)
    private String lecturerMobile;
    /**
     * 讲师邮箱
     */
    @ApiModelProperty(value = "讲师邮箱", required = false)
    private String lecturerEmail;
    /**
     * 职位
     */
    @ApiModelProperty(value = "职位", required = false)
    private String position;
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
    /**
     * 讲师分成比例
     */
    @ApiModelProperty(value = "讲师分成比例", required = false)
    private BigDecimal lecturerProportion;

}
