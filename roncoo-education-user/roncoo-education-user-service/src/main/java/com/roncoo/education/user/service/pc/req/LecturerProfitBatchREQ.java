package com.roncoo.education.user.service.pc.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 讲师提现日志-批量更新提现状态
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class LecturerProfitBatchREQ implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 主键集合
     */
    @ApiModelProperty(value = "主键集合", required = true)
    private List<Long> id;
    /**
     * 分润状态（1确认中，2成功，3失败）
     */
    @ApiModelProperty(value = "分润状态（1确认中，2成功，3失败）", required = true)
    private Integer profitStatus;
}
