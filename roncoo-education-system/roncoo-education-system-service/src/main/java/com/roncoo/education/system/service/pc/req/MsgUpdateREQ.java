package com.roncoo.education.system.service.pc.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 站内信消息-更新
 */
@Data
@Accessors(chain = true)
public class MsgUpdateREQ implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID", required = true)
    private Long id;
    /**
     * 短信标题
     */
    @ApiModelProperty(value = "短信标题", required = false)
    private String msgTitle;
    /**
     * 短信内容
     */
    @ApiModelProperty(value = "短信内容", required = false)
    private String msgText;
    /**
     * 是否置顶(1是;0否)
     */
    @ApiModelProperty(value = "是否置顶(1是;0否)", required = false)
    private Integer isTop;

}
