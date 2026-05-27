package com.roncoo.education.user.service.admin.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
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
@Schema(description = "ADMIN-用户信息查看")
public class AdminUsersViewResp implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "状态(1:正常，0:禁用)")
    private Integer statusId;

    @Schema(description = "手机号码")
    private String mobile;

    @Schema(description = "昵称")
    private String nickname;

    @Schema(description = "用户性别(1男，2女，3保密)")
    private Integer userSex;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Schema(description = "用户年龄")
    private LocalDate userAge;

    @Schema(description = "用户头像")
    private String userHead;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "微信唯一ID")
    private String unionId;

    @Schema(description = "微信OpenId")
    private String openId;

    @Schema(description = "城市")
    private String city;

    @Schema(description = "省份")
    private String province;

    @Schema(description = "国家")
    private String country;

    @Schema(description = "注册来源")
    private Integer registerSource;

    @Schema(description = "用户账户信息")
    private AdminUsersAccountViewResp usersAccountViewResp;
}
