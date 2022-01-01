package com.roncoo.education.course.service.auth.bo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 课时信息-审核
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class AuthCourseChapterPeriodAuditSaveBO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序", required = false)
    private Integer sort;
    /**
     * 章节ID
     */
    @ApiModelProperty(value = "章节ID", required = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long chapterId;
    /**
     * 课时名称
     */
    @ApiModelProperty(value = "课时名称", required = true)
    private String periodName;
    /**
     * 课时描述
     */
    @ApiModelProperty(value = "课时描述", required = false)
    private String periodDesc;
    /**
     * 是否免费：1免费，0收费
     */
    @ApiModelProperty(value = "是否免费：1免费，0收费", required = true)
    private Integer isFree;
    /**
     * 用户编号
     */
    @ApiModelProperty(value = "用户编号", required = true)
    private Long userNo;
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
}
