package com.roncoo.education.course.service.auth.dto;

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
public class AuthCourseChapterPeriodAuditSaveDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 课时ID
     */
    @ApiModelProperty(value = "课时ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 审核状态(0:待审核;1:审核通过;2:审核不通过)
     */
    @ApiModelProperty(value = "审核状态(0:待审核;1:审核通过;2:审核不通过)")
    private Integer auditStatus;
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
     * 是否存在文档
     */
    @ApiModelProperty(value = "是否存在文档：1存在，0否)")
    private Integer isDoc;
}
