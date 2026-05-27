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
public class AdminStatDataResp implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "今天订单数")
    private Integer todayOrder = 0;

    @Schema(description = "今天收入")
    private BigDecimal todayMoney = BigDecimal.ZERO;

    @Schema(description = "昨天订单数")
    private Integer yesterdayOrder = 0;

    @Schema(description = "昨天收入")
    private BigDecimal yesterdayMoney = BigDecimal.ZERO;

    @Schema(description = "用户总数")
    private Integer userSum = 0;

    @Schema(description = "课程总数")
    private Integer courseSum = 0;

    @Schema(description = "订单总数")
    private Integer orderSum = 0;

    @Schema(description = "总收入")
    private BigDecimal orderMoney = BigDecimal.ZERO;

}
