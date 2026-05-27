package com.roncoo.education.user.service.auth.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

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
@Schema(description = "API-AUTH-用户信息")
public class AuthUsersReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "请填写昵称")
    @Schema(description = "昵称")
    private String nickname;

    @NotNull(message = "请选择性别")
    @Schema(description = "用户性别(1男，2女，3保密)")
    private Integer userSex;

    @NotNull(message = "请填写年龄")
    @Schema(description = "用户年龄")
    private LocalDate userAge;

    @Schema(description = "用户头像")
    private String userHead;

    @Schema(description = "备注")
    private String remark;
}
