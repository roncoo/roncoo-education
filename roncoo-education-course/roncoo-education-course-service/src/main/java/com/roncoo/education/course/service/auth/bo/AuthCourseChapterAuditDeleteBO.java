/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.course.service.auth.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 讲师章节审核信息删除
 *
 * @author WY
 */
@Data
@Accessors(chain = true)
public class AuthCourseChapterAuditDeleteBO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 章节ID
     */
    @ApiModelProperty(value = "章节ID", required = true)
    private Long id;
    /**
     * 用户编号
     */
    @ApiModelProperty(value = "用户编号", required = true)
    private Long userNo;
}
