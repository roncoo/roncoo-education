package com.roncoo.education.course.service.api.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * API-专区课程关联表
 * </p>
 *
 * @author wujing
 * @date 2022-08-25
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "ApiZoneCourseResp", description = "API-专区课程关联表")
public class ApiZoneCourseResp implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "课程名称")
    private String courseName;

    @ApiModelProperty(value = "课程封面")
    private String courseLogo;

    @ApiModelProperty(value = "原价")
    private BigDecimal courseOriginal;

    @ApiModelProperty(value = "优惠价")
    private BigDecimal courseDiscount;

    @ApiModelProperty(value = "购买人数")
    private Integer countBuy;

    @ApiModelProperty(value = "学习人数")
    private Integer countStudy;
}
