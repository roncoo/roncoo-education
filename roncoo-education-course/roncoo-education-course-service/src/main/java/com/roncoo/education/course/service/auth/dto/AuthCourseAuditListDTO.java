package com.roncoo.education.course.service.auth.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 课程信息-审核
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class AuthCourseAuditListDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "课程ID")
    @JsonSerialize(using = ToStringSerializer.class)
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
     * 课程封面
     */
    @ApiModelProperty(value = "课程封面")
    private String courseLogo;
    /**
     * 是否免费：1免费，0收费
     */
    @ApiModelProperty(value = "是否免费：1免费，0收费")
    private Integer isFree;
    /**
     * 原价
     */
    @ApiModelProperty(value = "原价")
    private BigDecimal courseOriginal;
    /**
     * 优惠价
     */
    @ApiModelProperty(value = "优惠价")
    private BigDecimal courseDiscount;
    /**
     * 是否上架(1:上架，0:下架)
     */
    @ApiModelProperty(value = "是否上架(1:上架，0:下架)")
    private Integer isPutaway;
    /**
     * 是否可删除
     */
    @ApiModelProperty(value = "是否可删除（1:可删除，0:不可删除）")
    private Integer isDelete;
}
