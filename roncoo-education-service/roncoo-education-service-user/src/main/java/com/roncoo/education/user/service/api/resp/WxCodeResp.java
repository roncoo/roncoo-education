package com.roncoo.education.user.service.api.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author fengyw
 */
@Data
@Accessors(chain = true)
public class WxCodeResp implements Serializable {

    private static final long serialVersionUID = 2621609267080102065L;

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "token，有效期为1天")
    private String token;

    @ApiModelProperty(value = "已绑定用户")
    private Boolean bindUser;

    @ApiModelProperty(value = "授权信息")
    private AuthInfo authInfo;

    /**
     * 授权信息
     */
    @Data
    @Accessors(chain = true)
    public static class AuthInfo implements Serializable {

        @ApiModelProperty(value = "允许用户注册(false:禁止注册、true:允许注册)")
        private Boolean allowUserRegister;

        @ApiModelProperty(value = "UnionId")
        private String unionId;

        @ApiModelProperty(value = "用户头像")
        private String headImg;

        @ApiModelProperty(value = "用户昵称")
        private String nickname;

        @ApiModelProperty(value = "性别(1:男、2:女)")
        private Integer gender;
    }
}
