package com.roncoo.education.user.service.admin.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 用户日志
 * </p>
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "用户登录")
public class AdminStatLogin implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "时间")
    private String dates;

    @ApiModelProperty(value = "当天总次数")
    private Long logins;

    @ApiModelProperty(value = "登录状态")
    private Integer loginStatus;

}
