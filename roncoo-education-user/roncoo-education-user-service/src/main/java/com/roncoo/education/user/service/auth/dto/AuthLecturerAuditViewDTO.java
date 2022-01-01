/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.user.service.auth.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 讲师信息-审核 -查看接口
 * </p>
 *
 * @author wuyun
 */
@Data
@Accessors(chain = true)
public class AuthLecturerAuditViewDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtCreate;
    /**
     * 状态(1:有效;0:无效)
     */
    @ApiModelProperty(value = "状态(1:有效;0:无效)")
    private Integer statusId;
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
     * 电话
     */
    @ApiModelProperty(value = "电话")
    private String lecturerMobile;
    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
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
     * 审核状态(1:待审核,2:审核通过,3:审核不通过)
     */
    @ApiModelProperty(value = "审核状态(0:待审核,1:审核通过,2:审核不通过)")
    private Integer auditStatus;
    /**
     * 审核意见
     */
    @ApiModelProperty(value = "审核意见")
    private String auditOpinion;

}
