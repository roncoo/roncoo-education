package com.roncoo.education.user.service.pc.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 讲师提现日志表
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class LecturerProfitPageREQ implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 当前页
     */
    @ApiModelProperty(value = "当前页", required = true)
    private int pageCurrent = 1;
    /**
     * 当前页
     */
    @ApiModelProperty(value = "当前页", required = true)
    private int pageSize = 20;
    /**
     * 讲师名称
     */
    @ApiModelProperty(value = "讲师名称")
    private String lecturerName;
    /**
     * 分润状态（1确认中，2成功，3失败）
     */
    @ApiModelProperty(value = "分润状态（1确认中，2成功，3失败）")
    private Integer profitStatus;

    private String beginDate;
    private String endDate;
}
