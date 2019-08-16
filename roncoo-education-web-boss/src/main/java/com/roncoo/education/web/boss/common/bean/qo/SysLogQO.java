/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.web.boss.common.bean.qo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 后台操作日志表
 * </p>
 *
 * @author wujing123
 * @since 2018-01-29
 */
@Data
@Accessors(chain = true)
public class SysLogQO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 当前页
	 */
	private int pageCurrent;
	/**
	 * 每页记录数
	 */
	private int pageSize;

	private Long id;

	private Date gmtCreate;

	private Long userNo;

	private String realName;

	private String ip;

	private String operation;

	private String method;

	private String path;

	private String content;

	/**
	 * 查询开始时间
	 */
	private String beginDate;

	/**
	 * 查询结束时间
	 */
	private String endDate;
}
