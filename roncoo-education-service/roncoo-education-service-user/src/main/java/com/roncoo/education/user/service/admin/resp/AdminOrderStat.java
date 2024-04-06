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
public class AdminOrderStat implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "时间")
    private String dates;

    @ApiModelProperty(value = "订单数")
    private Integer orders;

    @ApiModelProperty(value = "金额")
    private BigDecimal moneys;

}
