package com.roncoo.education.user.service.auth.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
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
public class AuthUsersResp implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "微信unionId")
    private String unionId;

    @Schema(description = "微信OpenId")
    private String openId;

    @Schema(description = "城市")
    private String city;

    @Schema(description = "省份")
    private String province;

    @Schema(description = "国家")
    private String country;

    @Schema(description = "状态(1:正常，0:禁用)")
    private Integer statusId;

    @Schema(description = "手机号码")
    private String mobile;

    @Schema(description = "昵称")
    private String nickname;

    @Schema(description = "用户类型(1用户，2讲师)")
    private Integer userType;

    @Schema(description = "用户性别(1男，2女，3保密)")
    private Integer userSex;

    @Schema(description = "用户年龄")
    private LocalDate userAge;

    @Schema(description = "用户头像")
    private String userHead;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "可用金额")
    private BigDecimal availableAmount;

    @Schema(description = "冻结金额")
    private BigDecimal freezeAmount;
}
