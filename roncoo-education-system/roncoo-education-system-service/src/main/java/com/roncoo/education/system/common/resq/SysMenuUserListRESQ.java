package com.roncoo.education.system.common.resq;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 菜单信息-列出
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class SysMenuUserListRESQ implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 菜单集合
	 */
	@ApiModelProperty(value = "菜单集合")
	private List<SysMenuUserRESQ> sysMenu = new ArrayList<>();

}
