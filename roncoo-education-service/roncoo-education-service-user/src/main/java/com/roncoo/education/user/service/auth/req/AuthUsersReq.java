package com.roncoo.education.user.service.auth.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * <p>
 * API-AUTH-用户信息
 * </p>
 *
 * @author wujing
 * @date 2022-08-25
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "API-AUTH-用户信息")
public class AuthUsersReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "请填写昵称")
    @ApiModelProperty(value = "昵称")
    private String nickname;

    @NotNull(message = "请选择性别")
    @ApiModelProperty(value = "用户性别(1男，2女，3保密)")
    private Integer userSex;

    @NotNull(message = "请填写年龄")
    @ApiModelProperty(value = "用户年龄")
    private LocalDate userAge;

    @ApiModelProperty(value = "用户头像")
    private String userHead;

    @ApiModelProperty(value = "备注")
    private String remark;
}
