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
@ApiModel(description = "ADMIN-用户信息修改")
public class AdminUsersEditReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "状态(1:正常，0:禁用)")
    private Integer statusId;

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
}
