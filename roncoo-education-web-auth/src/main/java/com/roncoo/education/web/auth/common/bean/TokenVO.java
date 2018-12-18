/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.web.auth.common.bean;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TokenVO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 机构号
	 */
	private String orgNo;
	/**
	 * 用户编号
	 */
	private Long userNo;
	/**
	 * 手机号码
	 */
	private String mobile;
}
