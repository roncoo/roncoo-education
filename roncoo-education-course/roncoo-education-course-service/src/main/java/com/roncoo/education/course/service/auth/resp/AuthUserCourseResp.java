package com.roncoo.education.course.service.auth.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * API-AUTH-课程用户关联表
 * </p>
 *
 * @author wujing
 * @date 2022-08-25
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "API-AUTH-课程用户关联表")
public class AuthUserCourseResp implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime gmtCreate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "修改时间")
    private LocalDateTime gmtModified;

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "课程ID")
    private Long courseId;

    @ApiModelProperty(value = "购买类型(1支付，2免费)")
    private Integer buyType;

    @ApiModelProperty(value = "课程信息")
    private AuthCourseResp courseResp;

    @ApiModelProperty(value = "当前学习的课时名称")
    private String periodName;

    @ApiModelProperty(value = "当前学习的课时进度")
    private BigDecimal periodProgress;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "当前学习的课时时间")
    private LocalDateTime periodTime;

    @ApiModelProperty(value = "该课程总进度")
    private BigDecimal courseProgress;
}
