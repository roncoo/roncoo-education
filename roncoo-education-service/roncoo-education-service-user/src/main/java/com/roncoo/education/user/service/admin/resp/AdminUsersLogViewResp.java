package com.roncoo.education.user.service.admin.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * ADMIN-用户登录日志
 * </p>
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
@Schema(description = "ADMIN-用户登录日志查看")
public class AdminUsersLogViewResp implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "创建时间")
    private LocalDateTime gmtCreate;

    @Schema(description = "用户ID")
    private Long userId;

    @Schema(description = "登录状态(1成功，0失败)")
    private Integer loginStatus;

    @Schema(description = "登录客户端")
    private Integer loginClient;

    @Schema(description = "登录方式")
    private Integer loginType;

    @Schema(description = "登录IP")
    private String loginIp;

    @Schema(description = "国家")
    private String country;

    @Schema(description = "省")
    private String province;

    @Schema(description = "市")
    private String city;

    @Schema(description = "浏览器")
    private String browser;

    @Schema(description = "操作系统")
    private String os;
}
