package com.roncoo.education.user.service.admin.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * ADMIN-用户登录日志
 * </p>
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "ADMIN-用户登录日志分页")
public class AdminUsersLogPageReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "登录客户端")
    private Integer loginClient;

    @ApiModelProperty(value = "登录方式")
    private Integer loginType;

    @ApiModelProperty(value = "当前页")
    private int pageCurrent = 1;

    @ApiModelProperty(value = "每页条数")
    private int pageSize = 20;
}
