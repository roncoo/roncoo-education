package com.roncoo.education.system.common.req;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 角色用户关联表-列出
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class SysRoleUserListREQ implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 用户ID
	 */
	@ApiModelProperty(value = "用户ID", required = true)
	private Long userId;
}
