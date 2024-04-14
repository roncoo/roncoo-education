package com.roncoo.education.system.service.admin.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 后台用户信息-修改密码
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class AdminSysUserUpdatePasswordReq implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID", required = true)
    private Long userId;
    /**
     * 新密码
     */
    @ApiModelProperty(value = "新密码", required = true)
    private String mobilePwdEncrypt;

}
