package com.roncoo.education.system.service.pc.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 站内信消息-分页列表
 *
 * @author kyh
 */
@Data
@Accessors(chain = true)
public class MsgPageREQ implements Serializable {
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
     * 状态(1:正常，0:禁用)
     */
    @ApiModelProperty(value = "状态(1:正常，0:禁用)", required = false)
    private Integer statusId;
    /**
     * 短信标题
     */
    @ApiModelProperty(value = "短信标题", required = false)
    private String msgTitle;
    /**
     * 是否发送(1是;0否)
     */
    @ApiModelProperty(value = "是否发送(1是;0否)", required = false)
    private Integer isSend;
    /**
     * 是否置顶(1是;0否)
     */
    @ApiModelProperty(value = "是否置顶(1是;0否)", required = false)
    private Integer isTop;

}
