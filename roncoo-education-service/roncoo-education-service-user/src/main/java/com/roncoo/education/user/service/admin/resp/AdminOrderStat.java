package com.roncoo.education.user.service.admin.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 用户日志
 * </p>
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
@Schema(description = "数据统计")
public class AdminOrderStat implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "时间")
    private String dates;

    @Schema(description = "订单数")
    private Integer orders;

    @Schema(description = "金额")
    private BigDecimal moneys;

}
