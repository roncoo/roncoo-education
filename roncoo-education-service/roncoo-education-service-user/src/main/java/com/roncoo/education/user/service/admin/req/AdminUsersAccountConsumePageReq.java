package com.roncoo.education.user.service.admin.req;

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
 * ADMIN-用户账户消费记录
 * </p>
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "ADMIN-用户账户消费记录分页")
public class AdminUsersAccountConsumePageReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime gmtCreate;

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "消费类型(1消费，2收入)")
    private Integer consumeType;

    @ApiModelProperty(value = "消费前金额")
    private BigDecimal beginAmount;

    @ApiModelProperty(value = "消费金额(消费是正数，收入是负数)")
    private BigDecimal consumeAmount;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "当前页")
    private int pageCurrent = 1;

    @ApiModelProperty(value = "每页条数")
    private int pageSize = 20;
}
