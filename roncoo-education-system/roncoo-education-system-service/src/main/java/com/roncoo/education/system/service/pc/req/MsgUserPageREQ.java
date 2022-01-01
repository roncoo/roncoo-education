package com.roncoo.education.system.service.pc.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 站内信用户记录表-分页列表
 */
@Data
@Accessors(chain = true)
public class MsgUserPageREQ implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 当前页
     */
    @ApiModelProperty(value = "当前页", required = true)
    private int pageCurrent = 1;
    /**
     * 每页记录数
     */
    @ApiModelProperty(value = "每页记录数", required = true)
    private int pageSize = 20;
    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号", required = false)
    private String mobile;
    /**
     * 短信标题
     */
    @ApiModelProperty(value = "短信标题", required = false)
    private String msgTitle;
}
