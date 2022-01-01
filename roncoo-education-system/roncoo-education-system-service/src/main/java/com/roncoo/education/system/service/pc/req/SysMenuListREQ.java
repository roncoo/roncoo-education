package com.roncoo.education.system.service.pc.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 菜单信息-列出
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class SysMenuListREQ implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单名称
     */
    @ApiModelProperty(value = "菜单名称", required = false)
    private String menuName;

}
