package com.roncoo.education.user.service.admin.resp;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "用户登录")
public class AdminStatLogin implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "时间")
    private String dates;

    @Schema(description = "当天总次数")
    private Long logins;

    @Schema(description = "登录状态")
    private Integer loginStatus;

}
