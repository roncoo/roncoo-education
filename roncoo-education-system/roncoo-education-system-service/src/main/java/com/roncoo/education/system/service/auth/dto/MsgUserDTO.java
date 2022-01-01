package com.roncoo.education.system.service.auth.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 站内信用户记录表
 *
 * @author LHR
 */
@Data
@Accessors(chain = true)
public class MsgUserDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtCreate;
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
     * 用户编号
     */
    @ApiModelProperty(value = "用户编号")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userNo;
    /**
     * 用户手机
     */
    @ApiModelProperty(value = "用户手机")
    private String mobile;
    /**
     * 短信类型(1系统消息,2其他)
     */
    @ApiModelProperty(value = "短信类型(1系统消息,2其他)")
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
