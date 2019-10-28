package com.roncoo.education.system.common.req;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用户菜单信息-列出
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class SysMenuUserListREQ implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户编号
	 */
	@ApiModelProperty(value = "用户编号", required = false)
	private Long userNo;

}
