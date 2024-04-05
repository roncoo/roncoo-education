package com.roncoo.education.user.service.admin.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * ADMIN-用户信息
 * </p>
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "ADMIN-用户信息统计")
public class AdminOrderInfoStatResp implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "购买课程总数量")
    private Integer courseBuySum;

    @ApiModelProperty(value = "购买课程总金额")
    private BigDecimal courseBuyMoney;
}
