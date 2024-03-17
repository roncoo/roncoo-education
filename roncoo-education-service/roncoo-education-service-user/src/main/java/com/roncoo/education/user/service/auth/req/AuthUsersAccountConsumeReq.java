package com.roncoo.education.user.service.auth.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * API-AUTH-用户账户消费记录
 * </p>
 *
 * @author wujing
 * @date 2024-03-17
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "API-AUTH-用户账户消费记录")
public class AuthUsersAccountConsumeReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime gmtCreate;

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "消费类型(1支出，2收入)")
    private Integer consumeType;

    @ApiModelProperty(value = "消费金额(支出负数，收入是正数)")
    private BigDecimal consumeAmount;

    @ApiModelProperty(value = "剩余金额")
    private BigDecimal balanceAmount;

    @ApiModelProperty(value = "消费订单号")
    private Long orderNo;

    @ApiModelProperty(value = "备注")
    private String remark;
}
