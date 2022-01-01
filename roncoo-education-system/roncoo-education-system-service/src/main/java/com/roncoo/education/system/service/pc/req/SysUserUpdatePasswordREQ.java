package com.roncoo.education.system.service.pc.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 后台用户信息-更新密码
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class SysUserUpdatePasswordREQ implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 用户编号
     */
    @ApiModelProperty(value = "用户编号", required = true)
    private Long adminUserNo;
    /**
     * 新密码
     */
    @ApiModelProperty(value = "新密码", required = true)
    private String mobilePsw;
    /**
     * 确认密码
     */
    @ApiModelProperty(value = "确认密码", required = true)
    private String rePwd;

}
