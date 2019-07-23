package com.roncoo.education.system.service.common.resq;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 菜单信息
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class SysMenuUserRESQ implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@ApiModelProperty(value = "主键")
	@JsonSerialize(using = ToStringSerializer.class)
	private Long id;
	/**
	 * 菜单名称
	 */
	@ApiModelProperty(value = "菜单名称")
	private String name;
	/**
	 * 菜单路径
	 */
	@ApiModelProperty(value = "菜单路径")
	private String path;
	/**
	 * 菜单图标
	 */
	@ApiModelProperty(value = "菜单图标")
	private String icon;
	/**
	 * 目标名称
	 */
	@ApiModelProperty(value = "目标名称")
	private String targetName;
	/**
	 * 下级菜单集合
	 */
	private List<SysMenuUserRESQ> children;
}
