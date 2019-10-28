package com.roncoo.education.course.common.bo.auth;

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
public class AuthCourseChapterPeriodAuditSortBO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
	 * 课时ID
	 */
	@ApiModelProperty(value = "课时ID", required = true)
	private List<Long> periodIdList;
	/**
	 * 用户编号
	 */
	@ApiModelProperty(value = "用户编号", required = true)
	private Long userNo;
}
