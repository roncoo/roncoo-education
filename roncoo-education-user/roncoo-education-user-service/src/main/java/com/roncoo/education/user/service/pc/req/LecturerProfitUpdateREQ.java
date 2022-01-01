package com.roncoo.education.user.service.pc.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 讲师提现日志-更新提现状态
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class LecturerProfitUpdateREQ implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键", required = true)
    private Long id;
    /**
     * 分润状态（1确认中，2成功，3失败）
     */
    @ApiModelProperty(value = "分润状态（1确认中，2成功，3失败）", required = true)
    private Integer profitStatus;
}
