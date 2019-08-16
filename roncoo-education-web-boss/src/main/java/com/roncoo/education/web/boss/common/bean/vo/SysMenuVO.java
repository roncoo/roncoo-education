/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.web.boss.common.bean.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 菜单信息
 * </p>
 *
 * @author wujing123
 * @since 2018-01-29
 */
@Data
@Accessors(chain = true)
public class SysMenuVO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	private Long id;
	/**
	 * 创建时间
	 */
	private Date gmtCreate;
	/**
	 * 修改时间
	 */
	private Date gmtModified;
	/**
	 * 状态(0:无效,1:有效)
	 */
	private Integer statusId;
	/**
	 * 排序
	 */
	private Integer sort;
	/**
	 * 父ID
	 */
	private Long parentId;
	/**
	 * 菜单名称
	 */
	private String menuName;
	/**
	 * 菜单路径
	 */
	private String menuUrl;
	/**
	 * 目标名称
	 */
	private String targetName;
	/**
	 * 菜单图标
	 */
	private String menuIcon;
	/**
	 * 备注
	 */
	private String remark;

	private List<SysMenuVO> list;

	private Integer isShow;

}
