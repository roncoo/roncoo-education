package com.roncoo.education.course.service.pc.resq;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 课时信息-查看
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class CourseChapterPeriodViewRESQ implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @ApiModelProperty(value = "课时ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
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
}
