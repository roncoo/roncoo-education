package com.roncoo.education.user.common.bo.auth;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 讲师提现日志表
 *
 * @author wuyun
 */
@Data
@Accessors(chain = true)
public class AuthLecturerProfitPageBO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 讲师用户编号
	 */
	@ApiModelProperty(value = "讲师用户编号", required = true)
	private Long lecturerUserNo;
	/**
	 * 当前页
	 */
	@ApiModelProperty(value = "当前页", required = true)
	private int pageCurrent = 1;
	/**
	 * 每页记录数
	 */
	@ApiModelProperty(value = "每页记录数", required = true)
	private int pageSize = 20;
}
