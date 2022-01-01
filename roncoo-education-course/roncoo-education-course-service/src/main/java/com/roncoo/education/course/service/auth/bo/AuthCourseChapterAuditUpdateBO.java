/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.course.service.auth.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 讲师章节审核信息保存
 *
 * @author WY
 */
@Data
@Accessors(chain = true)
public class AuthCourseChapterAuditUpdateBO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 章节编号
     */
    @ApiModelProperty(value = "章节编号", required = true)
    private Long id;
    /**
     * 章节名称
     */
    @ApiModelProperty(value = "章节名称", required = true)
    private String chapterName;
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
}
