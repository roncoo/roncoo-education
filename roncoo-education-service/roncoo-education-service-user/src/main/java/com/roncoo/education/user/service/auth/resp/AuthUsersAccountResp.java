package com.roncoo.education.user.service.auth.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * API-AUTH-用户账户
 * </p>
 *
 * @author wujing
 * @date 2024-03-16
 */
@Data
@Accessors(chain = true)
@Schema(description = "API-AUTH-用户账户")
public class AuthUsersAccountResp implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键ID")
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "创建时间")
    private LocalDateTime gmtCreate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "修改时间")
    private LocalDateTime gmtModified;

    @Schema(description = "状态(1:正常，0:禁用)")
    private Integer statusId;

    @Schema(description = "用户ID")
    private Long userId;

    @Schema(description = "可用金额")
    private BigDecimal availableAmount;

    @Schema(description = "冻结金额")
    private BigDecimal freezeAmount;

    @Schema(description = "防篡改值")
    private String sign;
}
