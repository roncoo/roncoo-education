package com.roncoo.education.system.service.pc.resq;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 站内信用户记录表-分页列表
 */
@Data
@Accessors(chain = true)
public class MsgUserPageRESQ implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 状态(1有效, 0无效)
     */
    @ApiModelProperty(value = "状态(1有效, 0无效)")
    private Integer statusId;
    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer sort;
    /**
     * 短信ID
     */
    @ApiModelProperty(value = "短信ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long msgId;
    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String mobile;
    /**
     * 短信类型
     */
    @ApiModelProperty(value = "短信类型")
    private Integer msgType;
    /**
     * 短信标题
     */
    @ApiModelProperty(value = "短信标题")
    private String msgTitle;
    /**
     * 是否阅读(1是;0否)
     */
    @ApiModelProperty(value = "是否阅读(1是;0否)")
    private Integer isRead;
    /**
     * 是否置顶(1是;0否)
     */
    @ApiModelProperty(value = "是否置顶(1是;0否)")
    private Integer isTop;
}
