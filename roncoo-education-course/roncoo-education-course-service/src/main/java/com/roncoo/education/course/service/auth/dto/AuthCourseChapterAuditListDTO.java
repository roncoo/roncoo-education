package com.roncoo.education.course.service.auth.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 章节信息-审核
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class AuthCourseChapterAuditListDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 审核章节信息集合
     */
    @ApiModelProperty(value = "审核章节信息集合")
    private List<AuthCourseChapterAuditDTO> userChapterAuditList = new ArrayList<>();
}
