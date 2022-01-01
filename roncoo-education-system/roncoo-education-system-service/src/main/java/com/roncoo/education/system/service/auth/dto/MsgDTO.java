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
 * 站内信息表
 *
 * @author wuyun
 */
@Data
@Accessors(chain = true)
public class MsgDTO implements Serializable {

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
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtModified;
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
     * 短信内容
     */
    @ApiModelProperty(value = "短信内容")
    private String msgText;
    /**
     * 是否发送(1是;0否)
     */
    @ApiModelProperty(value = "是否发送(1是;0否)")
    private Integer isSend;
    /**
     * 是否置顶(1是;0否)
     */
    @ApiModelProperty(value = "是否置顶(1是;0否)")
    private Integer isTop;
    /**
     * 后台备注
     */
    @ApiModelProperty(value = "后台备注")
    private String backRemark;
}
