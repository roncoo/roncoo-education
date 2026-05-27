package com.roncoo.education.user.service.admin.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * <p>
 * ADMIN-用户信息
 * </p>
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
@Schema(description = "ADMIN-用户信息分页")
public class AdminUsersPageReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "状态(1:正常，0:禁用)")
    private Integer statusId;

    @Schema(description = "手机号码")
    private String mobile;

    @Schema(description = "昵称")
    private String nickname;

    @Schema(description = "用户性别(1男，2女，3保密)")
    private Integer userSex;

    @Schema(description = "用户年龄")
    private LocalDate userAge;

    @Schema(description = "用户头像")
    private String userHead;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "当前页")
    private int pageCurrent = 1;

    @Schema(description = "每页条数")
    private int pageSize = 20;
}
