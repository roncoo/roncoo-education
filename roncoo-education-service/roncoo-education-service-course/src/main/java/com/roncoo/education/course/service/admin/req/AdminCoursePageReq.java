package com.roncoo.education.course.service.admin.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * ADMIN-课程信息
 * </p>
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "ADMIN-课程信息分页")
public class AdminCoursePageReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "状态(1:正常，0:禁用)")
    private Integer statusId;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "讲师ID")
    private Long lecturerId;

    @ApiModelProperty(value = "分类ID")
    private Long categoryId;

    @ApiModelProperty(value = "课程名称")
    private String courseName;

    @ApiModelProperty(value = "课程封面")
    private String courseLogo;

    @ApiModelProperty(value = "课程简介")
    private String introduce;

    @ApiModelProperty(value = "是否免费(1:免费，0:收费)")
    private Integer isFree;

    @ApiModelProperty(value = "原价")
    private BigDecimal coursePrice;

    @ApiModelProperty(value = "优惠价")
    private BigDecimal rulingPrice;

    @ApiModelProperty(value = "是否上架(1:上架，0:下架)")
    private Integer isPutaway;

    @ApiModelProperty(value = "课程排序(前端显示使用)")
    private Integer courseSort;

    @ApiModelProperty(value = "购买人数")
    private Integer countBuy;

    @ApiModelProperty(value = "学习人数")
    private Integer countStudy;

    @ApiModelProperty(value = "当前页")
    private int pageCurrent = 1;

    @ApiModelProperty(value = "每页条数")
    private int pageSize = 20;
}
