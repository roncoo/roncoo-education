/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.web.boss.common.bean.qo;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 后台用户信息
 * </p>
 *
 * @author wujing123
 * @since 2018-01-29
 */
@Data
@Accessors(chain = true)
public class SysUserQO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 当前页
	 */
	private int pageCurrent;
	/**
	 * 每页记录数
	 */
	private int pageSize;
	/**
	 * 主键
	 */
	private Long id;
	/**
	 * 状态(0:无效,1:有效)
	 */
	private Integer statusId;
	/**
	 * 排序
	 */
	private Integer sort;
	/**
	 * 用户编号
	 */
	private Long userNo;
	/**
	 * 手机
	 */
	private String mobile;
	/**
	 * 真实姓名
	 */
	private String realName;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 新密码
	 */
	private String mobilePsw;
	/**
	 * 确认密码
	 */
	private String rePwd;

}
