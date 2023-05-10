package com.roncoo.education.user.service.admin.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * ADMIN-用户信息
 * </p>
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "ADMIN-用户信息分页")
public class AdminUsersPageReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "状态(1:正常，0:禁用)")
    private Integer statusId;

    @ApiModelProperty(value = "手机号码")
    private String mobile;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "用户性别(1男，2女，3保密)")
    private Integer userSex;

    @ApiModelProperty(value = "用户年龄")
    private Integer userAge;

    @ApiModelProperty(value = "用户头像")
    private String userHead;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "当前页")
    private int pageCurrent = 1;

    @ApiModelProperty(value = "每页条数")
    private int pageSize = 20;
}
