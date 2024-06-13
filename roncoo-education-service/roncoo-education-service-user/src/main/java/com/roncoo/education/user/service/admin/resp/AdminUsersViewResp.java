package com.roncoo.education.user.service.admin.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description = "ADMIN-用户信息查看")
public class AdminUsersViewResp implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "状态(1:正常，0:禁用)")
    private Integer statusId;

    @ApiModelProperty(value = "手机号码")
    private String mobile;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "用户性别(1男，2女，3保密)")
    private Integer userSex;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "用户年龄")
    private LocalDate userAge;

    @ApiModelProperty(value = "用户头像")
    private String userHead;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "微信唯一ID")
    private String unionId;

    @ApiModelProperty(value = "微信OpenId")
    private String openId;

    @ApiModelProperty(value = "城市")
    private String city;

    @ApiModelProperty(value = "省份")
    private String province;

    @ApiModelProperty(value = "国家")
    private String country;

    @ApiModelProperty(value = "注册来源")
    private Integer registerSource;

    @ApiModelProperty(value = "用户账户信息")
    private AdminUsersAccountViewResp usersAccountViewResp;
}
