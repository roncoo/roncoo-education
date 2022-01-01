package com.roncoo.education.system.service.pc.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 站内信消息-保存
 */
@Data
@Accessors(chain = true)
public class MsgSaveREQ implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 短信类型(1系统消息,2其他)
     */
    @ApiModelProperty(value = "短信类型(1系统消息,2其他)", required = true)
    private Integer msgType;
    /**
     * 短信标题
     */
    @ApiModelProperty(value = "短信标题", required = true)
    private String msgTitle;
    /**
     * 短信内容
     */
    @ApiModelProperty(value = "短信内容", required = false)
    private String msgText;
    /**
     * 是否定时发送（1是，0否）
     */
    @ApiModelProperty(value = "是否定时发送（1是，0否)", required = false)
    private Integer isTimeSend;
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
    /**
     * 发送时间
     */
    @ApiModelProperty(value = "发送时间", required = false)
    private Date sendTime;

}
