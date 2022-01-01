package com.roncoo.education.course.service.auth.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 章节信息-审核
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class AuthCourseChapterAuditUpdateDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 章节ID
     */
    @ApiModelProperty(value = "章节ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 章节名称
     */
    @ApiModelProperty(value = "章节名称")
    private String chapterName;
    /**
     * 是否免费：1免费，0收费
     */
    @ApiModelProperty(value = "是否免费：1免费，0收费")
    private Integer isFree;
    /**
     * 原价
     */
    @ApiModelProperty(value = "原价")
    private BigDecimal chapterOriginal;

}
