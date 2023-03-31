package com.roncoo.education.system.service.admin.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author fengyw
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "用户登录")
public class AdminSysUserLoginResp implements Serializable {

    private static final long serialVersionUID = -5227677558610916215L;

    @ApiModelProperty(value = "登录账号", required = true)
    private String mobile;

    @ApiModelProperty(value = "昵称", required = true)
    private String realName;

    @ApiModelProperty(value = "token，有效期为1天", required = true)
    private String token;
}
