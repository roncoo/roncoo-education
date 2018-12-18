package com.roncoo.education.course.common.bean.dto.auth;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 课时信息-审核
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class AuthCourseChapterPeriodAuditListDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<AuthCourseChapterPeriodAuditDTO> userCourseChapterPeriodAuditListDTO;

	/**
	 * 视频数量
	 */
	@ApiModelProperty(value = "视频数量")
	private Integer videoNum;
	/**
	 * 文档数量
	 */
	@ApiModelProperty(value = "文档数量")
	private Integer docNum;
}
