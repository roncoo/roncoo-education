package com.roncoo.education.user.service.admin.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description = "数据统计")
public class AdminStatDataResp implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "今天订单数")
    private Integer todayOrder;

    @ApiModelProperty(value = "今天收入")
    private BigDecimal todayMoney;

    @ApiModelProperty(value = "昨天订单数")
    private Integer yesterdayOrder;

    @ApiModelProperty(value = "昨天收入")
    private BigDecimal yesterdayMoney;

    @ApiModelProperty(value = "用户总数")
    private Integer userSum;

    @ApiModelProperty(value = "课程总数")
    private Integer courseSum;

    @ApiModelProperty(value = "订单总数")
    private Integer orderSum;

    @ApiModelProperty(value = "总收入")
    private BigDecimal orderMoney;

}
