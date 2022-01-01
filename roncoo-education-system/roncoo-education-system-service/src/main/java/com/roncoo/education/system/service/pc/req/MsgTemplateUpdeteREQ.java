package com.roncoo.education.system.service.pc.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 消息模板-更新
 */
@Data
@Accessors(chain = true)
public class MsgTemplateUpdeteREQ implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键", required = true)
    private Long id;
    /**
     * 标题
     */
    @ApiModelProperty(value = "标题", required = true)
    private String title;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", required = true)
    private String remark;

}
