package com.roncoo.education.course.service.auth.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 章节信息-审核
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class AuthCourseChapterAuditBO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 课程ID
     */
    @ApiModelProperty(value = "课程ID", required = true)
    private Long courseId;
}
