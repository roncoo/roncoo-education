package com.roncoo.education.course.service.api.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 课时信息
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class CourseChapterPeriodDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 课时ID
     */
    @ApiModelProperty(value = "课时ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 状态(1:正常，0:禁用)
     */
    @ApiModelProperty(value = "状态(1:正常，0:禁用)")
    private Integer statusId;
    /**
     * 课程ID
     */
    @ApiModelProperty(value = "课程ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long courseId;
    /**
     * 章节ID
     */
    @ApiModelProperty(value = "章节ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long chapterId;
    /**
     * 课时名称
     */
    @ApiModelProperty(value = "课时名称")
    private String periodName;
    /**
     * 课时描述
     */
    @ApiModelProperty(value = "课时描述")
    private String periodDesc;
    /**
     * 是否免费：1免费，0收费
     */
    @ApiModelProperty(value = "是否免费：1免费，0收费")
    private Integer isFree;
    /**
     * 原价
     */
    @ApiModelProperty(value = "原价")
    private BigDecimal periodOriginal;
    /**
     * 优惠价
     */
    @ApiModelProperty(value = "优惠价")
    private BigDecimal periodDiscount;
    /**
     * 购买人数
     */
    @ApiModelProperty(value = "购买人数")
    private Integer countBuy;
    /**
     * 学习人数
     */
    @ApiModelProperty(value = "学习人数")
    private Integer countStudy;
    /**
     * 是否存在文档(1存在，0否)
     */
    @ApiModelProperty(value = "是否存在文档(1存在，0否)")
    private Integer isDoc;
    /**
     * 文档名称
     */
    @ApiModelProperty(value = "文档名称")
    private String docName;
    /**
     * 文档地址
     */
    @ApiModelProperty(value = "文档地址")
    private String docUrl;
    /**
     * 是否存在视频(1存在，0否)
     */
    @ApiModelProperty(value = "是否存在视频(1存在，0否)")
    private String isVideo;
    /**
     * 视频编号
     */
    @ApiModelProperty(value = "视频编号")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long videoNo;
    /**
     * 视频名称
     */
    @ApiModelProperty(value = "视频名称")
    private String videoName;
    /**
     * 时长
     */
    @ApiModelProperty(value = "时长")
    private String videoLength;
    /**
     * 视频ID
     */
    @ApiModelProperty(value = "视频ID")
    private String videoVid;
    /**
     * 阿里云oas
     */
    @ApiModelProperty(value = "阿里云oas")
    private String videoOasId;
}
