package com.roncoo.education.system.service.admin.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 后台用户信息-添加
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class SysUserSaveREQ implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID", required = true)
    private Long userId;
    /**
     * 手机
     */
    @ApiModelProperty(value = "手机", required = true)
    private String mobile;
    /**
     * 真实姓名
     */
    @ApiModelProperty(value = "真实姓名", required = true)
    private String realName;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", required = false)
    private String remark;

}
