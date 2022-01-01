package com.roncoo.education.user.service.pc.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 用户发送短信日志-分页
 */
@Data
@Accessors(chain = true)
public class SendSmsLogPageREQ implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 手机号码
     */
    @ApiModelProperty(value = "手机号码", required = false)
    private String mobile;

    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间", required = false)
    private String beginGmtCreate;
    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间", required = false)
    private String endGmtCreate;

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
}
