package com.roncoo.education.system.service.pc.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 角色用户关联表-添加
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class SysRoleUserSaveREQ implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID", required = true)
    private Long userId;

    /**
     * 角色ID集合
     */
    @ApiModelProperty(value = "角色ID集合", required = true)
    private List<Long> roleId;
}
