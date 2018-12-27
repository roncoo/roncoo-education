package com.roncoo.education.course.common.bean.dto.auth;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 章节信息-审核
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class AuthCourseChapterAuditListDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<AuthCourseChapterAuditDTO> userChapterAuditList;
}
