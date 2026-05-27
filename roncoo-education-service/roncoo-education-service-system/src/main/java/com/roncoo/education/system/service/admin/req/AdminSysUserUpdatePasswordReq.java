package com.roncoo.education.system.service.admin.req;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description = "用户ID", required = true)
    private Long userId;
    /**
     * 新密码
     */
    @Schema(description = "新密码", required = true)
    private String mobilePwdEncrypt;

}
