package com.roncoo.education.course.service.auth.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 章节信息-审核
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class AuthCourseChapterAuditViewBO implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<AuthCourseChapterAuditDTO> userCourseChapterAuditListDTO;
}
