package com.roncoo.education.course.service.biz.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * API-课程信息
 * </p>
 *
 * @author wujing
 * @date 2022-08-25
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "API-课程信息")
public class CourseResp implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "是否允许学习: 0禁止学习，1可以学习")
    private Integer allowStudy = 0;

    @ApiModelProperty(value = "主键")
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime gmtCreate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "修改时间")
    private LocalDateTime gmtModified;

    @ApiModelProperty(value = "课程名称")
    private String courseName;

    @ApiModelProperty(value = "课程封面")
    private String courseLogo;

    @ApiModelProperty(value = "课程简介")
    private String introduce;

    @ApiModelProperty(value = "是否免费(1:免费，0:收费)")
    private Integer isFree;

    @ApiModelProperty(value = "原价")
    private BigDecimal rulingPrice;

    @ApiModelProperty(value = "优惠价")
    private BigDecimal coursePrice;

    @ApiModelProperty(value = "购买人数")
    private Integer countBuy;

    @ApiModelProperty(value = "学习人数")
    private Integer countStudy;

    @ApiModelProperty(value = "分类ID")
    private Long categoryId;

    @ApiModelProperty(value = "讲师ID")
    private Long lecturerId;

    @ApiModelProperty(value = "讲师信息")
    private CourseLecturerResp lecturerResp;

    @ApiModelProperty(value = "章节信息")
    private List<CourseChapterResp> chapterRespList;

    @ApiModelProperty(value = "课程收藏状态")
    private Boolean courseCollect = false;

}
