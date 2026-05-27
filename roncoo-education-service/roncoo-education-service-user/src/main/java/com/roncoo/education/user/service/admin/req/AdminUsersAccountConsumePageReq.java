package com.roncoo.education.user.service.admin.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * ADMIN-用户账户消费记录
 * </p>
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
@Schema(description = "ADMIN-用户账户消费记录分页")
public class AdminUsersAccountConsumePageReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键ID")
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "创建时间")
    private LocalDateTime gmtCreate;

    @Schema(description = "用户ID")
    private Long userId;

    @Schema(description = "消费类型(1支出，2收入)")
    private Integer consumeType;

    @Schema(description = "消费金额(支出负数，收入是正数)")
    private BigDecimal consumeAmount;

    @Schema(description = "剩余金额")
    private BigDecimal balanceAmount;

    @Schema(description = "消费订单号")
    private Long orderNo;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "当前页")
    private int pageCurrent = 1;

    @Schema(description = "每页条数")
    private int pageSize = 20;
}
