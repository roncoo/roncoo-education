package com.roncoo.education.user.service.admin.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * ADMIN-用户账户消费记录
 * </p>
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "ADMIN-用户账户消费记录添加")
public class AdminUsersAccountConsumeSaveReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "消费类型(1消费，2收入)")
    private Integer consumeType;

    @ApiModelProperty(value = "消费金额(消费是正数，收入是负数)")
    private BigDecimal consumeAmount;

    @ApiModelProperty(value = "备注")
    private String remark;
}
